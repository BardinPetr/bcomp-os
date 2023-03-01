ALTER USER s367079 SET search_path TO s367079;

TRUNCATE TABLE
    live_creatures,
    creature_species,
    spaceships,
    flights,
    flight_log_entries,
    planets,
    objects,
    mysteries,
    object_type,
    disclosure_type,
    mystery_disclosure,
    crew_participants,
    objects_mysteries
    CASCADE;

WITH ins_ids as
         (INSERT INTO planets (name, location)
             VALUES ('Earth', (10.0, 10.0, 10.0)),
                    ('Jupiter', (30.0, 40.0, 50.0))
             RETURNING id)
INSERT
INTO creature_species (name, base_planet_id)
VALUES ('Humans', (SELECT * FROM ins_ids));

INSERT
INTO live_creatures (species_id, name)
SELECT (SELECT id FROM creature_species WHERE name = 'Humans'),
       unnest(array ['A', 'B', 'C']);

-- INSERT INTO live_creatures (species_id, name)
-- VALUES ((SELECT id FROM creature_species WHERE name = 'Humans'), 'A'),
--        ((SELECT id FROM creature_species WHERE name = 'Humans'), 'B');


-- spaceships

-- INSERT INTO crew_participants (creature_id, role)
-- SELECT id as creature_id, 'private' FROM live_creatures;


-- WITH ship as (INSERT INTO spaceships (name, crew_id)
--     VALUES ('Alexey Leonov', ) RETURNING id)
-- INSERT
-- INTO flights
-- VALUES (default,
--         (SELECT * FROM ship),
--         (123, 123, 124),
--         (6153, 11223, 6124),
--         now());

INSERT INTO flight_log_entries (flight_id, obstacle_overcame_id, timestamp, location)
VALUES ((SELECT id FROM flights LIMIT 1),
        (SELECT objects.id
         FROM objects
                  JOIN object_type ON objects.type_id = object_type.id
         WHERE object_type.description == 'Clouds'),
        now(),
        (123, 1251, 425));

-- mysteries
INSERT INTO mysteries (name, description)
VALUES ('unknown mystery', 'we do not know about what this mystery is');

INSERT INTO disclosure_type (description, disclosure_percent)
VALUES ('guess', 10),
       ('solve', 100);

INSERT INTO mystery_disclosure (mystery_id, creature_species_id, available_type_id)
VALUES ((SELECT id FROM mysteries WHERE name = 'unknown mystery'),
        (SELECT id FROM creature_species WHERE name = 'Humans'),
        (SELECT id FROM disclosure_type WHERE description = 'guess'));


-- objects

INSERT INTO object_type (name, description)
VALUES ('Clouds', 'cloud layers'),
       ('Storm', 'storm'),
       ('Unknown', null);

INSERT INTO objects (type_id, planet_id, name, relative_position, area)
VALUES ((SELECT id FROM object_type WHERE object_type.name = 'Clouds'),
        (SELECT id FROM planets WHERE planets.name = 'Jupiter'),
        'Cloud layers',
        (0, 0, 1000),
        1000000);

WITH brs_data as
         (INSERT INTO objects (type_id, planet_id, name, relative_position, area)
             VALUES ((SELECT id FROM object_type WHERE object_type.name = 'Storm'),
                     (SELECT id FROM planets WHERE planets.name = 'Jupiter'),
                     'Big Red Spot',
                     (10.2, 123.4, 13.42),
                     312123) RETURNING id, planet_id),
     area_data as
         (INSERT INTO objects (type_id, planet_id, name, relative_position, area, parent_object_id)
             VALUES ((SELECT id FROM object_type WHERE object_type.name = 'Unknown'),
                     (SELECT planet_id FROM brs_data),
                     'Some area in Big Red Spot',
                     (1.1, 2.2, 3.3),
                     1000,
                     (SELECT id FROM brs_data)) RETURNING id)
INSERT
INTO objects_mysteries (mystery_id, object_id)
VALUES ((SELECT id FROM mysteries WHERE name LIKE '%unknown%'),
        (SELECT id FROM area_data));

