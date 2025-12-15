package ru.otus.application;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ApplicationRunner {

    private final Logger log = LoggerFactory.getLogger(ApplicationRunner.class);
    private final Flyway flyway;

    public ApplicationRunner(Flyway flyway) {
        this.flyway = flyway;
    }

    public void run() {
        log.debug("Application runner ready");
        log.debug("Flyway migration started...");
        flyway.migrate();
        log.debug("Flyway migration complete");
    }
}
