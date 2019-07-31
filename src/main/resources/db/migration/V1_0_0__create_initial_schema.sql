CREATE TABLE person
(
    person_id   BIGSERIAL,
    name        CHARACTER VARYING(255) ,
    email       CHARACTER VARYING(255) NOT NULL ,
    phone       CHARACTER VARYING(255) NOT NULL ,
    is_active    BOOLEAN NOT NULL ,
    is_deleted   BOOLEAN NOT NULL ,
    is_blocked   BOOLEAN NOT NULL ,

    PRIMARY KEY (person_id)
)