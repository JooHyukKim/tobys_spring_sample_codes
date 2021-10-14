package service;

import user.springbook.dao.UserDao;
import user.springbook.domain.User;

import java.util.ArrayList;
import java.util.List;

class MockerUserDao implements UserDao {
    private List<User> users;
    private List<User> updated = new ArrayList<>();

    public MockerUserDao(List<User> users) {
        this.users = users;
    }

    public List<User> getUpdated() {
        return updated;
    }

    @Override
    public void add(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User get(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User user) {
        updated.add(user);
    }

}
