package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.menu.commands.WorkByDifficultyCommand;

public class Hw1 {
    private static final Logger log = LoggerFactory.getLogger(Hw1.class);

    static void main() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        WorkByDifficultyCommand command = context.getBean(WorkByDifficultyCommand.class);
        command.execute();
    }
}
