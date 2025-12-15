-- =========================
-- COMPOSERS (7)
-- =========================
INSERT INTO public.composer (id, name, surname, country)
VALUES (1, 'Johann Sebastian', 'Bach', 'Germany'),
       (2, 'Wolfgang Amadeus', 'Mozart', 'Austria'),
       (3, 'Ludwig van', 'Beethoven', 'Germany'),
       (4, 'Frédéric', 'Chopin', 'Poland'),
       (5, 'Pyotr Ilyich', 'Tchaikovsky', 'Russia'),
       (6, 'Antonio', 'Vivaldi', 'Italy'),
       (7, 'Claude', 'Debussy', 'France');

-- =========================
-- INSTRUMENTS (6)
-- =========================
INSERT INTO public.instrument (id, name)
VALUES (1, 'Piano'),
       (2, 'Violin'),
       (3, 'Cello'),
       (4, 'Flute'),
       (5, 'Classical Guitar'),
       (6, 'Clarinet');

-- =========================
-- WORKS (30)
-- difficulty: EASY | MEDIUM | HARD | VERY_HARD
-- =========================
INSERT INTO public.work (id, title, composer_id, instrument_id, difficulty)
VALUES (1, 'Minuet in G Major, BWV Anh. 114', 1, 1, 'EASY'),
       (2, 'Prelude in C Major, BWV 846', 1, 1, 'MEDIUM'),
       (3, 'Invention No. 1 in C Major, BWV 772', 1, 1, 'HARD'),
       (4, 'Air (Orchestral Suite No. 3), BWV 1068', 1, 2, 'MEDIUM'),
       (5, 'Bourrée in E minor, BWV 996', 1, 5, 'MEDIUM'),

       (6, 'Piano Sonata No. 16 in C, K. 545', 2, 1, 'MEDIUM'),
       (7, 'Eine kleine Nachtmusik, K. 525 (Excerpt)', 2, 2, 'MEDIUM'),
       (8, 'Clarinet Concerto, K. 622 (Adagio)', 2, 6, 'HARD'),
       (9, 'Flute Concerto No. 1, K. 313 (Excerpt)', 2, 4, 'HARD'),

       (10, 'Für Elise, WoO 59', 3, 1, 'MEDIUM'),
       (11, 'Piano Sonata No. 14 "Moonlight" (I)', 3, 1, 'HARD'),
       (12, 'Symphony No. 5 (Opening motif, arr.)', 3, 2, 'MEDIUM'),
       (13, 'Ode to Joy (Theme), Op. 125 (arr.)', 3, 5, 'EASY'),

       (14, 'Prelude in E minor, Op. 28 No. 4', 4, 1, 'MEDIUM'),
       (15, 'Waltz in A minor, B. 150', 4, 1, 'MEDIUM'),
       (16, 'Etude Op. 10 No. 12 "Revolutionary"', 4, 1, 'VERY_HARD'),
       (17, 'Nocturne in E-flat Major, Op. 9 No. 2', 4, 1, 'HARD'),

       (18, 'Swan Lake (Theme, arr.)', 5, 1, 'MEDIUM'),
       (19, 'The Nutcracker - Dance of the Sugar Plum Fairy (arr.)', 5, 1, 'HARD'),
       (20, 'Serenade for Strings (Waltz, arr.)', 5, 2, 'HARD'),
       (21, 'Melody, Op. 42 No. 3 (arr.)', 5, 3, 'MEDIUM'),

       (22, 'The Four Seasons - Spring (I, excerpt)', 6, 2, 'HARD'),
       (23, 'The Four Seasons - Winter (I, excerpt)', 6, 2, 'VERY_HARD'),
       (24, 'Concerto in D Major, RV 93 (Excerpt)', 6, 4, 'HARD'),
       (25, 'Cello Sonata No. 5 in E minor, RV 40 (Excerpt)', 6, 3, 'HARD'),

       (26, 'Clair de Lune', 7, 1, 'VERY_HARD'),
       (27, 'Arabesque No. 1', 7, 1, 'HARD'),
       (28, 'Prélude à l’après-midi d’un faune (Excerpt, arr.)', 7, 4, 'VERY_HARD'),
       (29, 'Rêverie (arr.)', 7, 2, 'MEDIUM'),
       (30, 'Golliwogg’s Cakewalk (Excerpt)', 7, 1, 'HARD');
