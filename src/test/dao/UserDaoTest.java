package test.dao;

import main.user.springbook.dao.UserDao;
import main.user.springbook.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("vince");
        user.setName("Kim Joo Hyuk");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getId() + " 조회 결과");
        System.out.println(user2.getPassword());
        System.out.println(user2.getId());
    }
}
