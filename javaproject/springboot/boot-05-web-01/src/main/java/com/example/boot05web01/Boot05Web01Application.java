package com.example.boot05web01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 只要静态路径下的static或public或resources或META-INF/resources，访问只需要当前根路径/+静态资源名
 * 原理：静态映射/**
 * 请求进来先去找controller，如果controller不能处理去找静态资源
 * <p>
 * 静态资源配置原理
 * SpringBoot启动默认加载xxxAutoConfiguration类（自动配置类）
 * SpringMVC功能的自动配置类WebMvcAutoConfiguration
 * <p>
 * 静态资源配置原理
 * SpringBoot启动默认加载xxxAutoConfiguration类（自动配置类）
 * SpringMVC功能的自动配置类WebMvcAutoConfiguration
 * <p>
 * HandlerMapping：处理器映射，保存了每一个Handler能处理那些请求
 * <p>
 * SpringMVC支持的返回值
 * View
 * Model
 * ModelAndView
 * ResponseEntity
 * ResponseBodyEntity
 * StreamimgResponseBody
 * HttpEntity
 * HttpHeaders
 * Callable
 * DeferredResilt
 * ListenableFuture
 * CompletionStage
 * WebAsyncTage
 * 有@ModelAttribute
 *
 * @ResponseBody注解--->RequestResponBodyMethodProcessor
 */

/**
 * 静态资源配置原理
 * SpringBoot启动默认加载xxxAutoConfiguration类（自动配置类）
 * SpringMVC功能的自动配置类WebMvcAutoConfiguration
 */

/**
 * HandlerMapping：处理器映射，保存了每一个Handler能处理那些请求
 */

/**
 * SpringMVC支持的返回值
 * View
 * Model
 * ModelAndView
 * ResponseEntity
 * ResponseBodyEntity
 * StreamimgResponseBody
 * HttpEntity
 * HttpHeaders
 * Callable
 * DeferredResilt
 * ListenableFuture
 * CompletionStage
 * WebAsyncTage
 * 有@ModelAttribute
 * @ResponseBody注解--->RequestResponBodyMethodProcessor
 */

/**
 * HttpMessageConverter
 * 看是否支持将此类Class类型的对象，转为MediAtype类型的数据
 */

@SpringBootApplication
public class Boot05Web01Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot05Web01Application.class, args);
    }

}
