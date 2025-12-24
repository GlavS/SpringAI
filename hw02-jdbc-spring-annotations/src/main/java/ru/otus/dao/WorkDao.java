package ru.otus.dao;

import java.util.List;
import java.util.Optional;
import ru.otus.model.Work;

public interface WorkDao {
    List<Work> findAll();

    Optional<Work> findById(long id);

    Work insert(Work work);

    Work update(Work work);

    void delete(long id);
}
