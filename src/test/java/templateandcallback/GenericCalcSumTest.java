package templateandcallback;

import com.sun.tools.javac.jvm.Gen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import user.springbook.templateandcallback.Calculator;
import user.springbook.templateandcallback.GenericCaculator;

import java.io.IOException;
import java.nio.file.Paths;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenericCalcSumTest {
    GenericCaculator calculator;
    String numFilePath;

    @BeforeAll
    public void setUp() {
        this.calculator = new GenericCaculator();
        this.numFilePath = Paths
                .get("/Users/vince/dev/3Kims/github.com/tobys_spring_sample_codes/src/test/resources/sum-numbers.txt")
                .toString();
    }


    @Test
    public void addlines() throws IOException {
        String result = this.calculator.addAsString(numFilePath);
        Assertions.assertEquals("12345", result);
    }
}
