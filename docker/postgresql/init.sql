-- create table position
create table public.position
(
    id     serial primary key,
    title  varchar not null
);

comment on table "position" is 'Список доступных должностей';
comment on column "position".id is 'ID должности';
comment on column "position".title is 'Наименование должности';

insert into public."position" (title) values
                                          ('Конструктор'),
                                          ('Менеджер'),
                                          ('Купец-бакалейщик');

-- create table user
create table public.user
(
    id                  serial primary key,
    username            varchar not null,
    age                 int not null,
    date_of_employment  timestamp not null default current_timestamp,
    position_id         int constraint user_position_id_fk references "position" (id) not null
);

comment on table "user" is 'Список пользователей';
comment on column "user".id is 'ID пользователя';
comment on column "user".username is 'Имя пользователя';
comment on column "user".age is 'Возраст';
comment on column "user".date_of_employment is 'Дата принятия на работу';
comment on column "user".position_id is 'ID должности';

-- create table group
create table public.group
(
    id                  serial primary key,
    title               varchar not null,
    date_of_employment  timestamp not null default current_timestamp
);

comment on table "group" is 'Список групп';
comment on column "group".id is 'ID группы';
comment on column "group".title is 'Название группы';
comment on column "group".date_of_employment is 'Дата формирования группы';

-- create table group_user
create table public.group_user
(
    id        serial primary key,
    group_id  int constraint group_user_group_id_fk references "group" (id) not null,
    user_id   int constraint group_user_user_id_fk references "user" (id) not null
);

alter table public.group_user
    add constraint uniquie_group_id_user_id unique (group_id, user_id);

comment on table group_user is 'Список связей групп и пользователей';
comment on column group_user.id is 'ID связи';
comment on column group_user.group_id is 'ID группы';
comment on column group_user.user_id is 'ID пользователя';

-- create table task
create table public.task
(
    id              serial primary key,
    title           varchar not null,
    date_create     timestamp not null not null default current_timestamp,
    user_id         int constraint task_user_id_fk references "user" (id) not null
);

comment on table task is 'Список работы';
comment on column task.id is 'ID работы';
comment on column task.title is 'Наименование работы';
comment on column task.date_create is 'Дата создания работы';
comment on column task.user_id is 'ID пользователя, которому назначено на исполнение';