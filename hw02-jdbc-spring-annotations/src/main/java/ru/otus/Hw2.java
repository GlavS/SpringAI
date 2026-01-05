package ru.otus;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.otus.application.Hw2ApplicationRunner;

import java.io.File;

@Configuration
@ComponentScan(basePackages = "ru.otus", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = RestController.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebConfig.class)
})

@PropertySource("classpath:application.properties")
public class Hw2 {

    private static final Logger log = LoggerFactory.getLogger(Hw2.class);

    public static void main(String[] args) throws LifecycleException {

        log.debug("Application started");
        var coreCtx = new AnnotationConfigApplicationContext(Hw2.class);
        var runner = coreCtx.getBean(Hw2ApplicationRunner.class);
        runner.run();

        var webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.setParent(coreCtx);
        webCtx.register(WebConfig.class);
        startTomcat(webCtx, 8080);

    }

    private static void startTomcat(WebApplicationContext webCtx, int port) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();

        String docBase = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
        Context context = tomcat.addContext("", docBase);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webCtx);

        var reg = Tomcat.addServlet(context, "dispatcher", dispatcherServlet);
        reg.setLoadOnStartup(1);
        context.addServletMappingDecoded("/", "dispatcher");

        tomcat.start();
        log.info("Tomcat started on port: {}", port);
        tomcat.getServer().await();
    }

}
