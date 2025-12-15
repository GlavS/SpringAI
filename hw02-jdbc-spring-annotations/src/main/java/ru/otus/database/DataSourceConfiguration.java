package ru.otus.database;

import static ru.otus.utility.AppPropsUtility.getParam;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfiguration {

    private final Environment env;

    public DataSourceConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                getParam(env, "hw2.datasource.driver", "No DB driver name found in application environment"));
        dataSource.setUrl(getParam(env, "hw2.datasource.url", "No DB url found in application environment"));
        dataSource.setUsername(
                getParam(env, "hw2.datasource.username", "No DB username found in application environment"));
        dataSource.setPassword(
                getParam(env, "hw2.datasource.password", "No DB password found in application environment"));
        return dataSource;
    }
}
