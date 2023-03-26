package com.example.boot05webadmin;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 断言
 * 这些断言方法都是org.junit.jupiter.api.Assertions的静态方法
 * 断言前面的失败后面的就不会执行
 */
//@RunWith()
@SpringBootTest
@DisplayName("junit5功能测试类")
public class Junit5Test {
    /**
     * 测试前置条件
     * 前置条件（assumptions【假设】）类似于断言
     * 不同之处在于不满足的断言会使得测试方法失败
     * 而不满足的前置条件只会使得测试方法的执行终止
     * 前置条件可以看成是测试方法执行的前提，当该前提不满足时，就没有继续执行的必要。
     */
    @DisplayName("测试前置条件")
    @Test
    void testassumptions(){
        Assumptions.assumeTrue(true,"结果不是true");
        System.out.println("是true");

    }





    /**
     * assertEquals	判断两个对象或两个原始类型是否相等
     * assertNotEquals	判断两个对象或两个原始类型是否不相等
     * assertSame	判断两个对象引用是否指向同一个对象
     * assertNotSame	判断两个对象引用是否指向不同的对象
     * assertTrue	判断给定的布尔值是否为 true
     * assertFalse	判断给定的布尔值是否为 false
     * assertNull	判断给定的对象引用是否为 null
     * assertNotNull	判断给定的对象引用是否不为 null
     */
    @DisplayName("测试就按单断言")
    @Test
    void testSimpleAssertions() {
        int car = car(3, 2);
        assertEquals(5, car, "业务逻辑计算失败");
        Object o = new Object();
        Object o1 = new Object();
        assertSame(o, o1, "两个不一样");
    }

    int car(int i, int j) {
        return i + j;
    }

    /**
     * 数组断言
     */
    @DisplayName("数组断言")
    @Test
    public void array() {
        assertArrayEquals(new int[]{1, 3}, new int[]{1, 2}, "不一样两个数组");
    }

    /**
     * 组合断言
     */
    @Test
    @DisplayName("组合断言")
    public void all() {
        assertAll("Math",
                () -> assertEquals(2, 1 + 1, "两个不一样"),
                () -> assertTrue(1 > 0,"不一样")
        );
    }

    /**
     * 异常断言
     */
    @Test
    @DisplayName("异常断言")
    public void exceptionTest() {
        ArithmeticException exception = Assertions.assertThrows(
                //扔出断言异常
                ArithmeticException.class, () -> System.out.println(1 % 0));

    }

    /**
     * 超时测试
     * Assertions.assertTimeout() 为测试方法设置了超时时间
     */
    @Test
    @DisplayName("超时测试")
    public void timeoutTest() {
        //如果测试方法时间超过1s将会异常
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
    }

    /**
     * 快速失败
     * 通过 fail 方法直接使得测试失败
     */
    @Test
    @DisplayName("快速失败")
    public void shouldFail() {
        fail("This should fail");
    }

    @Resource
    JdbcTemplate jdbcTemplate;

    /**
     * 为测试类或者测试方法设置展示名称
     */
    @DisplayName("测试displayname注解")
    @Test
    void testDisplayName() {
        System.out.println("DisplayName");
    }

    /**
     * @Disabled 表示禁用该方法
     */
    @Disabled
    @DisplayName("test")
    @Test
    void test() {
        System.out.println("test");
    }

    /**
     * @RepeatedTest 表示重复测试几次
     */

    @RepeatedTest(5)
    @Test
    void test5() {
        System.out.println("test5");
    }

    /**
     * 规定方法超时时间，超出时间测试出异常
     */
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void testTimeout() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 表示在每个单元测试之前执行
     */
    @BeforeEach
    void testBeforeEach() {
        System.out.println("BeforeEach");
    }

    /**
     * 表示在每个单元测试之后执行
     */
    @AfterEach
    void testAdterEach() {
        System.out.println("AfterEach");
    }

    /**
     * 表示在所有单元测试之前执行
     */
    @BeforeAll
    static void testBeforeAll() {
        System.out.println("BeforeAll");
    }

    /**
     * 表示在所有单元测试之后执行
     */
    @AfterAll
    static void testAfterAll() {
        System.out.println("AfterAll");
    }
}