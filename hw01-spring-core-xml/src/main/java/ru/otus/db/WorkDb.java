package ru.otus.db;

import java.util.List;
import java.util.Optional;
import ru.otus.model.Work;

public interface WorkDb {
    List<Work> findAll();

    Optional<Work> findById(long id);

    Work save(Work work);

    void deleteById(long id);
}
