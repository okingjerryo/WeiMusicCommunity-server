package com.weiCommity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by uryuo on 17/4/16.
 */
//要继承原先web.xml文件的左右就需要实现Spring中 webApplicationInitializer的Startup方法
public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //这里是初始化Spring管理Bean工厂的方法，在Controller层编写时不要再使用老的获取Context方法再次初始化Bean。会抛出异常
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        //将书写的SpringConfig类进行注册
        ctx.register(SpringConfigration.class);
        ctx.setServletContext(servletContext);
        //设置SpringMVC Servlet跳页面的方式为转发
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",new DispatcherServlet(ctx));
        //servlet生效的方式为浏览器访问"/"也就是根下的访问路径
        servlet.addMapping("/");
        //在软件运行的一开始就让这个设置生效
        servlet.setLoadOnStartup(1);
    }
}
