package service;

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

import static user.springbook.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static user.springbook.service.UserService.MIN_RECOMMENDED_FOR_GOLD;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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
    public void upgradeLevels() {
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
            assertNotEquals(userUpdated.getLevel(), user.getLevel());
        } else {
            assertEquals(userUpdated.getLevel(), user.getLevel());
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


}
