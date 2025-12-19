package ru.otus.database;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class JdbcConfiguration {

    @Bean
    public NamedParameterJdbcOperations namedJdbc(DataSource ds) {
        return new NamedParameterJdbcTemplate(ds);
    }
}
