-- =========================
-- COMPOSERS (7)
-- =========================
INSERT INTO hw2.composer (name, surname, country)
VALUES ('Johann Sebastian', 'Bach', 'Germany'),
       ('Wolfgang Amadeus', 'Mozart', 'Austria'),
       ('Ludwig van', 'Beethoven', 'Germany'),
       ('Frédéric', 'Chopin', 'Poland'),
       ('Pyotr Ilyich', 'Tchaikovsky', 'Russia'),
       ('Antonio', 'Vivaldi', 'Italy'),
       ('Claude', 'Debussy', 'France');

-- =========================
-- INSTRUMENTS (6)
-- =========================
INSERT INTO hw2.instrument (name)
VALUES ('Piano'),
       ('Violin'),
       ('Cello'),
       ('Flute'),
       ('Classical Guitar'),
       ('Clarinet');

-- =========================
-- WORKS (30)
-- difficulty: EASY | MEDIUM | HARD | VERY_HARD
-- =========================
INSERT INTO hw2.work (title, composer_id, instrument_id, difficulty)
VALUES ('Minuet in G Major, BWV Anh. 114', 1, 1, 'EASY'),
       ('Prelude in C Major, BWV 846', 1, 1, 'MEDIUM'),
       ('Invention No. 1 in C Major, BWV 772', 1, 1, 'HARD'),
       ('Air (Orchestral Suite No. 3), BWV 1068', 1, 2, 'MEDIUM'),
       ('Bourrée in E minor, BWV 996', 1, 5, 'MEDIUM'),

       ('Piano Sonata No. 16 in C, K. 545', 2, 1, 'MEDIUM'),
       ('Eine kleine Nachtmusik, K. 525 (Excerpt)', 2, 2, 'MEDIUM'),
       ('Clarinet Concerto, K. 622 (Adagio)', 2, 6, 'HARD'),
       ('Flute Concerto No. 1, K. 313 (Excerpt)', 2, 4, 'HARD'),

       ('Für Elise, WoO 59', 3, 1, 'MEDIUM'),
       ('Piano Sonata No. 14 "Moonlight" (I)', 3, 1, 'HARD'),
       ('Symphony No. 5 (Opening motif, arr.)', 3, 2, 'MEDIUM'),
       ('Ode to Joy (Theme), Op. 125 (arr.)', 3, 5, 'EASY'),

       ('Prelude in E minor, Op. 28 No. 4', 4, 1, 'MEDIUM'),
       ('Waltz in A minor, B. 150', 4, 1, 'MEDIUM'),
       ('Etude Op. 10 No. 12 "Revolutionary"', 4, 1, 'VERY_HARD'),
       ('Nocturne in E-flat Major, Op. 9 No. 2', 4, 1, 'HARD'),

       ('Swan Lake (Theme, arr.)', 5, 1, 'MEDIUM'),
       ('The Nutcracker - Dance of the Sugar Plum Fairy (arr.)', 5, 1, 'HARD'),
       ('Serenade for Strings (Waltz, arr.)', 5, 2, 'HARD'),
       ('Melody, Op. 42 No. 3 (arr.)', 5, 3, 'MEDIUM'),

       ('The Four Seasons - Spring (I, excerpt)', 6, 2, 'HARD'),
       ('The Four Seasons - Winter (I, excerpt)', 6, 2, 'VERY_HARD'),
       ('Concerto in D Major, RV 93 (Excerpt)', 6, 4, 'HARD'),
       ('Cello Sonata No. 5 in E minor, RV 40 (Excerpt)', 6, 3, 'HARD'),

       ('Clair de Lune', 7, 1, 'VERY_HARD'),
       ('Arabesque No. 1', 7, 1, 'HARD'),
       ('Prélude à l’après-midi d’un faune (Excerpt, arr.)', 7, 4, 'VERY_HARD'),
       ('Rêverie (arr.)', 7, 2, 'MEDIUM'),
       ('Golliwogg’s Cakewalk (Excerpt)', 7, 1, 'HARD');
