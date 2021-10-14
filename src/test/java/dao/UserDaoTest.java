package dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.springbook.TestTobysApplicationContext;
import user.springbook.TobysApplicationContext;
import user.springbook.dao.UserDao;
import user.springbook.domain.Level;
import user.springbook.domain.User;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 바로 아래 주석처리된 것들만 봐도 알수 있는것.
 * 애플리케이션의 도움 없이 구체적인 내용으로 DI를 구현하기 위해
 *
 * @BeforeEach 메소드에 적용.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestTobysApplicationContext.class})
@ActiveProfiles("test")
public class UserDaoTest {


    @Autowired
    ApplicationContext context;

    @Autowired
    UserDao dao;

    User user1;
    User user2;
    User user3;

    @BeforeEach
    public void setUpEach() {
        this.user1 = new User("user1", "user1", "1234", Level.BASIC, 1, 0, "beanskobe@gmail.com");
        this.user2 = new User("user2", "user2", "1234", Level.SILVER, 55, 10, "beanskobe@gmail.com");
        this.user3 = new User("user3", "user3", "1234", Level.GOLD, 100, 40, "beanskobe@gmail.com");

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
        assertEquals(user1.getEmail(), user2.getEmail());
        return true;
    }

    @Test
    public void getCount() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        dao.add(user1);
        assertEquals(dao.getCount(), 1);
        dao.add(user2);
        assertEquals(dao.getCount(), 2);
        dao.add(user3);
        assertEquals(dao.getCount(), 3);

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

        dao.add(user1);
        Assertions.assertEquals(1, dao.getAll().size());
        isTwoUsersEqual(user1, dao.get(user1.getId()));

        dao.add(user2);
        Assertions.assertEquals(2, dao.getAll().size());
        isTwoUsersEqual(user2, dao.get(user2.getId()));

        dao.add(user3);
        Assertions.assertEquals(3, dao.getAll().size());
        isTwoUsersEqual(user3, dao.get(user3.getId()));


        List<User> finalList = dao.getAll();
        Assertions.assertTrue(finalList.size() == 3);
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

        User userToUpdate = new User();
        userToUpdate.setId(user1.getId());
        userToUpdate.setLogin(user1.getLogin() + 2);
        userToUpdate.setRecommend(user1.getRecommend() + 321);
        userToUpdate.setLevel(Level.GOLD);
        userToUpdate.setPassword(user1.getPassword() + "321");
        userToUpdate.setName(user1.getName() + 321);
        userToUpdate.setEmail(user1.getEmail() + 1321);

        dao.update(userToUpdate);

        User updatedUser1 = dao.get(user1.getId());
        assertNotEquals(user1.getEmail(), updatedUser1.getEmail());
        assertNotEquals(user1.getLogin(), updatedUser1.getLogin());
        assertNotEquals(user1.getLevel(), updatedUser1.getLevel());
        assertNotEquals(user1.getPassword(), updatedUser1.getPassword());
        assertNotEquals(user1.getName(), updatedUser1.getName());

        User user2same = dao.get(user2.getId());
        isTwoUsersEqual(user2, user2same);

    }


}
