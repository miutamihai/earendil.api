create table repositories(
    id serial primary key,
    name varchar(255) not null unique ,
    token varchar(255) not null
);

create table runs(
    id serial primary key ,
    creation_date timestamp default now(),
    status varchar(255),
    initiator varchar(255),
    repository_id integer not null,
    constraint repository_id_fk foreign key (repository_id) references repositories (id)
);