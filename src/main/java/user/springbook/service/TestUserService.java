package user.springbook.service;

import user.springbook.domain.User;
import user.springbook.exception.TestUserServiceException;

class TestUserService extends UserServiceImpl {
    private String id = "user3";

    @Override
    protected void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) {
            throw new TestUserServiceException();
        }
        super.upgradeLevel(user);

    }

}
