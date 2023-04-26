SET search_path TO schema,s367079,public;

DROP FUNCTION IF EXISTS get_self_mysteries;
CREATE FUNCTION get_self_mysteries(cur_object_id int) RETURNS SETOF mystery AS
$$
DECLARE
BEGIN
    RETURN QUERY (SELECT m.*
                  FROM mystery m
                           JOIN object_mystery om on m.id = om.mystery_id
                  WHERE om.object_id = cur_object_id);
END;
$$ LANGUAGE plpgsql;


DROP FUNCTION IF EXISTS get_all_mysteries;
CREATE FUNCTION get_all_mysteries(cur_object object) RETURNS SETOF mystery AS
$$
DECLARE
    parent object;
BEGIN
    RETURN QUERY (SELECT * FROM get_self_mysteries(cur_object.id));

    SELECT o.*
    INTO parent
    FROM object o
    WHERE o.id = cur_object.parent_object_id;

    IF NOT FOUND THEN
        RETURN;
    END IF;

    RETURN QUERY SELECT * FROM get_all_mysteries(parent);
END;
$$ LANGUAGE plpgsql;


DROP FUNCTION IF EXISTS check_inherited_mysteries CASCADE;
CREATE FUNCTION check_inherited_mysteries() RETURNS TRIGGER AS
$$
DECLARE
    target_object   object;
    existed_mystery mystery;
BEGIN
    SELECT * INTO target_object FROM object WHERE object.id = (NEW.object_id);

    SELECT * INTO existed_mystery FROM get_all_mysteries(target_object) WHERE NEW.mystery_id = id;

    IF FOUND THEN
        RAISE NOTICE 'Object "%" already has mystery №% ("%"). Row % skipped',
            target_object.name, existed_mystery.id, existed_mystery.name, TG_OP;
        RETURN NULL;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE TRIGGER mystery_check_trigger
    BEFORE INSERT OR UPDATE
    ON object_mystery
    FOR EACH ROW
EXECUTE FUNCTION check_inherited_mysteries();



SELECT get_all_mysteries(o)
FROM object o
WHERE o.name = 'Some area in Big Red Spot';

INSERT INTO object_mystery (object_id, mystery_id)
VALUES (1, 4),
       (3, 4),
       (2, 5);

UPDATE object_mystery
SET mystery_id=(SELECT id FROM mystery WHERE name = 'test1')
WHERE object_id = (SELECT id FROM object WHERE name = 'Big Red Spot')
  AND mystery_id = (SELECT id FROM mystery WHERE name = 'test2');

UPDATE object_mystery
SET mystery_id=(SELECT id FROM mystery WHERE name = 'test3')
WHERE object_id = (SELECT id FROM object WHERE name = 'Big Red Spot')
  AND mystery_id = (SELECT id FROM mystery WHERE name = 'test2');


SELECT get_all_mysteries(o)
FROM object o
WHERE o.name = 'Some area in Big Red Spot';
