package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.application.ApplicationRunner;

@SuppressWarnings("java:S1172")
public class Hw1 {
    private Hw1() {
        throw new IllegalStateException("Main entry class");
    }

    static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationRunner runner = context.getBean(ApplicationRunner.class);
        runner.run();
    }
}
