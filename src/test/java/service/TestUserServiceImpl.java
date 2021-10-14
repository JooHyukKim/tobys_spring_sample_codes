package service;

import user.springbook.domain.User;
import user.springbook.exception.TestUserServiceException;
import user.springbook.service.UserServiceImpl;

class TestUserServiceImpl extends UserServiceImpl {
    private String id = "user3";


    @Override
    protected void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) {
            throw new TestUserServiceException();
        }
        super.upgradeLevel(user);

    }

}
