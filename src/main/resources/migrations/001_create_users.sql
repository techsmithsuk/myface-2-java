create table users
(
    id              int unsigned not null auto_increment,
    username        varchar(255) not null unique,
    email           varchar(255) not null unique,
    first_name      varchar(255) not null,
    last_name       varchar(255) not null,
    
    primary key (id)
);