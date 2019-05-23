package com.cesaba.siriusmobliemain.config;


import com.cesaba.siriusmobliemain.tables.PageTableArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.io.File;
import java.util.List;

@Configuration
public class webMvcConfig extends WebMvcConfigurerAdapter {           //视图解析器
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("rolelogin");
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry){
                //registry.addViewController("/hello").setViewName("labWeb");
            }
        };
        return adapter;
    }

    @Bean
    public PageTableArgumentResolver tableHandlerMethodArgumentResolver() {
        return new PageTableArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tableHandlerMethodArgumentResolver());
    }

    //默认路径
    @Value("${files.path}")
    private String filesPath;

    //外部文件访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**")
                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + filesPath + File.separator);
        registry.addResourceHandler("/demo/**").addResourceLocations("http://file:///E:/files/**");
    }

    //@Value("${files.path}")
    //private String filesPath;
}