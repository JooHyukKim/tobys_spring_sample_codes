package dao;

import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.springbook.dao.DaoFactory;
import user.springbook.dao.UserDao;
import user.springbook.domain.User;

import java.lang.reflect.Executable;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ContextConfiguration 을 사용하여 컨텍스트를 아노테이션 방식으로 생성.
 * @ApplicationContext도 결국 빈이다. @ApplicationContext는 BeanFactory를 확장한 클래스로 스프링에서 Bean 으로 관리하기 때문에
 *          @Autowiring 가능
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class JUnit1_UserDaoTest {

    @Autowired
    private ApplicationContext context;
    private UserDao dao;


    @BeforeEach
    public void setUpIndividually(){
        dao = context.getBean("UserDao", UserDao.class);
        System.out.println("context    :   "+ this.context);
        System.out.println("dao   :   " + this.dao);
        System.out.println("TestClass   :   " + this);
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

        User userAdded = dao.get(user.getId());

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
        user.setId("user" + dao.getAll().size());
        user.setPassword("1234");

        dao.add(user);

        User user2 = dao.get(user.getId());

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

    @Test
    public void annotationContextTest() throws SQLException, ClassNotFoundException {

        dao.deleteAll();


        assertEquals(dao.getCount(), 0);
        User user = new User();
        user.setId("user" + 1);
        user.setName("user" + 1);
        user.setPassword("1234");

        dao.add(user);

        User userAdded = dao.get(user.getId());

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
    public void get() throws SQLException, ClassNotFoundException {
        if (dao.getCount() != 0) {
            dao.deleteAll();
        }
        User user1 = new User("user1", "name1", "password1");
        User user2 = new User("user2", "name2", "password2");
        dao.add(user1);
        dao.add(user2);

        assertEquals(dao.getCount(), 2);

        User user1Get = dao.get(user1.getId());
        assertTrue(isTwoUsersEqual(user1, user1Get));

        User user2Get = dao.get(user2.getId());
        assertTrue(isTwoUsersEqual(user2, user2Get));
    }

    @Test
    public void getUserFailure() throws SQLException {

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        Assertions.assertThrows(SQLException.class, () -> {
            dao.get("XZZZZ");
        });


    }


}
