package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.springbook.dao.UserDao;
import user.springbook.domain.Level;
import user.springbook.domain.User;
import user.springbook.service.UserService;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-applicationContext.xml")
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;

    List<User> userlist;

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(userDao);

        userlist = Arrays.asList(
                new User("user1", "user1", "1234", Level.BASIC, 49, 0)
                , new User("user2", "user2", "1234", Level.BASIC, 50, 0)
                , new User("user3", "user3", "1234", Level.SILVER, 60, 29)
                , new User("user4", "user4", "1234", Level.SILVER, 60, 30)
                , new User("user5", "user5", "1234", Level.GOLD, 100, 100)
        );
    }

    @Test
    public void bean() {
        Assertions.assertNotNull(userService);
    }

    @Test
    public void upgradeLevels() {
        userDao.deleteAll();
        userlist.forEach(user -> userDao.add(user));

        userService.upgradeLevels();

        checkLevel(userlist.get(0), Level.BASIC);
        checkLevel(userlist.get(1), Level.SILVER);
        checkLevel(userlist.get(2), Level.SILVER);
        checkLevel(userlist.get(3), Level.GOLD);
        checkLevel(userlist.get(4), Level.GOLD);
    }

    private void checkLevel(User user, Level expectedLevel) {
        User userUpdated = userDao.get(user.getId());
        Assertions.assertEquals(expectedLevel, userUpdated.getLevel());
    }


}
