package ru.otus.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import ru.otus.model.Work;

@Component
@SuppressWarnings("java:S1068")
public class WorkDaoImpl implements WorkDao {

    private final NamedParameterJdbcOperations namedJdbc;

    public WorkDaoImpl(NamedParameterJdbcOperations namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public List<Work> findAll() {
        throw new UnsupportedOperationException("findAll TDD");
    }

    @Override
    public Optional<Work> findById(long id) {
        throw new UnsupportedOperationException("findById TDD");
    }

    @Override
    public Work save(Work work) {
        if (work == null) throw new UnsupportedOperationException("parameter should not be null TDD");
        throw new UnsupportedOperationException("save TDD");
    }

    @Override
    public void delete(Work work) {
        if (work == null) throw new UnsupportedOperationException("parameter should not be null TDD");
        throw new UnsupportedOperationException("delete TDD");
    }
}
