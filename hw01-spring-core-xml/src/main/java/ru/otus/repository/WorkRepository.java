package ru.otus.repository;

import java.util.List;
import java.util.Optional;
import ru.otus.model.Difficulty;
import ru.otus.model.Work;

public interface WorkRepository {
    List<Work> getAll();

    Optional<Work> getById(long id);

    List<Work> getByComposer(String composerSurname);

    List<Work> getByDifficulty(Difficulty difficulty);

    long addWork(Work work);
}
