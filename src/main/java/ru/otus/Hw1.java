package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.menu.Menu;

public class Hw1 {
    private static final Logger log = LoggerFactory.getLogger(Hw1.class);

    static void main() {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Menu menu = (Menu)context.getBean("menu");
        menu.menuItems().forEach(item -> System.out.printf("%d. %s%n", item.id(), item.name()));


    }
}
