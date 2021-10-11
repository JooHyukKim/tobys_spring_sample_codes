package user.springbook.dao;


import com.mysql.cj.exceptions.MysqlErrorNumbers;
import jdk.nashorn.internal.scripts.JD;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import user.springbook.domain.User;
import user.springbook.exception.DuplicateUserIdException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    };

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(User user) {
        this.jdbcTemplate.update(
                "insert into users(id, name, password) values(?,?,?)"
                , user.getId()
                , user.getName()
                , user.getPassword()
        );
    }

    @Override
    public User get(String id) {
        return this.jdbcTemplate.queryForObject(
                "select * from users where id = ?"
                , new Object[]{id}
                , this.userMapper
        );
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query(
                "select * from users order by id",
                this.userMapper);
    }


    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }

    public int getCount() {
        return this.jdbcTemplate.queryForInt("select count(*) from users");
    }


}
