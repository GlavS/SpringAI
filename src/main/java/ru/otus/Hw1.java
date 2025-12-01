package ru.otus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.sample.Bean;

public class Hw1 {
    private static final Logger log = LoggerFactory.getLogger(Hw1.class);

    static void main() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object bean = context.getBean("bean");
        if (bean instanceof Bean b) {
            log.info(b.getName());
            Resource resource = new ClassPathResource(b.getCsv());
            try (InputStream is = resource.getInputStream();
                    InputStreamReader reader = new InputStreamReader(is)) {
                reader.readAllLines().forEach(log::info);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
