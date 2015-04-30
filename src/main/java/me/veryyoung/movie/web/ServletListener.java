package me.veryyoung.movie.web;

import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 * <p/>
 * Created by veryyoung on 2015/3/12.
 */

@WebListener
public class ServletListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContextUtils
                .getRequiredWebApplicationContext(servletContextEvent.getServletContext())
                .getAutowireCapableBeanFactory()
                .autowireBean(this);

        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("appName", "VY电影");
        context.setAttribute("qiniu", "http://7xia3v.com1.z0.glb.clouddn.com/");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
