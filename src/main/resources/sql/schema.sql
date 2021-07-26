create table if not exists  messages
(
    id bigserial not null,
    msg text not null
);

create unique index messages_id_uindex
	on messages (id);

alter table messages
    add constraint messages_pk
        primary key (id);

