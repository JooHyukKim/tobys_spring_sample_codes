package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Description;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.springbook.dao.UserDao;
import user.springbook.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 바로 아래 주석처리된 것들만 봐도 알수 있는것.
 * 애플리케이션의 도움 없이 구체적인 내용으로 DI를 구현하기 위해
 *
 * @BeforeEach 메소드에 적용.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private ApplicationContext context;
    UserDao dao;

    @BeforeEach
    public void setUpEach() {
        dao = context.getBean("UserDao", UserDao.class);
    }

    @Test
    public void xmlContextTest() throws SQLException, ClassNotFoundException {
        dao.deleteAll();


        assertEquals(dao.getCount(), 0);
        User user = new User();
        user.setId("user" + 1);
        user.setName("user" + 1);
        user.setPassword("1234");

        dao.add(user);

        User userAdded = dao.getUser(new User(user.getId()));

        assertTrue(isTwoUsersEqual(user, userAdded));

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("성공 : testing xmlType Context");
        System.out.println(user);
        System.out.println(userAdded);

        testDeleteAndCount(dao);
    }

    private void testDeleteAndCount(UserDao dao) throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        User user = new User();
        user.setName("김주혁");
        user.setId("user" + dao.findAll().size());
        user.setPassword("1234");

        dao.add(user);

        User user2 = dao.getUser(user);

        assertTrue(isTwoUsersEqual(user, user2));

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("성공 : testing delete And Count");

    }

    private boolean isTwoUsersEqual(User user1, User user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getName(), user2.getName());
        assertEquals(user1.getPassword(), user2.getPassword());
        return true;
    }

    /**/
    @Test
    public void annotationContextTest() throws SQLException, ClassNotFoundException {

        dao.deleteAll();


        assertEquals(dao.getCount(), 0);
        User user = new User();
        user.setId("user" + 1);
        user.setName("user" + 1);
        user.setPassword("1234");

        dao.add(user);

        User userAdded = dao.getUser(user);

        assertTrue(isTwoUsersEqual(user, userAdded));

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("성공 : testing AnnotationType Context");
        System.out.println(user);
        System.out.println(userAdded);
    }

    @Test
    public void getCount() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        for (int i = 1; i < 6; i++) {
            dao.add(
                    new User("user" + i, "user" + i, "1234")
            );
            assertEquals(dao.getCount(), i);
        }

        System.out.println("getCountTest success");

        dao.deleteAll();
    }

    @Test
    public void getCountSimple() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertEquals(0, dao.getCountSimple());

        for (int i = 1; i < 6; i++) {
            dao.add(
                    new User("user" + i, "user" + i, "1234")
            );
            assertEquals(dao.getCountSimple(), i);
        }

        System.out.println(" getCountSimple success");
        dao.deleteAll();
    }


    @Test
    public void get()  {
        if (dao.getCount() != 0) {
            dao.deleteAll();
        }
        User user1 = new User("user1", "name1", "password1");
        User user2 = new User("user2", "name2", "password2");
        dao.add(user1);
        dao.add(user2);

        assertEquals(dao.getCount(), 2);

        User user1Get = dao.getUser(user1);
        assertTrue(isTwoUsersEqual(user1, user1Get));

        User user2Get = dao.getUser(user2);
        assertTrue(isTwoUsersEqual(user2, user2Get));
    }

    @Test
    public void getFailure() {

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.getUser(new User("feiawfhoeaho"));
        });
    }

    @Test
    public void addUserFailure() {

    }

    @Test
    public void getUserFailure() {

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.getUser(new User("fehoaifhoaevhoa", "user01", "user01"));
        });
    }

    @Test
    public void getUser() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertEquals(0, dao.getCountSimple());

        User user1 = new User("user01", "user01", "user01");

        dao.add(user1);

        User user2 = dao.getUser(user1);

        assertTrue(isTwoUsersEqual(user1, user2));
    }

    @Test
    public void findALl() throws SQLException, ClassNotFoundException {
        dao.deleteAll();

        List<User> userlist = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            User user = new User("user" + i, "user" + i, "user" + i);
            userlist.add(user);
            dao.add(user);
            Assertions.assertEquals(
                    i
                    , dao.findAll().size()
            );
            isTwoUsersEqual(user, dao.getUser(user));
        }

        List<User> finalList = dao.findAll();
        Assertions.assertTrue(finalList.size() == 11);
    }
}
