package ru.otus.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.db.WorksDB;
import ru.otus.model.Difficulty;
import ru.otus.model.Work;

class WorkRepositoryTest {

    private final WorksDB worksDB = mock(WorksDB.class);
    private final WorkRepository workRepository = new WorkRepositoryImpl(worksDB);
    private final List<Work> db = new ArrayList<>();

    @BeforeEach
    void setUp() {
        when(worksDB.getDB()).thenReturn(db);
        Work workForTestDb = new Work(42L, "testName", "testTitle", "testInstrument", Difficulty.EASY);
        db.add(workForTestDb);
    }

    @Test
    void repositoryGetAllMethodShouldReturnAllWorks() {
        List<Work> works = workRepository.getAll();
        assertThat(works).usingRecursiveAssertion().isEqualTo(db);
    }

    @Test
    void repositoryGetByIdMethodShouldReturnExpectedOptional() {
        Optional<Work> work = workRepository.getById(42L);
        assertThat(work).isPresent();
        assertThat(work.get().getId()).isEqualTo(42L);
    }

    @Test
    void repositoryGetByComposerMethodShouldReturnExpectedListOfWorks() {
        List<Work> works = workRepository.getByComposer("testName");
        assertThat(works).usingRecursiveAssertion().isEqualTo(db);
    }

    @Test
    void repositoryGetByDifficultyMethodShouldReturnExpectedListOfWorks() {
        List<Work> works = workRepository.getByDifficulty(Difficulty.EASY);
        assertThat(works).usingRecursiveAssertion().isEqualTo(db);
    }

    @Test
    void repositoryAddWorkMethodShouldReturnExpectedNewWorkId() {
        Work workToAdd = db.getFirst();
        long expectedId = (long) workRepository.getAll().size() + 1;
        long newID = workRepository.addWork(workToAdd);
        assertThat(newID).isEqualTo(expectedId);
    }
}
