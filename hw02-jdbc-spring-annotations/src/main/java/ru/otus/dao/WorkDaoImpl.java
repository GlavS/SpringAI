package ru.otus.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import ru.otus.model.*;

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
        Map<String, Long> idParam = Map.of("id", id);
        String workSql =
                """
                        select  w.work_id, w.title, w.difficulty,
                                c.composer_id, c.name, c.surname, country,
                                i.instrument_id, i.name
                        from hw2.work w
                                join hw2.composer c on w.composer_id = c.composer_id
                                join hw2.instrument i on i.instrument_id = w.instrument_id
                                where w.work_id = :id
                        """;
        String genreSql =
                """
                        select g.genre_id, g.name from hw2.genre g
                                join hw2.work_genre wg on g.genre_id = wg.genre_id
                                join hw2.work w on wg.work_id = w.work_id
                                where w.work_id = :id
                        """;
        String recordingSql =
                """
                        select r.recording_id,
                               r.performer,
                               r.label,
                               r.recorded_at,
                               r.duration_sec,
                               r.source_url from hw2.recording r
                                    join hw2.work w on w.work_id = r.work_id
                                    where w.work_id = :id
                        """;
        WorkVo workVo = namedJdbc.queryForObject(
                workSql,
                idParam,
                (rs, rn) -> new WorkVo(
                        rs.getLong(1),
                        rs.getString(2),
                        new Composer(rs.getLong(4), rs.getString(5), rs.getString(6), rs.getString(7)),
                        new Instrument(rs.getLong(8), rs.getString(9)),
                        rs.getString(3)));
        List<Genre> genres = namedJdbc.query(genreSql, idParam, (rs, rn) -> new Genre(rs.getLong(1), rs.getString(2)));
        List<Recording> recordings = namedJdbc.query(
                recordingSql,
                idParam,
                (rs, rn) -> new Recording(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        Date.valueOf(rs.getString(4)),
                        rs.getInt(5),
                        rs.getString(6)));
        return Optional.of(
                new Work(workVo.work_id(), workVo.title(), workVo.composer(), workVo.instrument(), genres, recordings));
    }

    @Override
    public Work save(Work work) {
        if (work == null) throw new UnsupportedOperationException("parameter should not be null TDD");
        throw new UnsupportedOperationException("save TDD");
    }

    @Override
    public void delete(long id) {
        if (id == 0) throw new UnsupportedOperationException("parameter should not be zero TDD");
        throw new UnsupportedOperationException("delete TDD");
    }

    private static class WorkRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong(1), rs.getString(2));
        }
    }

    private record WorkVo(long work_id, String title, Composer composer, Instrument instrument, String difficulty) {}
}
