package ru.otus.dao;

import static org.assertj.core.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.dao.testconfig.TestConfiguration;
import ru.otus.model.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, WorkDaoImpl.class})
class WorkDaoTest {

    @Autowired
    private WorkDao dao;

    @Autowired
    private Flyway flyway;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ResourceLoader resourceLoader;

    @BeforeEach
    void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void findAll() {
        List<Work> all = dao.findAll();
    }

    @Test
    void findById() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:fixtures/work.json");
        Work w = mapper.readValue(resource.getInputStream(), Work.class);
        Optional<Work> byId = dao.findById(1L);
        assertThat(byId).isPresent().get().usingRecursiveComparison().isEqualTo(w);
    }

    @Test
    void save() {
        List<Genre> genreListToAdd =
                new ArrayList<>(List.of(new Genre(0L, "test name 1"), new Genre(0L, "test name 2")));
        List<Recording> recordingListToAdd = new ArrayList<>(List.of(
                new Recording(0L, "test performer", "test label", Date.valueOf("1970-03-20"), 600, "test/url"),
                new Recording(0L, "test performer", "test label", Date.valueOf("1970-03-20"), 600, "test/url")));
        Work workToAdd = new Work(
                0L,
                "test title",
                new Composer(0L, "test name", "test surname", "test country"),
                new Instrument(0L, "test name"),
                genreListToAdd,
                recordingListToAdd);
        dao.save(workToAdd);
    }

    @Test
    void delete() {
        dao.delete(0L);
    }
}
