package ru.otus.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import ru.otus.csvparser.CsvParser;
import ru.otus.model.Work;

public class InMemoryWorkDb implements WorkDb {

    private final List<Work> modifableDb = new ArrayList<>();

    private final AtomicLong idSequence = new AtomicLong(0);

    public InMemoryWorkDb(CsvParser csvParser) {
        List<Work> unmodifableData = csvParser.parse();
        modifableDb.addAll(unmodifableData);
        idSequence.set(modifableDb.size());
    }

    @Override
    public List<Work> findAll() {
        return List.copyOf(modifableDb);
    }

    @Override
    public Optional<Work> findById(long id) {
        return modifableDb.stream().filter(work -> work.getId() == id).findFirst();
    }

    @Override
    public Work save(Work work) {
        if (Objects.isNull(work)) {
            throw new InMemoryWorkDbException("Work object cannot be null");
        }

        if (work.getId() == null) {
            work.setId(idSequence.incrementAndGet());
            modifableDb.add(work);
            return work;
        }

        modifableDb.removeIf(w -> w.getId().equals(work.getId()));
        modifableDb.add(work);
        return work;
    }

    @Override
    public void deleteById(long id) {
        if (modifableDb.stream().noneMatch(w -> w.getId() == id)) {
            throw new InMemoryWorkDbException("Cannot delete: no work with id " + id);
        }
        modifableDb.removeIf(w -> w.getId() == id);
    }
}
