create table if not exists messages
(
    id  bigserial not null
        constraint messages_pk
            primary key,
    msg text      not null
);

alter table messages
    owner to postgres;

create unique index if not exists messages_id_uindex
    on messages (id);


/*Table for users*/
create table if not exists users
(
    id  bigserial
        constraint users_pk
            primary key,
    msg text      not null
);

alter table users
    owner to postgres;

create unique index if not exists users_id_uindex
    on users (id);




