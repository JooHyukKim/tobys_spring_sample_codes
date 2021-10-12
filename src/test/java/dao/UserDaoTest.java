package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.springbook.dao.UserDao;
import user.springbook.domain.Level;
import user.springbook.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 바로 아래 주석처리된 것들만 봐도 알수 있는것.
 * 애플리케이션의 도움 없이 구체적인 내용으로 DI를 구현하기 위해
 *
 * @BeforeEach 메소드에 적용.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private DataSource dataSource;
    UserDao dao;

    User user1;
    User user2;
    User user3;

    @BeforeEach
    public void setUpEach() {
        dao = context.getBean("userDao", UserDao.class);
        this.user1 = new User("user1", "user1", "1234", Level.BASIC, 1, 0);
        this.user2 = new User("user2", "user2", "1234", Level.SILVER, 55, 10);
        this.user3 = new User("user3", "user3", "1234", Level.GOLD, 100, 40);

    }


    @Test
    public void xmlContextTest() throws SQLException, ClassNotFoundException {
        dao.deleteAll();


        assertEquals(dao.getCount(), 0);

        dao.add(user1);

        User userAdded = dao.get(user1.getId());

        assertTrue(isTwoUsersEqual(user1, userAdded));

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("성공 : testing xmlType Context");
        System.out.println(user1);
        System.out.println(userAdded);

        testDeleteAndCount(dao);
    }

    private void testDeleteAndCount(UserDao dao) throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        dao.add(user1);

        User user2 = dao.get(user1.getId());

        assertTrue(isTwoUsersEqual(user1, user2));

        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("성공 : testing delete And Count");

    }

    private boolean isTwoUsersEqual(User user1, User user2) {
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getName(), user2.getName());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getLevel(), user2.getLevel());
        assertEquals(user1.getLogin(), user2.getLogin());
        assertEquals(user1.getRecommend(), user2.getRecommend());
        return true;
    }

    @Test
    public void getCount() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        for (int i = 1; i < 6; i++) {
            dao.add(
                    new User("user" + i, "user" + i, "1234", Level.BASIC, 1, 0)
            );
            assertEquals(dao.getCount(), i);
        }

        System.out.println("getCountTest success");

        dao.deleteAll();
    }

    @Test
    public void get() {
        if (dao.getCount() != 0) {
            dao.deleteAll();
        }
        dao.add(user1);
        dao.add(user2);

        assertEquals(dao.getCount(), 2);

        User user1Get = dao.get(user1.getId());
        assertTrue(isTwoUsersEqual(user1, user1Get));

        User user2Get = dao.get(user2.getId());
        assertTrue(isTwoUsersEqual(user2, user2Get));
    }

    @Test
    public void getFailure() {

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.get("feiawfhoeaho");
        });
    }

    @Test
    public void getUserFailure() {

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.get("fehoaifhoaevhoa");
        });
    }

    @Test
    public void getUser() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertEquals(0, dao.getCount());

        dao.add(user1);

        User user2 = dao.get(user1.getId());

        assertTrue(isTwoUsersEqual(user1, user2));
    }

    @Test
    public void findALl() throws SQLException, ClassNotFoundException {
        dao.deleteAll();

        List<User> userlist = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            User user = new User("user" + i, "user" + i, "user" + i, user1.getLevel(), user1.getLogin(), user1.getRecommend());
            userlist.add(user);
            dao.add(user);
            Assertions.assertEquals(
                    i
                    , dao.getAll().size()
            );
            isTwoUsersEqual(user, dao.get(user.getId()));
        }

        List<User> finalList = dao.getAll();
        Assertions.assertTrue(finalList.size() == 11);
    }

    @Test
    public void duplicateUserId() {
        dao.deleteAll();
        dao.add(user1);
        Assertions.assertThrows(DuplicateKeyException.class, () -> {
            dao.add(user1);
        });

    }

    /**
     * DataSource를 활용해 SQLException에서 직접 DuplicateKeyException으로 전환하는 기능을 확인해보는 학습 테스트다.
     */
    @Test
    public void sqlExceptionTranslate() {
        dao.deleteAll();
        assertThrows(DataAccessException.class, () -> {
            dao.add(user1);
            dao.add(user1);
        });
    }

    @Test
    public void update() {
        dao.deleteAll();

        dao.add(user1);
        dao.add(user2);

        user1.setLogin(333);
        user1.setRecommend(321);
        user1.setLevel(Level.GOLD);
        user1.setPassword("4321");
        user1.setName("주혀킴");

        dao.update(user1);

        User updatedUser = dao.get(user1.getId());
        isTwoUsersEqual(user1, updatedUser);

        User user2same = dao.get(user2.getId());
        isTwoUsersEqual(user2, user2same);

    }


}
