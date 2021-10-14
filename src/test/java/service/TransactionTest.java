package service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.springbook.service.TestUserService;
import user.springbook.service.UserService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
public class TransactionTest {

}
