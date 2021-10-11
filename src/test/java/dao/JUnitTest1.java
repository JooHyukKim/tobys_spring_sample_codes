package dao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.*;

/**
 * JUnit이 @Test 메소드를 실행할 때마다 새로운 객체를 만들어내는지 체크
 */
public class JUnitTest1 {
    static JUnitTest1 testObj;
    static HashSet<JUnitTest1> set = new HashSet<>();

    @Test
    public void test1() {
        Assert.assertThat(this, is(not(testObj)));
        testObj = this;
    }

    @Test
    public void test2() {
        Assert.assertThat(this, is(not(sameInstance(testObj))));
        Assert.assertThat(set, not(hasItem(this)));
        set.add(this);
        set.stream().forEach(System.out::println);
    }

    @Test
    public void test3() {
        Assert.assertThat(this, is(not(sameInstance(testObj))));
        Assert.assertThat(set, not(hasItem(this)));
        set.add(this);
        set.stream().forEach(System.out::println);
    }
}
