create sequence t_artists_SEQ start with 1 increment by 50;
create sequence t_customers_SEQ start with 1 increment by 50;
create sequence t_items_SEQ start with 1 increment by 50;
create sequence t_publishers_SEQ start with 1 increment by 50;
create table Book
(
    nb_of_pages      integer,
    publication_date date,
    id               bigint not null,
    isbn             varchar(15),
    language         varchar(20) check (language in
                                        ('ENGLISH', 'FRENCH', 'SPANISH', 'GERMAN', 'ITALIAN', 'FINNISH', 'SWEDISH',
                                         'NORWEGIAN', 'RUSSIAN', 'POLISH', 'JAPANESE', 'CHINESE', 'HINDI', 'ARABIC',
                                         'PORTUGUESE', 'DUTCH', 'BENGALI', 'PUNJABI', 'TURKISH', 'CZECH', 'UKRAINIAN',
                                         'KOREAN', 'ROMANIAN', 'GREEK', 'HUNGARIAN', 'DANISH', 'PERSIAN', 'MALAY',
                                         'THAI', 'INDONESIAN', 'HEBREW', 'VIETNAMESE', 'URDU', 'TAMIL', 'LATVIAN',
                                         'LITHUANIAN', 'SLOVAK', 'SLOVENIAN', 'BULGARIAN', 'CROATIAN', 'ESTONIAN',
                                         'ICELANDIC', 'MALTESE')),
    primary key (id)
);
create table CD
(
    id            bigint not null,
    genre         varchar(100),
    music_company varchar(255),
    primary key (id)
);
create table t_artists
(
    created_date timestamp(6) with time zone not null,
    id           bigint                      not null,
    name         varchar(100)                not null,
    bio          varchar(3000)               not null,
    primary key (id)
);
create table t_customers
(
    created_date timestamp(6) with time zone not null,
    id           bigint                      not null,
    first_name   varchar(50),
    last_name    varchar(50),
    e_mail       varchar(255)                not null,
    primary key (id)
);
create table t_items
(
    price        numeric(38, 2)              not null,
    created_date timestamp(6) with time zone not null,
    id           bigint                      not null,
    title        varchar(100)                not null,
    description  varchar(3000),
    primary key (id)
);
create table t_publishers
(
    created_date timestamp(6) with time zone not null,
    id           bigint                      not null,
    name         varchar(50)                 not null,
    primary key (id)
);
alter table if exists Book
    add constraint FKrc4aujt4u7vihi7kovrbbtxsv foreign key (id) references t_items;
alter table if exists CD
    add constraint FKaytv0uvt7n00ea6i2dnw4o78v foreign key (id) references t_items;
