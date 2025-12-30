
SET search_path TO hw2;

-- =========================
-- GENRES (~20)
-- =========================
INSERT INTO hw2.genre (name)
VALUES
    ('Baroque'),
    ('Classical'),
    ('Romantic'),
    ('Impressionism'),
    ('Modern'),
    ('Contemporary'),
    ('Sacred'),
    ('Opera'),
    ('Ballet'),
    ('Chamber Music'),
    ('Symphony'),
    ('Concerto'),
    ('Sonata'),
    ('Suite'),
    ('Etude'),
    ('Nocturne'),
    ('Prelude'),
    ('Fugue'),
    ('Variations'),
    ('Program Music')
ON CONFLICT (name) DO NOTHING;

-- =========================
-- WORK <-> GENRE links
-- 1..3 жанра на work, достаточно разнообразно
-- =========================
INSERT INTO hw2.work_genre (work_id, genre_id)
SELECT w.work_id, g.genre_id
FROM hw2.work w
         JOIN LATERAL (
    SELECT genre_id
    FROM hw2.genre
    WHERE w.work_id IS NOT NULL
    ORDER BY random()
    LIMIT (1 + (random() * 2)::int) -- 1..3
    ) g ON true
ON CONFLICT DO NOTHING;

-- =========================
-- RECORDINGS: много записей на каждое произведение
-- 4..10 записей на work (в среднем много, для N+1 прям идеально)
-- =========================
INSERT INTO hw2.recording (work_id, performer, label, recorded_at, duration_sec, source_url)
SELECT
    w.work_id,

    (ARRAY[
        'Berlin Philharmonic',
        'Vienna Philharmonic',
        'London Symphony Orchestra',
        'Royal Concertgebouw Orchestra',
        'Academy of St Martin in the Fields',
        'The English Concert',
        'Yo-Yo Ma',
        'Martha Argerich',
        'Glenn Gould',
        'Andrés Segovia',
        'Julian Bream',
        'Hilary Hahn',
        'Gidon Kremer',
        'Cecilia Bartoli',
        'Placido Domingo'
        ])[1 + (random() * 14)::int] || ' #' || gs.n AS performer,

    (ARRAY[
        'Deutsche Grammophon',
        'Sony Classical',
        'EMI Classics',
        'Decca',
        'Philips',
        'Naxos',
        'Warner Classics',
        'Harmonia Mundi'
        ])[1 + (random() * 7)::int] AS label,

    (DATE '1970-01-01' + ((random() * 20000)::int)) AS recorded_at,

    (120 + (random() * 1200)::int) AS duration_sec,

    ('https://example.org/hw2/recordings/' || w.work_id || '/' || gs.n) AS source_url
FROM hw2.work w
         JOIN LATERAL generate_series(
        1,
        4 + (random() * 6)::int  -- 4..10
                      ) gs(n) ON true;
