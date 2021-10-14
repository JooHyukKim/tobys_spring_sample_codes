package user.springbook.service;

import user.springbook.domain.User;
import user.springbook.exception.TestUserServiceException;

import java.util.List;

public class TestUserService extends UserServiceImpl {
    private String id = "user3";

    @Override
    protected void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) {
            throw new TestUserServiceException();
        }
        super.upgradeLevel(user);

    }


    public List<User> getAll() {
        for (User user :
                super.getAll()) {
            super.update(user);

        }
        return null;
    }


}
