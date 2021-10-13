package example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.springbook.example.Message;
import user.springbook.example.MessageFactoryBean;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
public class FactoryBeanTest {
    @Autowired
    ApplicationContext context;

    @Test
    public void getMessageFromFactoryBean() {
        Object message = context.getBean("message");
        assertEquals(Message.class, message.getClass());
        assertEquals("Factory Bean", ((Message) message).getText());

    }

    @Test
    public void getFactoryBean() {
        Object message = context.getBean("&message");
        assertEquals(MessageFactoryBean.class, message.getClass());

    }
}
