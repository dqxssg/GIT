package com.example.boot05web01.config;

import com.example.boot05web01.bean.Pet;
import com.example.boot05web01.converter.JiConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 方法一：
 * 需要链接接口implements WebMvcConfigurer
 *
 * @Override public void configurePathMatch(PathMatchConfigurer configurer) {
 * UrlPathHelper urlPathHelper = new UrlPathHelper();
 * //不移除，后面的内容，矩阵变量功能就可以生效
 * urlPathHelper.setRemoveSemicolonContent(false);
 * configurer.setUrlPathHelper(urlPathHelper);
 * }
 * 方法二：
 * 重写
 * @Bean public WebMvcConfigurer webMvcConfigurer() {
 * return new WebMvcConfigurer() {
 * @Override public void configurePathMatch(PathMatchConfigurer configurer) {
 * WebMvcConfigurer.super.configurePathMatch(configurer);
 * UrlPathHelper urlPathHelper = new UrlPathHelper();
 * urlPathHelper.setRemoveSemicolonContent(false);
 * configurer.setUrlPathHelper(urlPathHelper);
 * }
 * };
 * }
 */
@Configuration(proxyBeanMethods = false)
public class WebConfig /*implements WebMvcConfigurer*/ {
    /**
     * 更改请求名
     *
     * @return
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("_m");
        return methodFilter;
    }

    /**
     * WebMvcConfigurer定制化SpringMVC的功能
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            /**
             *自定义内容协商策略
             */
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                HashMap<String, MediaType> mediaTypes = new HashMap<>();
                mediaTypes.put("json", MediaType.APPLICATION_JSON);
                mediaTypes.put("xml", MediaType.APPLICATION_XML);
                mediaTypes.put("Ji", MediaType.parseMediaType("application/ji"));
                //指定支持解析那些参数对应的那些媒体类型
                ParameterContentNegotiationStrategy parameterContentNegotiationStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
                //请求头
                HeaderContentNegotiationStrategy headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();
                configurer.strategies(Arrays.asList(parameterContentNegotiationStrategy, headerContentNegotiationStrategy));
                WebMvcConfigurer.super.configureContentNegotiation(configurer);
            }

            /**
             * 给容器中添加一个WebMvcConfigurer
             */
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                //添加自定义
                converters.add(new JiConverter());
            }

            /**
             * 矩阵变量
             */
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                WebMvcConfigurer.super.configurePathMatch(configurer);
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                //不移除，后面的内容，矩阵变量功能就可以生效
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }

            /**
             * 将小猫,5转换为两个数据
             * @param registry
             */
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>() {
                    @Override
                    public Pet convert(String source) {
                        //小猫, 5
                        if (!StringUtils.isEmpty(source)) {
                            Pet pet = new Pet();
                            String[] split = source.split(",");
                            pet.setName(split[0]);
                            pet.setAge(split[1]);
                            return pet;
                        }
                        return null;
                    }
                });
            }
        };
    }

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        UrlPathHelper urlPathHelper = new UrlPathHelper();
//        //不移除，后面的内容，矩阵变量功能就可以生效
//        urlPathHelper.setRemoveSemicolonContent(false);
//        configurer.setUrlPathHelper(urlPathHelper);
//    }
}