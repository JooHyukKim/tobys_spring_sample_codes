package user.springbook.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.mysql.cj.jdbc.Driver;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao UserDao() throws ClassNotFoundException {
        UserDao userDao = new UserDao();
        userDao.setDataSource(this.datasource());
        return userDao;
    }

    @Bean
    public DataSource datasource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/mysql");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }

}
