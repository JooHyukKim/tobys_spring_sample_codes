package user.springbook.service;

import user.springbook.domain.User;

import java.sql.SQLException;

public interface UserService {
    void add(User user);

    void upgradeLevels() throws SQLException;
}
