create table comments
(
    id          int unsigned not null auto_increment,
    sender      int unsigned not null,
    post        int unsigned not null,
    message     varchar(140) not null,
    timestamp   datetime not null,
    
    primary key (id),
    foreign key (sender) references users(id),
    foreign key (post) references posts(id)
);