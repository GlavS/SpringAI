package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.application.ApplicationRunner;

@SuppressWarnings("java:S1118")
public class Hw1 {

    static void main() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationRunner runner = context.getBean(ApplicationRunner.class);
        runner.run();
    }
}
