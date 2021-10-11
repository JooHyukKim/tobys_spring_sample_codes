package dao;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * JUnit이 @Test 메소드를 실행할 때마다 새로운 객체를 만들어내는지 체크
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class JUnitTest2 {
    @Autowired
    ApplicationContext context;

    static Set<JUnitTest2> testObjects = new HashSet<>();
    static ApplicationContext contextObj = null;


    @Test
    public void test1() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
    }

    @Test
    public void test2() {
        Assertions.assertTrue(contextObj == null || contextObj == this.context);
        context = this.context;
    }

    @Test
    public void test3() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

        Assertions.assertTrue(contextObj == null || contextObj == this.context);
        contextObj = this.context;
    }

    @Test
    public void test4() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

        assertThat(contextObj, either(is(nullValue())).or(is(this.context)));
        contextObj = this.context;
    }

    @Test
    public void test5() {
    }

}
