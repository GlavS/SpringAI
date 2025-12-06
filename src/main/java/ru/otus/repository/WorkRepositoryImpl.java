package ru.otus.repository;

import java.util.List;
import java.util.Optional;
import ru.otus.db.WorksDB;
import ru.otus.model.Difficulty;
import ru.otus.model.Work;

public class WorkRepositoryImpl implements WorkRepository {

    private final WorksDB db;

    public WorkRepositoryImpl(WorksDB db) {
        this.db = db;
    }

    @Override
    public List<Work> getAll() {
        return db.getDB();
    }

    @Override
    public Optional<Work> getById(long id) {
        return db.getDB().stream().filter(work -> work.getId() == id).findFirst();
    }

    @Override
    public List<Work> getByComposer(String composerSurname) {
        return db.getDB().stream()
                .filter(work -> work.getComposer().contains(composerSurname))
                .toList();
    }

    @Override
    public List<Work> getByDifficulty(Difficulty difficulty) {
        return db.getDB().stream()
                .filter(work -> work.getDifficulty() == difficulty)
                .toList();
    }

    @Override
    public long addWork(Work work) {
        List<Work> works = db.getDB();
        long id = works.size() + 1;
        work.setId(id);
        works.add(work);
        return id;
    }
}
