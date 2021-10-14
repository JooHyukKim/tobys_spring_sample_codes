package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.springbook.TestTobysApplicationContext;
import user.springbook.TobysApplicationContext;
import user.springbook.service.UserService;

import java.lang.reflect.Proxy;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestTobysApplicationContext.class)
public class PointCutTest {

    @Autowired
    UserService testUserService;

    @Test
    public void advisorProAUtoProxy() {
        Assertions.assertTrue(testUserService instanceof Proxy);
    }

}
