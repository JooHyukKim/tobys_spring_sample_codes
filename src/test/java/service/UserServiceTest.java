package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import user.springbook.dao.UserDao;
import user.springbook.domain.Level;
import user.springbook.domain.User;
import user.springbook.service.UserService;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import static user.springbook.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static user.springbook.service.UserService.MIN_RECOMMENDED_FOR_GOLD;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    @Autowired
    PlatformTransactionManager transactionManager;


    List<User> userlist;

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(userDao);

        userlist = Arrays.asList(
                new User("user1", "user1", "1234", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0)
                , new User("user2", "user2", "1234", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0)
                , new User("user3", "user3", "1234", Level.SILVER, MIN_RECOMMENDED_FOR_GOLD - 1, 29)
                , new User("user4", "user4", "1234", Level.SILVER, MIN_RECOMMENDED_FOR_GOLD, 30)
                , new User("user5", "user5", "1234", Level.GOLD, MIN_RECOMMENDED_FOR_GOLD, 100)
        );
    }

    @Test
    public void bean() {
        Assertions.assertNotNull(userService);
    }

    @Test
    public void upgradeLevels() throws SQLException {
        userDao.deleteAll();
        userlist.forEach(user -> {
            userDao.add(user);
        });

        userService.upgradeLevels();

        List<User> users = userDao.getAll();
        checkLevel(userlist.get(0), false);
        checkLevel(userlist.get(1), true);
        checkLevel(userlist.get(2), false);
        checkLevel(userlist.get(3), true);
        checkLevel(userlist.get(4), false);
    }

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
    @Description("트랜잭션 테스트")
    public void upgradeAllOrNothing() {
        UserService testUserService = new TestUserService(userlist.get(3).getId());

        // 빈으로 등록 할것이 아니므로 수동으로 DI 해준다.
        testUserService.setUserDao(this.userDao);
        testUserService.setTransactionManager(this.transactionManager);

        userDao.deleteAll();

        for (User user :
                userlist) {
            testUserService.add(user);
        }

        try {
            testUserService.upgradeLevels();
            fail("TestUserServiceException expected");
        } catch (TestUserServiceException | SQLException e) {

        }
        checkLevel(userlist.get(1), false);
    }

}

class TestUserService extends UserService {
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

