package com.weiCommity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by uryuo on 17/4/11.
 */
//使Spring认识到这是一个配置文件类
@Configuration
//利用扫描包的注解使Spring在初始化时自动注册相关Bean 在这里是扫面全部包的Autowired注解的变量
@ComponentScan(basePackages="com.weiCommity")
//要使用SpringMvc功能
@EnableWebMvc

//由于这个Spring类需要配置以前在web.xml的属性，所以需要继承WebMvcConfiguerAdapter的接口类
public class SpringConfigration extends WebMvcConfigurerAdapter {

    //注册跳页面的Servlet，即访问相关连接后会自动跳转到指定的页面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/signin").setViewName("/signin");
        registry.addViewController("/fileUpload").setViewName("/file");
    }

    //让SpringMVC可以直接Controller跳转页面时的String返回值，以及对上面方法viewName属性添加前缀和后缀
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //添加的前缀为tomcat的view文件夹路径
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        //添加的后缀是jsp页面格式
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    //使服务端开启对文件上传的支持
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //设置最大单文件为200
        multipartResolver.setMaxUploadSize(200000000);
        return multipartResolver;
    }
    //将服务端指定文件夹下的内容暴露出来，使用与客户端直接请求服务器的文件
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //web页面用的相关js和css需要能直接访问
        registry.addResourceHandler("/assat/**").addResourceLocations("classpath:/assat/");
        registry.addResourceHandler("/pic/*.png").addResourceLocations("classpath:/pic/");
        //将客户端储存文件的位置暴露出来，方便客户端可以直接请求数据
        registry.addResourceHandler("/fileSpace/**").addResourceLocations("classpath:/FileSpace/");
    }
}
