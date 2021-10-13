package service;

import com.mysql.cj.TransactionEventHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import user.springbook.dao.UserDao;
import user.springbook.domain.Level;
import user.springbook.domain.User;
import user.springbook.service.TransactionHandler;
import user.springbook.service.UserService;
import user.springbook.service.UserServiceImpl;
import user.springbook.service.UserServiceTx;

import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static user.springbook.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static user.springbook.service.UserServiceImpl.MIN_RECOMMENDED_FOR_GOLD;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserDao userDao;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    MailSender mailSender;


    List<User> userlist;

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(userDao);

        userlist = Arrays.asList(
                new User("user1", "user1", "1234", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0, "beanskobe@gmail.com")
                , new User("user2", "user2", "1234", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0, "beanskobe@gmail.com")
                , new User("user3", "user3", "1234", Level.SILVER, MIN_RECOMMENDED_FOR_GOLD - 1, 29, "beanskobe@gmail.com")
                , new User("user4", "user4", "1234", Level.SILVER, MIN_RECOMMENDED_FOR_GOLD, 30, "beanskobe@gmail.com")
                , new User("user5", "user5", "1234", Level.GOLD, MIN_RECOMMENDED_FOR_GOLD, 100, "beanskobe@gmail.com")
        );
    }

    @Test
    public void bean() {
        Assertions.assertNotNull(userService);
    }

    @Test
    public void upgradeLevels() throws SQLException {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        MockerUserDao mockerUserDao = new MockerUserDao(this.userlist);
        userServiceImpl.setUserDao(mockerUserDao);

        MockMailSender mockMailSender = new MockMailSender();
        userServiceImpl.setMailSender(mockMailSender);

        userServiceImpl.upgradeLevels();

        List<User> updated = mockerUserDao.getUpdated();

        assertEquals(updated.size(), 2);
        checkUserAndLevel(updated.get(0), "user2", Level.SILVER);
        checkUserAndLevel(updated.get(1), "user4", Level.GOLD);

        List<String> request = mockMailSender.getRequests();
        assertEquals(2, request.size());
        assertEquals(userlist.get(1).getEmail(), request.get(0));
        assertEquals(userlist.get(3).getEmail(), request.get(1));

    }

    @Test
    public void mockUpgradeLevels() {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        UserDao mockUserDao = mock(UserDao.class);
        when(mockUserDao.getAll()).thenReturn(this.userlist);
        userServiceImpl.setUserDao(mockUserDao);

        MailSender mockMailSender = mock(MailSender.class);
        userServiceImpl.setMailSender(mockMailSender);

        userServiceImpl.upgradeLevels();

        verify(mockUserDao, times(2)).update(any(User.class));
        verify(mockUserDao, times(2)).update(any(User.class));

        verify(mockUserDao).update(userlist.get(1));
        assertEquals(Level.SILVER, userlist.get(1).getLevel());

        verify(mockUserDao).update(userlist.get(3));
        assertEquals(Level.GOLD, userlist.get(3).getLevel());

        ArgumentCaptor<SimpleMailMessage> mailMessageArg = ArgumentCaptor.forClass(SimpleMailMessage.class);

        verify(mockMailSender, times(2)).send(mailMessageArg.capture());
        List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
        assertEquals(userlist.get(1).getEmail(), mailMessages.get(0).getTo()[0]);
        assertEquals(userlist.get(3).getEmail(), mailMessages.get(1).getTo()[0]);
    }

    private void checkUserAndLevel(User updated, String expectedId, Level expectedLevel) {
        assertEquals(expectedId, updated.getId());
        assertEquals(expectedLevel, updated.getLevel());
    }

//

    private void checkLevel(User user, boolean hasBeenUpgraded) {
        User userUpdated = userDao.get(user.getId());
        if (hasBeenUpgraded) {
            assertNotEquals(user.getLevel(), userUpdated.getLevel());
        } else {
            assertEquals(user.getLevel(), userUpdated.getLevel());
        }
    }

    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = userlist.get(4); // GOLD
        User userWIthoutLevel = userlist.get(0);
        userWIthoutLevel.setLevel(null);

        userService.add(userWithLevel);
        userService.add(userWIthoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWIthoutLevel.getId());

        assertEquals(userWithLevelRead.getLevel(), userWithLevel.getLevel());
        assertEquals(userWithoutLevelRead.getLevel(), Level.BASIC);
    }

    @Test
    public void update() {
        userDao.deleteAll();

        User user = userlist.get(1);

        userDao.add(userlist.get(1));

        Assertions.assertEquals(user.getLevel(), Level.BASIC);

        user.setLevel(Level.SILVER);

        userDao.update(user);
        User updatedUser1 = userDao.get(user.getId());
        Assertions.assertEquals(Level.SILVER, updatedUser1.getLevel());

    }

    @Test
    @Description("트랜잭션 테스트")
    @DirtiesContext
    public void upgradeAllOrNothing() {
        TestUserService testUserService = new TestUserService(userlist.get(3).getId());
        testUserService.setMailSender(this.mailSender);
        testUserService.setUserDao(this.userDao);

        // 빈으로 등록 할것이 아니므로 수동으로 DI 해준다.
        TransactionHandler txHandler = new TransactionHandler();
        txHandler.setTarget(testUserService);
        txHandler.setPattern("upgradeLevels");
        txHandler.setTransactionManager(transactionManager);

        UserService userService = (UserService) Proxy.newProxyInstance(
                getClass().getClassLoader()
                , new Class[]{UserService.class}
                , txHandler
        );
    }


}

class TestUserService extends UserServiceImpl {
    private String id;

    public TestUserService(String id) {
        this.id = id;
    }

    @Override
    protected void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) {
            throw new TestUserServiceException();
        }
        super.upgradeLevel(user);

    }

}


class MockMailSender implements MailSender {
    private List<String> requests = new ArrayList<>();

    public List<String> getRequests() {
        return requests;
    }

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        requests.add(simpleMessage.getTo()[0]);
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {

    }
}

class MockerUserDao implements UserDao {
    private List<User> users;
    private List<User> updated = new ArrayList<>();

    public MockerUserDao(List<User> users) {
        this.users = users;
    }

    public List<User> getUpdated() {
        return updated;
    }

    @Override
    public void add(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User get(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User user) {
        updated.add(user);
    }

}
