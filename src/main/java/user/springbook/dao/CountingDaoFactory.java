package user.springbook.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PublicKey;

@Configuration
public class CountingDaoFactory {

    // 이후 MessageDao AccountDao 등의 구현에서 중복코드를 피하고자.
    @Bean
    public UserDao UserDao() {
//        UserDao userDao = new UserDao(this.connectionMaker());
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(this.connectionMaker());
        return userDao;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
    }
}
