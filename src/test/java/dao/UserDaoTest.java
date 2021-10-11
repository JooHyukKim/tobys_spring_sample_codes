package dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import user.springbook.dao.*;
import user.springbook.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        annotationContext();
        xmlContext();
    }

    private static void xmlContext() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        List<User> userlist = dao.getAll();

        User user = new User();
        user.setId("user" + userlist.size());
        user.setName("user" + userlist.size());
        user.setPassword("1234");

        dao.add(user);

        User userAdded = dao.get(user.getId());

        System.out.println("---------------------------");
        System.out.println("testing xmlType Context");
        System.out.println(user);
    }


    public static void annotationContext() throws SQLException, ClassNotFoundException {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("UserDao", UserDao.class);

        List<User> userlist = dao.getAll();

        User user = new User();
        user.setId("user" + userlist.size());
        user.setName("user" + userlist.size());
        user.setPassword("1234");

        dao.add(user);

        User userAdded = dao.get(user.getId());

        System.out.println("---------------------------");
        System.out.println("testing annotationtype Context");
        System.out.println(user);

    }
}
