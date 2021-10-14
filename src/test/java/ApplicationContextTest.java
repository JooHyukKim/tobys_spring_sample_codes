import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.springbook.TestTobysApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestTobysApplicationContext.class)
@ActiveProfiles("test")
public class ApplicationContextTest {


    @Autowired
    DefaultListableBeanFactory bf;

    @Test
    public void beans() {
        System.out.println("-------------------------------------------------------");
        for (String n : bf.getBeanDefinitionNames()) {
            System.out.println(n);
        }
        System.out.println("-------------------------------------------------------");
    }
}
