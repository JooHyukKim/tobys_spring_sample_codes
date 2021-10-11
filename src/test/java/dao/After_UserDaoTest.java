package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import user.springbook.dao.DaoFactory;
import user.springbook.dao.UserDao;
import user.springbook.domain.User;

import java.sql.SQLException;
import java.util.List;

public class After_UserDaoTest {

    @Test
    public void xmlContextTest() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        List<User> userlist = dao.getAll();

        User user = new User();
        user.setId("user" + userlist.size());
        user.setName("user" + userlist.size());
        user.setPassword("1234");

        dao.add(user);

        User userAdded = dao.get(user.getId());

        Assertions.assertEquals(user.getName(), userAdded.getName());
        Assertions.assertEquals(user.getPassword(), userAdded.getPassword());

        System.out.println("---------------------------");
        System.out.println("성공 : testing xmlType Context");
        System.out.println(user);
        System.out.println(userAdded);
    }


    @Test
    public void annotationContextTest() throws SQLException, ClassNotFoundException {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("UserDao", UserDao.class);

        List<User> userlist = dao.getAll();

        User user = new User();
        user.setId("user" + userlist.size());
        user.setName("user" + userlist.size());
        user.setPassword("1234");

        dao.add(user);

        User userAdded = dao.get(user.getId());

        Assertions.assertEquals(user.getName(), userAdded.getName());
        Assertions.assertEquals(user.getPassword(), userAdded.getPassword());

        System.out.println("---------------------------");
        System.out.println("성공 : testing AnnotationType Context");
        System.out.println(user);
        System.out.println(userAdded);
    }
}
