package ru.otus.dao;

import java.sql.Date;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.otus.model.*;

@Component
@SuppressWarnings({"java:S1068", "java:S1481", "java:S1854"})
public class WorkDaoImpl implements WorkDao {

    private static final Logger log = LoggerFactory.getLogger(WorkDaoImpl.class);
    private final NamedParameterJdbcOperations namedJdbc;

    public WorkDaoImpl(NamedParameterJdbcOperations namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    private static long getNewWorkId(KeyHolder keyHolder) {
        Map<String, Object> newWorkAsMap;
        if (keyHolder.getKeys() != null) {
            newWorkAsMap = keyHolder.getKeys();
        } else {
            throw new DaoException("Error getting data from keyholder");
        }
        return (long) newWorkAsMap.get("work_id");
    }

    @Override
    public List<Work> findAll() {
        List<Work> resultWorkList = new ArrayList<>();
        String workSql =
                """
                        select  w.work_id, w.title, w.difficulty,
                                c.composer_id, c.name, c.surname, country,
                                i.instrument_id, i.name
                        from hw2.work w
                                join hw2.composer c on w.composer_id = c.composer_id
                                join hw2.instrument i on i.instrument_id = w.instrument_id
                        order by w.work_id
                        """;

        String genreVoSql =
                """
                        select w.work_id, g.genre_id, g.name from hw2.work w
                            left join hw2.work_genre wg on w.work_id = wg.work_id
                            left join hw2.genre g on g.genre_id = wg.genre_id
                        order by w.work_id
                        """;

        String recordingSql =
                """
                        select r.recording_id,
                               r.work_id,
                               r.performer,
                               r.label,
                               r.recorded_at,
                               r.duration_sec,
                               r.source_url from hw2.recording r
                        """;
        List<WorkVo> workVoList = namedJdbc.query(
                workSql,
                (rs, _) -> new WorkVo(
                        rs.getLong(1),
                        rs.getString(2),
                        new Composer(rs.getLong(4), rs.getString(5), rs.getString(6), rs.getString(7)),
                        new Instrument(rs.getLong(8), rs.getString(9)),
                        rs.getString(3)));
        log.debug("{}", workSql);
        List<RecordingWorkIdVo> recordingWorkIdVoList = namedJdbc.query(
                recordingSql,
                (rs, _) -> new RecordingWorkIdVo(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getString(3),
                        rs.getString(4),
                        Date.valueOf(rs.getString(5)),
                        rs.getInt(6),
                        rs.getString(7)));
        log.debug("{}", recordingSql);
        Map<Long, List<Recording>> recordingIndex = new HashMap<>();
        for (RecordingWorkIdVo r : recordingWorkIdVoList) {
            recordingIndex
                    .computeIfAbsent(r.work_id(), _ -> new ArrayList<>())
                    .add(new Recording(
                            r.recording_id(), r.performer(), r.label(), r.recorded_at(), r.duration(), r.source_url()));
        }
        log.debug("{}", recordingSql);
        List<GengreVo> gengreVoList =
                namedJdbc.query(genreVoSql, (rs, _) -> new GengreVo(rs.getLong(1), rs.getLong(2), rs.getString(3)));

        Map<Long, List<Genre>> genreIndex = new HashMap<>();
        for (GengreVo g : gengreVoList) {
            genreIndex.computeIfAbsent(g.work_id(), _ -> new ArrayList<>()).add(new Genre(g.genre_id(), g.name()));
        }

        for (WorkVo w : workVoList) {
            resultWorkList.add(new Work(
                    w.work_id(),
                    w.title(),
                    w.composer(),
                    w.instrument(),
                    genreIndex.get(w.work_id()),
                    recordingIndex.get(w.work_id()),
                    Difficulty.from(w.difficulty())));
        }

        return resultWorkList;
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
        WorkVo workVo;
        try {
            workVo = namedJdbc.queryForObject(
                    workSql,
                    idParam,
                    (rs, _) -> new WorkVo(
                            rs.getLong(1),
                            rs.getString(2),
                            new Composer(rs.getLong(4), rs.getString(5), rs.getString(6), rs.getString(7)),
                            new Instrument(rs.getLong(8), rs.getString(9)),
                            rs.getString(3)));
            log.debug("{}", workSql);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return Optional.empty();
        }

        List<Genre> genres = namedJdbc.query(genreSql, idParam, (rs, _) -> new Genre(rs.getLong(1), rs.getString(2)));
        log.debug("{}", genreSql);
        List<Recording> recordings = namedJdbc.query(
                recordingSql,
                idParam,
                (rs, _) -> new Recording(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        Date.valueOf(rs.getString(4)),
                        rs.getInt(5),
                        rs.getString(6)));
        log.debug("{}", recordingSql);
        return Optional.of(new Work(
                workVo.work_id(),
                workVo.title(),
                workVo.composer(),
                workVo.instrument(),
                genres,
                recordings,
                Difficulty.from(workVo.difficulty())));
    }

    @Override
    public Work insert(Work work) {
        if (work == null) throw new DaoException("parameter should not be null");
        Map<String, Object> workParamMap = Map.of(
                "title", work.getTitle(),
                "composer_id", work.getComposer().getId(),
                "instrument_id", work.getInstrument().getId(),
                "difficulty", work.getDifficulty().name());
        MapSqlParameterSource params = new MapSqlParameterSource(workParamMap);
        String insertSql =
                """
                insert into hw2.work(title, composer_id, instrument_id, difficulty)
                values (:title, :composer_id, :instrument_id, :difficulty)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update(insertSql, params, keyHolder);
        long newWorkId = getNewWorkId(keyHolder);
        long[] genreIds = work.getGenres().stream()
                .map(Genre::getId)
                .mapToLong(Long::longValue)
                .toArray();
        MapSqlParameterSource wgParams = new MapSqlParameterSource();
        wgParams.addValue("work_id", newWorkId);
        wgParams.addValue("genre_ids", genreIds);
        String wgInsertSql =
                """
                INSERT INTO hw2.work_genre (work_id, genre_id)
                SELECT :work_id, unnest(:genre_ids::bigint[])
                ON CONFLICT DO NOTHING
                """;
        namedJdbc.update(wgInsertSql, wgParams);

        return new Work(
                newWorkId,
                work.getTitle(),
                work.getComposer(),
                work.getInstrument(),
                work.getGenres(),
                work.getRecordings(),
                work.getDifficulty());
    }

    @Override
    public Work update(Work work) {
        return null;
    }

    @Override
    public void delete(long id) {
        if (id == 0) throw new UnsupportedOperationException("parameter should not be zero TDD");
        throw new UnsupportedOperationException("delete TDD");
    }

    private record WorkVo(long work_id, String title, Composer composer, Instrument instrument, String difficulty) {}

    private record RecordingWorkIdVo(
            long recording_id,
            long work_id,
            String performer,
            String label,
            Date recorded_at,
            int duration,
            String source_url) {}

    private record GengreVo(long work_id, long genre_id, String name) {}
}
