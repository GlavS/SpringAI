package ru.otus.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.db.WorkDb;
import ru.otus.model.Difficulty;
import ru.otus.model.Work;

class WorkRepositoryTest {

    private final WorkDb workDb = mock(WorkDb.class);
    private final WorkRepository workRepository = new WorkRepositoryImpl(workDb);
    private final Work resultWork = new Work(42L, "testName", "testTitle", "testInstrument", Difficulty.EASY);
    private final List<Work> resultList = List.of(resultWork);
    private final List<Work> works = List.of(resultWork);

    @BeforeEach
    void setUp() {
        when(workDb.findAll()).thenReturn(works);
        when(workDb.findById(42L)).thenReturn(Optional.of(resultWork));
    }

    @Test
    void repositoryGetAllMethodShouldReturnAllWorks() {
        List<Work> allWorks = workRepository.getAll();
        assertThat(allWorks).usingRecursiveComparison().isEqualTo(resultList);
    }

    @Test
    void repositoryGetByIdMethodShouldReturnExpectedOptional() {
        Optional<Work> work = workRepository.getById(42L);
        assertThat(work).isPresent();
        assertThat(work.get().getId()).isEqualTo(42L);
    }

    @Test
    void repositoryGetByComposerMethodShouldReturnExpectedListOfWorks() {
        List<Work> allWorks = workRepository.getByComposer("testName");
        assertThat(allWorks).usingRecursiveComparison().isEqualTo(resultList);
    }

    @Test
    void repositoryGetByDifficultyMethodShouldReturnExpectedListOfWorks() {
        List<Work> allWorks = workRepository.getByDifficulty(Difficulty.EASY);
        assertThat(allWorks).usingRecursiveComparison().isEqualTo(resultList);
    }

    @Test
    void repositoryAddWorkMethodShouldWorkAsExpected() {
        workRepository.addWork(resultWork);
        verify(workDb, times(1)).save(resultWork);
    }
}
