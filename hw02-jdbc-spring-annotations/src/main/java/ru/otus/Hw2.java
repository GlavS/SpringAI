package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.application.ApplicationRunner;

@Configuration
@ComponentScan(basePackages = "ru.otus")
@PropertySource("classpath:application.properties")
public class Hw2 {

    private static final Logger log = LoggerFactory.getLogger(Hw2.class);

    public static void main(String[] args) {
        log.debug("Application started");
        try (var ctx = new AnnotationConfigApplicationContext(Hw2.class)) {
            var runner = ctx.getBean(ApplicationRunner.class);
            runner.run();
        }
    }
}
