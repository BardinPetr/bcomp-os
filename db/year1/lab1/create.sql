ALTER USER s367079
    SET search_path TO s367079;

DROP TABLE IF EXISTS
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

DROP TYPE IF EXISTS crew_role, d3_position CASCADE;
CREATE TYPE s367079.crew_role AS enum ('commander', 'private');
CREATE TYPE s367079.d3_position AS
(
    x float,
    y float,
    z float
);

-- object
CREATE TABLE s367079.object_type
(
    id          int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        varchar(50) UNIQUE,
    description text
);

CREATE TABLE s367079.planet
(
    id       int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     varchar(50) NOT NULL UNIQUE,
    location d3_position NOT NULL
);

CREATE TABLE s367079.object
(
    id                int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    parent_object_id  int REFERENCES object (id),
    planet_id         int REFERENCES planet (id),
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
    base_planet_id int REFERENCES planet (id)
);

CREATE TABLE s367079.live_creature
(
    id         int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    species_id int REFERENCES creature_species (id) NOT NULL,
    name       varchar(50)                          NOT NULL

);

-- Space ship
CREATE TABLE s367079.crew
(
    id            int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name          varchar(50) UNIQUE NOT NULL,
    creation_date date               NOT NULL
);

CREATE TABLE s367079.astronaut
(
    creature_id int REFERENCES live_creature (id) UNIQUE NOT NULL,
    crew_id     int REFERENCES crew (id)                 NOT NULL,
    role        crew_role                                 NOT NULL DEFAULT ('private'),

    PRIMARY KEY (creature_id, crew_id)
);

CREATE TABLE s367079.spaceship
(
    id      int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name    varchar(50) NOT NULL,
    crew_id int REFERENCES crew (id)
);

CREATE TABLE s367079.flight
(
    id           int GENERATED ALWAYS AS IDENTITY UNIQUE,
    spaceship_id int REFERENCES spaceship (id) NOT NULL,
    origin       d3_position                    NOT NULL,
    destination  d3_position                    NOT NULL,
    start_date   timestamp                      NOT NULL,
    PRIMARY KEY (id, spaceship_id)
);

CREATE TABLE s367079.flight_log_entry
(
    id                   int GENERATED ALWAYS AS IDENTITY,
    flight_id            int REFERENCES flight (id) NOT NULL,
    obstacle_overcame_id int REFERENCES object (id),
    timestamp            timestamp                   NOT NULL,
    location             d3_position                 NOT NULL,
    PRIMARY KEY (id, flight_id)
);


-- mystery
CREATE TABLE s367079.mystery
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
    mystery_id          int REFERENCES mystery (id)        NOT NULL,
    creature_species_id int REFERENCES creature_species (id) NOT NULL,
    available_type_id   int REFERENCES disclosure_type (id)  NOT NULL,
    PRIMARY KEY (mystery_id, creature_species_id)
);

CREATE TABLE s367079.object_mystery
(
    mystery_id int REFERENCES mystery (id) NOT NULL,
    object_id  int REFERENCES object (id)   NOT NULL,
    PRIMARY KEY (mystery_id, object_id)
);
