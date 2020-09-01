create table users(
    username varchar(50) not null primary key,
    password varchar(300) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null primary key,
    authority varchar(50) not null primary key,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);


CREATE TABLE `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `holder_name` varchar(45) NOT NULL,
  `card_number` varchar(14) NOT NULL,
  `card_expiry_date` varchar(5) NOT NULL,
  PRIMARY KEY (`id`,`user_name`),
  UNIQUE KEY `card_number_UNIQUE` (`card_number`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;


Insert into ecards.users (username,password,enabled) values('admin10','$2a$10$6iPSce.D4Q0p3C/7y0H8J.0KxCVjtHbL/KyhgC8XK/ilcrQgLXkV.',1);
Insert into ecards.authorities (username,authority) values('admin10','ROLE_ADMIN');
