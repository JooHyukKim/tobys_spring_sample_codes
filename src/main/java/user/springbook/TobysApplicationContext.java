package user.springbook;

import com.mysql.cj.jdbc.Driver;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import user.springbook.dao.UserDao;
import user.springbook.dao.UserDaoJdbc;
import user.springbook.service.*;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "user.springbook")
public class TobysApplicationContext {

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
        dataSource.setDriverClass(Driver.class);
        dataSource.setUsername("root");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

    @Bean
    public TransactionAdvice transactionAdvice() {
        TransactionAdvice txAdivce = new TransactionAdvice();
        txAdivce.setTransactionManager(transactionManager());

        return txAdivce;
    }


}
