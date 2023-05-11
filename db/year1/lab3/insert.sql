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


-- object
INSERT INTO object_type (name, description)
VALUES ('Clouds', 'cloud layers'),
       ('Storm', 'storm'),
       ('Planet', 'planet'),
       ('Unknown', null);

WITH jup_data as
         (INSERT INTO object (type_id, name, relative_position, area)
             VALUES ((SELECT id FROM object_type WHERE object_type.name = 'Planet'),
                     'Jupyter',
                     (10000, 20000, 30000),
                     100000000) RETURNING id),

     cld_data as (INSERT INTO object (type_id, name, parent_object_id, relative_position, area)
         VALUES ((SELECT id FROM object_type WHERE object_type.name = 'Clouds'),
                 'Cloud layers',
                 (SELECT id FROM jup_data),
                 (0, 0, 1000),
                 1000000)),
     brs_data as
         (INSERT INTO object (type_id, name, parent_object_id, relative_position, area)
             VALUES ((SELECT id FROM object_type WHERE object_type.name = 'Storm'),
                     'Big Red Spot',
                     (SELECT id FROM jup_data),
                     (10.2, 123.4, 13.42),
                     312123) RETURNING id)
INSERT
INTO object (type_id, name, parent_object_id, relative_position, area)
VALUES ((SELECT id FROM object_type WHERE object_type.name = 'Unknown'),
        'Some area in Big Red Spot',
        (SELECT id FROM brs_data),
        (1.1, 2.2, 3.3),
        1000);

-- Humans
WITH planet_ids as (SELECT id
                    FROM object
                    WHERE object.type_id = (SELECT id FROM object_type WHERE object_type.name = 'Planet')),
     ins_ids as
         (INSERT INTO planet (SELECT * FROM planet_ids)
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
INSERT INTO disclosure_type (description, disclosure_percent)
VALUES ('guess', 10),
       ('know', 100);


WITH mysteries_ids as (INSERT INTO mystery (name, description)
    VALUES ('planet mystery', 'mystery of planet'),
           ('spot mystery', 'mystery of spot'),
           ('strange mystery', 'we do not know about what this mystery is'),
           ('test1', 'test1'),
           ('test2', 'test2'),
           ('test3', 'test3')
    RETURNING id)
INSERT
INTO object_mystery (mystery_id, object_id) VALUES
                                                ((SELECT id FROM mysteries_ids LIMIT 1), (SELECT id FROM object WHERE object.name = 'Jupyter')),
                                                ((SELECT id FROM mysteries_ids LIMIT 1 OFFSET 1), (SELECT id FROM object WHERE object.name = 'Big Red Spot')),
                                                ((SELECT id FROM mysteries_ids LIMIT 1 OFFSET 2), (SELECT id FROM object WHERE object.name = 'Some area in Big Red Spot'));

WITH myst as (SELECT id FROM mystery WHERE name = 'strange mystery')
INSERT
INTO mystery_disclosure (mystery_id, creature_species_id, available_type_id)
VALUES ((SELECT * FROM myst),
        (SELECT id FROM creature_species WHERE name = 'Humans'),
        (SELECT id FROM disclosure_type WHERE description = 'guess')),
       ((SELECT * FROM myst),
        (SELECT id FROM creature_species WHERE name = 'Jupiterians'),
        (SELECT id FROM disclosure_type WHERE description = 'know'));

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
INTO flight_log_entry (flight_id, timestamp, obstacle_overcame_id, location)
VALUES ((SELECT * FROM flight),
        now(),
        (SELECT object.id
         FROM object
                  JOIN object_type ON object.type_id = object_type.id
         WHERE object_type.name = 'Clouds'),
        (123, 1251, 425)),
       ((SELECT * FROM flight),
        '2023-03-01 10:11:12',
        null,
        (12, 125, 42));
