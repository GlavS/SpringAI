package ru.otus.dao.testconfig;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class TestConfiguration {

    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testDB")
            .withUsername("test")
            .withPassword("test");

    static {
        POSTGRES.start();
    }

    @Bean
    public DataSource dataSource() {
        var ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl(POSTGRES.getJdbcUrl());
        ds.setUsername(POSTGRES.getUsername());
        ds.setPassword(POSTGRES.getPassword());
        return ds;
    }

    @Bean
    public Flyway flyway(DataSource ds) {
        return Flyway.configure()
                .dataSource(ds)
                .schemas("hw2")
                .defaultSchema("hw2")
                .cleanDisabled(false)
                .locations("classpath:db/migration", "classpath:db/testseed")
                .load();
    }

    @Bean
    public NamedParameterJdbcOperations namedJdbc(DataSource ds) {
        return new NamedParameterJdbcTemplate(ds);
    }
}
