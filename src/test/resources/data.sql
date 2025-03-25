create  table customer(
    id varchar(30)  primary key not null ,
    name varchar(100) not null,
    mail varchar(100) not null unique,
    phone varchar(20) not null,
    role varchar(1) not null,
    creation_date timestamp not null default current_timestamp,
    password varchar(100) not null
);

insert into customer(id,
name,
mail,
phone,
role,
creation_date,
password) values
 ('CUS001','My Ranto', 'my.randrianantoandro@gmail.com', '0348549237','A',current_timestamp,'myranto'),
 ('CUS002','Larry Hasinjato', 'larry.fah@gmail.com', '0346580050','C',current_timestamp,'Larry'),
 ('CUS003','Betty', 'betty@gmail.com', '0348549239','C',current_timestamp,'betty');
-- (name
-- mail
-- phone
-- role
-- creation_date
-- password);