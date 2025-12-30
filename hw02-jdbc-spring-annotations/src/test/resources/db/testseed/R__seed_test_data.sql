-- Test seed data (repeatable)
CREATE SCHEMA IF NOT EXISTS hw2;
SET search_path TO hw2;

-- Make repeatable migration idempotent in tests
TRUNCATE TABLE
    hw2.recording,
    hw2.work_genre,
    hw2.work,
    hw2.genre,
    hw2.composer,
    hw2.instrument
    RESTART IDENTITY CASCADE;

-- =========================
-- COMPOSERS
-- =========================
INSERT INTO hw2.composer (name, surname, country)
VALUES ('TestComposerName1', 'TestComposerSurname1', 'TestCountry1'),
       ('TestComposerName2', 'TestComposerSurname2', 'TestCountry2');

-- =========================
-- INSTRUMENTS
-- =========================
INSERT INTO hw2.instrument (name)
VALUES ('Instrument1'),
       ('Instrument2');

-- =========================
-- WORKS (difficulty: EASY|MEDIUM|HARD|VERY_HARD)
-- =========================
INSERT INTO hw2.work (title, composer_id, instrument_id, difficulty)
VALUES ('TestWorkTitle1', 1, 1, 'EASY'),
       ('TestWorkTitle2', 1, 2, 'MEDIUM'),
       ('TestWorkTitle3', 2, 1, 'HARD');

-- =========================
-- GENRES
-- =========================
INSERT INTO hw2.genre (name)
VALUES ('Genre1'),
       ('Genre2'),
       ('Genre3');

-- =========================
-- WORK <-> GENRE
-- =========================
INSERT INTO hw2.work_genre (work_id, genre_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 3);

-- =========================
-- RECORDINGS
-- =========================
INSERT INTO hw2.recording (work_id, performer, label, recorded_at, duration_sec, source_url)
VALUES (1, 'TestPerformer1', 'TestLabel1', DATE '2020-01-01', 180, 'http://example.com/rec1'),
       (1, 'TestPerformer2', NULL, DATE '2021-02-03', 210, NULL),
       (2, 'TestPerformer3', 'TestLabel3', DATE '2022-03-04', 240, 'http://example.com/rec3');
