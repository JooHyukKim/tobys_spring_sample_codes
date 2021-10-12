package user.springbook.service;

import user.springbook.domain.User;

public interface UserLevelUpgradePolicy {
    boolean canUpgradeLevel(User user);
}
