create table posts
(
    id          int unsigned not null auto_increment,
    sender      int unsigned not null,
    message     varchar(140) not null,
    image_url   varchar(1024),
    timestamp   timestamp,

    primary key (id),
    foreign key (sender) references users(id)
);
