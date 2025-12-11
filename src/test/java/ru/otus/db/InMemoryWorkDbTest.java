package ru.otus.db;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import ru.otus.csvparser.CsvParser;
import ru.otus.model.Difficulty;
import ru.otus.model.Work;

class InMemoryWorkDbTest {
    private final Work testWork1 = new Work(1L, "Test1 Name1", "TestWork1", "TestInstrument1", Difficulty.EASY);
    private final Work testWork2 = new Work(2L, "Test2 Name2", "TestWork2", "TestInstrument2", Difficulty.HARD);
    private final List<Work> databaseWorkList = new ArrayList<>();
    private final InMemoryWorkDb workDb;

    public InMemoryWorkDbTest() {
        this.databaseWorkList.add(testWork1);
        this.databaseWorkList.add(testWork2);
        CsvParser mockParser = mock(CsvParser.class);
        when(mockParser.parse()).thenReturn(databaseWorkList);
        this.workDb = new InMemoryWorkDb(mockParser);
    }

    @Test
    void findAll() {
        List<Work> resultList = workDb.findAll();
        assertThat(resultList).usingRecursiveComparison().isEqualTo(databaseWorkList);
    }

    @Test
    void findById() {
        Optional<Work> work1 = workDb.findById(1L);
        assertThat(work1).isPresent().contains(testWork1);

        Optional<Work> work2 = workDb.findById(2L);
        assertThat(work2).isPresent().contains(testWork2);

        Optional<Work> work3 = workDb.findById(999L);
        assertThat(work3).isNotPresent();
    }

    @Test
    void save() {
        Work newWork = new Work(null, "Test3 Name3", "TestWork3", "TestInstrument3", Difficulty.VERY_HARD);
        Work newResultWork = new Work(3L, "Test3 Name3", "TestWork3", "TestInstrument3", Difficulty.VERY_HARD);
        workDb.save(newWork);
        assertThat(workDb.findAll()).hasSize(3);
        assertThat(workDb.findById(3L)).isPresent().contains(newResultWork);
    }

    @Test
    void deleteById() {
        workDb.deleteById(2L);
        assertThat(workDb.findAll()).hasSize(1);
        assertThat(workDb.findById(1L)).isPresent();
        assertThat(workDb.findById(2L)).isNotPresent();
        assertThatThrownBy(() -> workDb.deleteById(3L))
                .isInstanceOf(InMemoryWorkDbException.class)
                .hasMessage("Cannot delete: no work with id 3");
    }
}
