package dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.springbook.dao.CountingConnectionMaker;
import user.springbook.dao.CountingDaoFactory;
import user.springbook.dao.UserDao;
import user.springbook.domain.User;

import java.sql.SQLException;

public class UserDaoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        runDB();
    }


    public static void runDB() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        UserDao dao = context.getBean("UserDao", UserDao.class);
        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);

        User user = new User();
        user.setId("user10");
        user.setName("Kim Joo Hyuk");
        user.setPassword("1234");

//        dao.add(user);
//        System.out.println("Connection counter : " + ccm.getCounter());
//        System.out.println(dao);

        dao.get(user.getId());
        System.out.println("Connection counter : " + ccm.getCounter());
        System.out.println(dao);
        dao.get(user.getId());
        System.out.println("Connection counter : " + ccm.getCounter());
        System.out.println(dao);
        dao.get(user.getId());
        System.out.println("Connection counter : " + ccm.getCounter());
        System.out.println(dao);
        dao.get(user.getId());
        System.out.println("Connection counter : " + ccm.getCounter());
        System.out.println(dao);
        dao.get(user.getId());
        System.out.println("Connection counter : " + ccm.getCounter());
        System.out.println(dao);
        dao.get(user.getId());
        System.out.println("Connection counter : " + ccm.getCounter());
        System.out.println(dao);


    }
}
