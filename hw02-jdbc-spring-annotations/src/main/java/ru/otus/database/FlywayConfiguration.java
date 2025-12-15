package ru.otus.database;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {

    private final DataSource dataSource;

    public FlywayConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public Flyway flyway(){
        return Flyway.configure()
                .dataSource(dataSource)
                .schemas("hw2")
                .defaultSchema("hw2")
                .locations("classpath:db/migration")
                .load();
    }
}
