package user.springbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import user.springbook.service.DummyMailSender;
import user.springbook.service.MockUserService;
import user.springbook.service.UserService;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "user.springbook")
@Profile("test")
public class TestTobysApplicationContext {

    @Bean
    public UserService testUserService() {
        MockUserService testUserService = new MockUserService();
        return testUserService;
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }

}
