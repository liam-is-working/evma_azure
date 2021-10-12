package com.wrox.config.bootstrap;

import com.wrox.config.RestServletContextConfiguration;
import com.wrox.config.RootContextConfiguration;
import com.wrox.site.CorsFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

@SuppressWarnings("unused")
@Order(1)
public class FrameworkBootstrap implements WebApplicationInitializer
{
    private static final Logger log = LogManager.getLogger();

    @Override
    public void onStartup(ServletContext container) {
        log.info("Executing framework bootstrap.");

        container.getServletRegistration("default").addMapping("/resource/*");

        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContextConfiguration.class);
        container.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext webContext =
                new AnnotationConfigWebApplicationContext();
        webContext.register(RestServletContextConfiguration.class);
        ServletRegistration.Dynamic dispatcher = container.addServlet(
                "springWebDispatcher", new DispatcherServlet(webContext)
        );
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/api/*");

        FilterRegistration.Dynamic registration = container.addFilter(
                "CorsFilter", new CorsFilter()
        );
        registration.addMappingForUrlPatterns(null, false, "/*");

//        AnnotationConfigWebApplicationContext restContext =
//                new AnnotationConfigWebApplicationContext();
//        restContext.register(RestServletContextConfiguration.class);
//        DispatcherServlet restServlet = new DispatcherServlet(restContext);
//        restServlet.setDispatchOptionsRequest(true);
//        dispatcher = container.addServlet("springRestDispatcher", restServlet);
//        dispatcher.setLoadOnStartup(2);
//        dispatcher.addMapping("/services/Rest/*");

//        AnnotationConfigWebApplicationContext soapContext =
//                new AnnotationConfigWebApplicationContext();
//        soapContext.register(SoapServletContextConfiguration.class);
//        MessageDispatcherServlet soapServlet =
//                new MessageDispatcherServlet(soapContext);
//        soapServlet.setTransformWsdlLocations(true);
//        dispatcher = container.addServlet("springSoapDispatcher", soapServlet);
//        dispatcher.setLoadOnStartup(3);
//        dispatcher.addMapping("/services/Soap/*");

//        FilterRegistration.Dynamic registration = container.addFilter(
//                "preSecurityLoggingFilter", new PreSecurityLoggingFilter()
//        );
//        registration.addMappingForUrlPatterns(null, false, "/*");
    }
}
