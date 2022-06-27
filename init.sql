create table repositories(
    id serial primary key,
    name varchar(255) not null unique ,
    token varchar(255) not null
)
