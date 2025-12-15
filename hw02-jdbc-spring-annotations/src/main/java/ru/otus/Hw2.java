package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import ru.otus.application.Hw2ApplicationRunner;

@Configuration
@ComponentScan(basePackages = "ru.otus")
@PropertySource("classpath:application.properties")
public class Hw2 {

    private static final Logger log = LoggerFactory.getLogger(Hw2.class);

    public static void main(String[] args) {

        log.debug("Application started");
        try (var ctx = new AnnotationConfigApplicationContext(Hw2.class)) {
            var runner = ctx.getBean(Hw2ApplicationRunner.class);
            runner.run();
        }
    }
}
