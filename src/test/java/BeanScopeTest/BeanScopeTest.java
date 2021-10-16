package BeanScopeTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashSet;
import java.util.Set;

public class BeanScopeTest {

    @Test
    public void singletonTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                SingletonBean.class, SingletonClientBean.class
        );
        Set<SingletonBean> beans = new HashSet<>();

        beans.add(ac.getBean(SingletonBean.class));
        beans.add(ac.getBean(SingletonBean.class));
        Assertions.assertEquals(beans.size(), 1);

        beans.add(ac.getBean(SingletonClientBean.class).bean1);
        beans.add(ac.getBean(SingletonClientBean.class).bean2);
        Assertions.assertEquals(beans.size(), 1);

    }

    static class SingletonBean {

    }

    static class SingletonClientBean {

        @Autowired
        SingletonBean bean1;

        @Autowired
        SingletonBean bean2;

    }
}
