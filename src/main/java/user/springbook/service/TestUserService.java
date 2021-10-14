package user.springbook.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import user.springbook.domain.User;
import user.springbook.exception.TestUserServiceException;
import user.springbook.service.UserServiceImpl;

import java.util.List;

@Service("testUserService")
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
