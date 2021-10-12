package user.springbook.service;

import user.springbook.dao.UserDao;
import user.springbook.domain.Level;
import user.springbook.domain.User;

import java.util.List;

public class UserService {
    UserLevelUpgradePolicy upgradePolicy;
    UserDao userDao;
    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMENDED_FOR_GOLD = 30;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUpgradePolicy(UserLevelUpgradePolicy upgradePolicy) {
        this.upgradePolicy = upgradePolicy;
    }

    public void upgradeLevels() {
        List<User> userlist = userDao.getAll();
        for (User user : userlist) {
            upgradeLevel(user);
        }
    }

    public void add(User user) {
        if (user.getLevel() == null) user.setLevel(Level.BASIC);
        userDao.add(user);
    }

    private void upgradeLevel(User user) {
        if (upgradePolicy.canUpgradeLevel(user)) {
            user.upgradeLevel();
            userDao.update(user);
        }
    }

}

