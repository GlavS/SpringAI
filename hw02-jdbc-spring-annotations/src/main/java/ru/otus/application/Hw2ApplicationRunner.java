package ru.otus.application;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class Hw2ApplicationRunner {

    private static final String CLEAN = "clean";
    private final Logger log = LoggerFactory.getLogger(Hw2ApplicationRunner.class);
    private final Environment env;
    private final DataSource dataSource;

    public Hw2ApplicationRunner(Environment env, DataSource dataSource) {
        this.env = env;
        this.dataSource = dataSource;
    }

    public void run() {
        log.debug("Application runner ready");
        migrateSelectedMode();
    }

    private void migrateSelectedMode() {
        boolean cleaningIsActive;
        String mode = env.getProperty("hw2.mode");
        log.debug("Current flyway mode: {}", mode);
        cleaningIsActive = mode != null && mode.equalsIgnoreCase(CLEAN);

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .schemas("hw2")
                .defaultSchema("hw2")
                .locations("classpath:db/migration")
                .cleanDisabled(true)
                .load();
        if (cleaningIsActive) {
            Flyway flywayReset = Flyway.configure()
                    .dataSource(dataSource)
                    .schemas("hw2")
                    .defaultSchema("hw2")
                    .locations("classpath:db/migration")
                    .cleanDisabled(false)
                    .load();
            log.debug("Flyway cleaning migration started...");
            flywayReset.clean();
            flywayReset.migrate();
            log.debug("Flyway cleaning migration complete");
        } else {
            log.debug("Flyway normal migration started...");
            flyway.migrate();
            log.debug("Flyway normal migration complete");
        }
    }
}
