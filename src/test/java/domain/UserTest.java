package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.springbook.domain.Level;
import user.springbook.domain.User;

import static user.springbook.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;

public class UserTest {
    User user;
    User user1 ;

    @BeforeEach
    public void setUp() {
        user = new User();
        user1 = new User("user1", "user1", "1234", Level.SILVER, MIN_LOGCOUNT_FOR_SILVER, 0, "beanskobe@gmail.com");
    }

    @Test
    public void level(){
        Assertions.assertEquals(2, user1.getLevel().intValue());
    }

    @Test
    public void upgradeLevel() {
        Level[] levels = Level.values();
        for (Level level : levels) {
            if (level.nextLevel() == null) continue;

            user.setLevel(level);
            user.upgradeLevel();
            Assertions.assertEquals(user.getLevel(), level.nextLevel());
        }
    }

    @Test
    public void cannotUpgradeLevel() {
        Level[] levels = Level.values();
        for (Level level : levels) {
            if (level.nextLevel() != null) continue;
            user.setLevel(level);
            user.upgradeLevel();
        }
    }
}
