package ru.otus.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.dao.testconfig.TestConfiguration;
import ru.otus.model.Work;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, WorkDaoImpl.class})
@DisplayName("В классе WorkDaoImpl")
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
    @DisplayName("Метод findAll возвращает все сущности со связями")
    void findAllShouldReturnCorrectEntityList() throws IOException {
        List<Work> all = dao.findAll();
        assertThat(all).hasSize(3);
        assertThat(all.getFirst()).isInstanceOf(Work.class);

        List<Work> expectedList = mapper.readValue(
                resourceLoader
                        .getResource("classpath:fixtures/expected_works_all.json")
                        .getInputStream(),
                new TypeReference<List<Work>>() {});
        assertThat(all).usingRecursiveComparison().isEqualTo(expectedList);
    }

    @Test
    @DisplayName("Метод findById возвращает сущность")
    void findByIdShouldReturnCorrectEntity() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:fixtures/expected_work_1.json");
        Work expected = mapper.readValue(resource.getInputStream(), Work.class);
        Optional<Work> result = dao.findById(1L);
        assertThat(result).isPresent().get().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @DisplayName("Метод findById возвращает пустой Optional, если сущности нет в базе")
    void findByIdShouldReturnOptionalEmptyInCaseOfNotFound() {
        Optional<Work> result = dao.findById(142L);
        assertThat(result).isEmpty();
    }

    @Test
    void save() throws IOException {
        Work workToAdd = mapper.readValue(
                resourceLoader
                        .getResource("classpath:fixtures/work_to_add_and_save.json")
                        .getInputStream(),
                Work.class);
        Work saved = dao.insert(workToAdd);
        System.out.println(saved); //TODO Проверить recordings
    }

    @Test
    void delete() {
        dao.delete(0L);
    }
}
