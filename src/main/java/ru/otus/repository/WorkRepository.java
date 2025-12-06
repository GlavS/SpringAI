package ru.otus.repository;

import ru.otus.model.Difficulty;
import ru.otus.model.Work;

import java.util.List;
import java.util.Optional;

public interface WorkRepository {
    List<Work> getAll();

    Optional<Work> getById(long id);

    List<Work> getByComposer(String composerSurname);

    List<Work> getByDifficulty(Difficulty difficulty);

    long addWork(Work work);
}
