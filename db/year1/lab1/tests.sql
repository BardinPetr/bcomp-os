-- test runs
SELECT sh.name, fl.start_date, fl.origin as flight_origin, fl.destination as flight_destination
FROM spaceships sh
         JOIN flights fl on fl.spaceship_id = sh.id;

SELECT lc.name, cr.name as crew_name, astr.role as crew_role, sp.name as spaceship_name
FROM astronauts astr
         JOIN live_creatures lc on astr.creature_id = lc.id
         JOIN crews cr on astr.crew_id = cr.id
         JOIN spaceships sp on cr.id = sp.crew_id;

SELECT sp.name as spaceship, fle.timestamp, obj.name as obstacle_overcame_name, obj.relative_position as location
FROM flight_log_entries fle
         LEFT JOIN objects obj on fle.obstacle_overcame_id = obj.id
         JOIN spaceships sp on fle.flight_id = sp.id
WHERE flight_id = (SELECT id FROM flights LIMIT 1);


SELECT obj.name       as object,
       parent.name    as part_of,
       m.name         as mystery,
       dt.description as allowed_to,
       cs.name        as allowed_for
FROM objects obj
         JOIN objects parent on obj.parent_object_id = parent.id
         JOIN objects_mysteries om on obj.id = om.object_id
         JOIN mysteries m on om.mystery_id = m.id
         JOIN mystery_disclosure md on m.id = md.mystery_id
         JOIN creature_species cs on md.creature_species_id = cs.id
         JOIN disclosure_type dt on md.available_type_id = dt.id;

