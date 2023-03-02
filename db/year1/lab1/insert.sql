ALTER USER s367079 SET search_path TO s367079;

TRUNCATE TABLE
    live_creature,
    creature_species,
    spaceship,
    flight,
    flight_log_entry,
    planet,
    object,
    mystery,
    object_type,
    disclosure_type,
    mystery_disclosure,
    astronaut,
    crew,
    object_mystery
    CASCADE;


-- Humans
WITH ins_ids as
         (INSERT INTO planet (name, location)
             VALUES ('Earth', (10.0, 10.0, 10.0)),
                    ('Jupiter', (30.0, 40.0, 50.0))
             RETURNING id),
     ins_species as
         (INSERT INTO creature_species (name, base_planet_id)
             VALUES ('Humans', (SELECT * FROM ins_ids LIMIT 1)),
                    ('Jupiterians', (SELECT * FROM ins_ids OFFSET 1 LIMIT 1))
             RETURNING id)
INSERT
INTO live_creature (species_id, name)
VALUES ((SELECT * FROM ins_species LIMIT 1), 'A'),
       ((SELECT * FROM ins_species LIMIT 1), 'B');


-- mystery
INSERT INTO mystery (name, description)
VALUES ('strange mystery', 'we do not know about what this mystery is');

INSERT INTO disclosure_type (description, disclosure_percent)
VALUES ('guess', 10),
       ('know', 100);

WITH myst as (SELECT id FROM mystery WHERE name = 'strange mystery')
INSERT
INTO mystery_disclosure (mystery_id, creature_species_id, available_type_id)
VALUES ((SELECT * FROM myst),
        (SELECT id FROM creature_species WHERE name = 'Humans'),
        (SELECT id FROM disclosure_type WHERE description = 'guess')),
       ((SELECT * FROM myst),
        (SELECT id FROM creature_species WHERE name = 'Jupiterians'),
        (SELECT id FROM disclosure_type WHERE description = 'know'));


-- object
INSERT INTO object_type (name, description)
VALUES ('Clouds', 'cloud layers'),
       ('Storm', 'storm'),
       ('Unknown', null);

INSERT INTO object (type_id, planet_id, name, relative_position, area)
VALUES ((SELECT id FROM object_type WHERE object_type.name = 'Clouds'),
        (SELECT id FROM planet WHERE planet.name = 'Jupiter'),
        'Cloud layers',
        (0, 0, 1000),
        1000000);

WITH brs_data as
         (INSERT INTO object (type_id, planet_id, name, relative_position, area)
             VALUES ((SELECT id FROM object_type WHERE object_type.name = 'Storm'),
                     (SELECT id FROM planet WHERE planet.name = 'Jupiter'),
                     'Big Red Spot',
                     (10.2, 123.4, 13.42),
                     312123) RETURNING id, planet_id),
     area_data as
         (INSERT INTO object (type_id, planet_id, name, relative_position, area, parent_object_id)
             VALUES ((SELECT id FROM object_type WHERE object_type.name = 'Unknown'),
                     (SELECT planet_id FROM brs_data),
                     'Some area in Big Red Spot',
                     (1.1, 2.2, 3.3),
                     1000,
                     (SELECT id FROM brs_data)) RETURNING id)
INSERT
INTO object_mystery (mystery_id, object_id)
VALUES ((SELECT id FROM mystery WHERE name LIKE '%strange%'),
        (SELECT id FROM area_data));


-- spaceship
WITH crew_id as
         (INSERT INTO crew (name, creation_date)
             VALUES ('main crew', now()) RETURNING id),
     ship as
         (INSERT INTO spaceship (name, crew_id)
             VALUES ('Alexey Leonov', (SELECT * FROM crew_id))
             RETURNING id)
INSERT
INTO astronaut (creature_id, crew_id)
SELECT id, (SELECT * FROM crew_id)
FROM live_creature;

WITH flight as (INSERT
    INTO flight
        VALUES (default,
                (SELECT id FROM spaceship LIMIT 1),
                (123, 123, 124),
                (6153, 11223, 6124),
                now()) RETURNING id)
INSERT
INTO flight_log_entry (flight_id, obstacle_overcame_id, timestamp, location)
VALUES ((SELECT * FROM flight),
        (SELECT object.id
         FROM object
                  JOIN object_type ON object.type_id = object_type.id
         WHERE object_type.name = 'Clouds'),
        now(),
        (123, 1251, 425)),
       ((SELECT * FROM flight),
        null,
        '2023-03-01 10:11:12',
        (12, 125, 42));
