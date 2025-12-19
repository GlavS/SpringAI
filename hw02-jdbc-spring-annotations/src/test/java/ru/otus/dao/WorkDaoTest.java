package ru.otus.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.dao.testconfig.TestConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, WorkDaoImpl.class})
class WorkDaoTest {

    @Autowired
    private WorkDao dao;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void findAll() {
        dao.findAll();
    }

    @Test
    void findById() {}

    @Test
    void save() {}

    @Test
    void delete() {}
}
