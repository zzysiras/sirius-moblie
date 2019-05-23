package com.cesaba.siriusmobliemain.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<String, String>();

        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123123");
        initParams.put("allow","");  //默认允许所有访问

        return bean;

    }

    @Bean      //配置web监控filter
    public FilterRegistrationBean webstatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initparams = new HashMap<String, String>();
        initparams.put("exclusions","/druid/*");  //允许以下访问

        bean.setInitParameters(initparams);

        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;

    }


}
