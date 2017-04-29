package com.weiCommity;

import com.weiCommity.Util.StaticVar;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import sun.misc.IOUtils;

import javax.annotation.Resources;
import java.io.IOException;

/**
 * Created by uryuo on 17/4/11.
 */
@Configuration
@ComponentScan(basePackages="com.weiCommity")
@EnableWebMvc
public class SpringConfigration extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/*.png").addResourceLocations("classpath:/pic/");
        registry.addResourceHandler("/fileSpace/**").addResourceLocations("classpath:/FileSpace/");
    }
}
