drop table crime;
drop table stop_force;
drop table location;
drop table street;
drop table outcome_status;
drop table outcome_object;
drop table force;
create table street
(
    id   bigserial primary key,
    name varchar(200)
);
create table outcome_object
(
    id   varchar(200) primary key,
    name varchar(200)
);
create table outcome_status
(
    id       bigserial primary key,
    category varchar(200),
    date     varchar(50)
);

create table location
(
    id        bigserial primary key,
    latitude  float,
    longitude float,
    street_id bigint references street (id) on delete set null
);
create table crime
(
    category          varchar(200),
    location_type     varchar(200),
    location_id       bigint references location (id) on delete set null,
    context           varchar(200),
    outcome_status_id bigint references outcome_status (id) on delete set null,
    persistent_id     varchar(200),
    id                bigserial primary key,
    location_subtype  varchar(200),
    month             date

);

create table stop_force
(
    id                                  bigserial primary key,
    age_range                           varchar(200),
    self_defined_ethnicity              varchar(200),
    outcome_linked_to_object_of_search  boolean,
    datetime                            date,
    removal_of_more_than_outer_clothing boolean,
    operation                           boolean,
    officer_defined_ethnicity           varchar(200),
    object_of_search                    varchar(200),
    involved_person                     boolean,
    outcome_object_id                   varchar(200) references outcome_object (id) on delete set null,
    gender                              varchar(10),
    legislation                         varchar(300),
    location_id                         bigint       references location (id) on delete set null,
    outcome                             boolean,
    type                                varchar(100),
    operation_name                      varchar(100)
);
create table force
(
    id   varchar(200) primary key,
    name varchar(200)
);
