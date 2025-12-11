package ru.otus.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ru.otus.db.WorkDb;
import ru.otus.model.Difficulty;
import ru.otus.model.Work;

@SuppressWarnings("unused")
public class WorkRepositoryImpl implements WorkRepository {

    private final WorkDb db;

    public WorkRepositoryImpl(WorkDb db) {
        this.db = db;
    }

    @Override
    public List<Work> getAll() {
        return new ArrayList<>(db.findAll());
    }

    @Override
    public Optional<Work> getById(long id) {
        return db.findById(id);
    }

    @Override
    public List<Work> getByComposer(String composerSurname) {
        return db.findAll().stream()
                .filter(work -> work.getComposer().contains(composerSurname))
                .toList();
    }

    @Override
    public List<Work> getByDifficulty(Difficulty difficulty) {
        return db.findAll().stream()
                .filter(work -> work.getDifficulty() == difficulty)
                .toList();
    }

    @Override
    public long addWork(Work work) {
        db.save(work);
        return work.getId();
    }
}
