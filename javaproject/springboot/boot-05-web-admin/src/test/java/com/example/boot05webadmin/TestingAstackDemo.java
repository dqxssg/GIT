package com.example.boot05webadmin;

import io.micrometer.common.util.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 嵌套测试
 * 以通过 Java 中的内部类和@Nested注解实现嵌套测试
 * 从而可以更好的把相关的测试方法组织在一起
 * 在内部类中可以使用@BeforeEach和@AfterEach注解，而且嵌套的层次没有限制。
 * <p>
 * 嵌套测试情况下，外层的@Test不能驱动内层的BeforeEach等之类的方法提前或之后运行
 * <p>
 * 嵌套测试情况下，外层的@Test不能驱动内层的BeforeEach等之类的方法提前或之后运行
 * <p>
 * 嵌套测试情况下，外层的@Test不能驱动内层的BeforeEach等之类的方法提前或之后运行
 */

/**
 * 嵌套测试情况下，外层的@Test不能驱动内层的BeforeEach等之类的方法提前或之后运行
 */

/**
 * 内层的可以驱动外层的
 */
@DisplayName("嵌套测试")
public class TestingAstackDemo {
    Stack<Object> stack;

    /**
     * 参数化测试
     * 它使得用不同的参数多次运行测试成为了可能，也为我们的单元测试带来许多便利。
     * 利用@ValueSource等注解，指定入参，我们将可以使用不同的参数进行多次单元测试，
     * 而不需要每新增一个参数就新增一个单元测试
     * @ValueSource: 为参数化测试指定入参来源，支持八大基础类以及String类型,Class类型
     * @NullSource: 表示为参数化测试提供一个null的入参
     * @EnumSource: 表示为参数化测试提供一个枚举入参
     * @CsvFileSource：表示读取指定CSV文件内容作为参数化测试入参
     * @MethodSource：表示读取指定方法的返回值作为参数化测试入参(注意方法返回需要是一个流
     */
    /**
     * @ParameterizedTest 表示参数化测试
     */
    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three"})
    @DisplayName("参数化测试1")
    public void parameterizedTest1(String string) {
        System.out.println(string);
        Assertions.assertTrue(StringUtils.isNotBlank(string));
    }

    @ParameterizedTest
    @MethodSource("method")//指定方法名
    @DisplayName("方法来源参数")
    public void testWithExplicitLocalMethodSource(String name) {
        System.out.println(name);
        Assertions.assertNotNull(name);
    }

    static Stream<String> method() {
        return Stream.of("apple", "banana");
    }

    /**
     * 嵌套测试
     */

    @Test
    @DisplayName("is instantiated with new Stack()")
    void isInstantiatedWithNew() {
        new Stack<>();
    }

    /**
     * @Nested 嵌套注解
     */
    @Nested
    @DisplayName("when new")
    class WhenNew {
        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        void throwsExceptionWhenPopped() {
            assertThrows(EmptyStackException.class, stack::pop);
        }

        @Test
        @DisplayName("throws EmptyStackException when peeked")
        void throwsExceptionWhenPeeked() {
            assertThrows(EmptyStackException.class, stack::peek);
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {
            String anElement = "an element";

            @BeforeEach
            void pushAnElement() {
                stack.push(anElement);
            }

            @Test
            @DisplayName("it is no longer empty")
            void isNotEmpty() {
                assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                assertEquals(anElement, stack.pop());
                assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                assertEquals(anElement, stack.peek());
                assertFalse(stack.isEmpty());
            }
        }
    }
}
