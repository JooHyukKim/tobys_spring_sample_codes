package user.springbook.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    // 이후 MessageDao AccountDao 등의 구현에서 중복코드를 피하고자.
    @Bean
    public UserDao UserDao() {
        UserDao userDao = new UserDao(this.connectionMaker());
        return userDao;
    }

    @Bean
    ConnectionMaker connectionMaker() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        return connectionMaker;
    }
}
