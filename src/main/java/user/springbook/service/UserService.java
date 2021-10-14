package user.springbook.service;

import org.springframework.transaction.annotation.Transactional;
import user.springbook.domain.User;

import java.sql.SQLException;
import java.util.List;

@Transactional
public interface UserService {
    void add(User user);

    void deleteAll();

    void update(User user);

    void upgradeLevels() throws SQLException;

    @Transactional(readOnly = true)
    User get(String id);


    @Transactional(readOnly = true)
    List<User> getAll();

}
