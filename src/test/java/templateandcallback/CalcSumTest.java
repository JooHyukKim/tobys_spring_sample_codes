package templateandcallback;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import user.springbook.templateandcallback.Calculator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalcSumTest {
    Calculator calculator;
    String numFilePath;

    @BeforeAll
    public void setUp() {
        this.calculator = new Calculator();
        this.numFilePath = Paths
                .get("/Users/vince/dev/3Kims/github.com/tobys_spring_sample_codes/src/test/resources/sum-numbers.txt")
                .toString();
    }


    @Test
    public void sumOfNumbers() throws IOException {
        int sum = this.calculator.calcSum(numFilePath);
        Assertions.assertEquals(15, sum);
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        int product = this.calculator.calcMultiply(numFilePath);
        Assertions.assertEquals(120, product);

    }

}
