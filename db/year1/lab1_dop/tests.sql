-- test runs
SELECT sh.name, fl.start_date, fl.origin as flight_origin, fl.destination as flight_destination
FROM spaceship sh
         JOIN flight fl on fl.spaceship_id = sh.id;

SELECT lc.name, cr.name as crew_name, astr.role as crew_role, sp.name as spaceship_name
FROM astronaut astr
         JOIN live_creature lc on astr.creature_id = lc.id
         JOIN crew cr on astr.crew_id = cr.id
         JOIN spaceship sp on cr.id = sp.crew_id;

SELECT sp.name as spaceship, fle.timestamp, obj.name as obstacle_overcame_name, fle.location as location
FROM flight_log_entry fle
         LEFT JOIN object obj on fle.obstacle_overcame_id = obj.id
         JOIN flight fl on fle.flight_id = fl.id
         JOIN spaceship sp on fl.spaceship_id = sp.id
WHERE flight_id = (SELECT id FROM flight LIMIT 1);

SELECT obj.name       as object,
       parent.name    as part_of,
       m.name         as mystery,
       dt.description as allowed_to,
       cs.name        as allowed_for
FROM object obj
         JOIN object parent on obj.parent_object_id = parent.id
         JOIN object_mystery om on obj.id = om.object_id
         JOIN mystery m on om.mystery_id = m.id
         JOIN mystery_disclosure md on m.id = md.mystery_id
         JOIN creature_species cs on md.creature_species_id = cs.id
         JOIN disclosure_type dt on md.available_type_id = dt.id;


SELECT * from object;