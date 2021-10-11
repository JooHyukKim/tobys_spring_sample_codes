package dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.springbook.dao.DaoFactory;
import user.springbook.dao.UserDao;
import user.springbook.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = context.getBean("UserDao", UserDao.class);

        User user = new User();
        user.setId("user6");
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
