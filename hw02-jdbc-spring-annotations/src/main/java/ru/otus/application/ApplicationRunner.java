package ru.otus.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ApplicationRunner {

    private final Logger log = LoggerFactory.getLogger(ApplicationRunner.class);

    public void run() {
        log.debug("Application runner ready");
    }
}
