ALTER USER s367079
    SET search_path TO s367079;

DROP TABLE IF EXISTS
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
    astronauts,
    crews,
    objects_mysteries
    CASCADE;

DROP TYPE IF EXISTS crew_role, d3_position CASCADE;
CREATE TYPE s367079.crew_role AS enum ('commander', 'private');
CREATE TYPE s367079.d3_position AS
(
    x float,
    y float,
    z float
);

-- Objects
CREATE TABLE s367079.object_type
(
    id          int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        varchar(50) UNIQUE,
    description text
);

CREATE TABLE s367079.planets
(
    id       int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     varchar(50) NOT NULL UNIQUE,
    location d3_position NOT NULL
);

CREATE TABLE s367079.objects
(
    id                int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    parent_object_id  int REFERENCES objects (id),
    planet_id         int REFERENCES planets (id),
    type_id           int REFERENCES object_type (id) NOT NULL,
    name              varchar(50)                     NOT NULL,
    relative_position d3_position UNIQUE              NOT NULL,
    area              float
);


-- Creatures
CREATE TABLE s367079.creature_species
(
    id             int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name           varchar(50) UNIQUE NOT NULL,
    base_planet_id int REFERENCES planets (id)
);

CREATE TABLE s367079.live_creatures
(
    id         int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    species_id int REFERENCES creature_species (id) NOT NULL,
    name       varchar(50)                          NOT NULL

);

-- Space ship
CREATE TABLE s367079.crews
(
    id            int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name          varchar(50) UNIQUE NOT NULL,
    creation_date date               NOT NULL
);

CREATE TABLE s367079.astronauts
(
    creature_id int REFERENCES live_creatures (id) UNIQUE NOT NULL,
    crew_id     int REFERENCES crews (id)                 NOT NULL,
    role        crew_role                                 NOT NULL DEFAULT ('private'),

    PRIMARY KEY (creature_id, crew_id)
);

CREATE TABLE s367079.spaceships
(
    id      int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    varchar(50) NOT NULL,
    crew_id int REFERENCES crews (id)
);

CREATE TABLE s367079.flights
(
    id           int GENERATED ALWAYS AS IDENTITY UNIQUE,
    spaceship_id int REFERENCES spaceships (id) NOT NULL,
    origin       d3_position                    NOT NULL,
    destination  d3_position                    NOT NULL,
    start_date   timestamp                      NOT NULL,
    PRIMARY KEY (id, spaceship_id)
);

CREATE TABLE s367079.flight_log_entries
(
    id                   int GENERATED ALWAYS AS IDENTITY,
    flight_id            int REFERENCES flights (id) NOT NULL,
    obstacle_overcame_id int REFERENCES objects (id),
    timestamp            timestamp                   NOT NULL,
    location             d3_position                 NOT NULL,
    PRIMARY KEY (id, flight_id)
);


-- Mysteries 
CREATE TABLE s367079.mysteries
(
    id          int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        varchar(50) NOT NULL UNIQUE,
    description text
);

CREATE TABLE s367079.disclosure_type
(
    id                 int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    description        TEXT NOT NULL UNIQUE,
    disclosure_percent int2
);

CREATE TABLE s367079.mystery_disclosure
(
    mystery_id          int REFERENCES mysteries (id)        NOT NULL,
    creature_species_id int REFERENCES creature_species (id) NOT NULL,
    available_type_id   int REFERENCES disclosure_type (id)  NOT NULL,
    PRIMARY KEY (mystery_id, creature_species_id)
);

CREATE TABLE s367079.objects_mysteries
(
    mystery_id int REFERENCES mysteries (id) NOT NULL,
    object_id  int REFERENCES objects (id)   NOT NULL,
    PRIMARY KEY (mystery_id, object_id)
);
