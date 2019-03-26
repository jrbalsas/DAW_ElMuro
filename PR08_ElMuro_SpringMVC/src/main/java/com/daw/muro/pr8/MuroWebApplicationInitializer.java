/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.muro.pr8;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Servlet 3.0 Programmatic configuration (instead of web.xml)
 *
 * @author jrbalsas
 */
public class MuroWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {

        //Launch and configure SpringMVC
        
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext
                = new AnnotationConfigWebApplicationContext();
        
        //Configure SpringMVC
        rootContext.register(SpringMvcConfig.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Register and map the SpringMVC dispatcher servlet
        ServletRegistration.Dynamic dispatcher
                = container.addServlet("springFrontDispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/"); //Map all urls to SpringMVC FrontDispatcher

    }
}
