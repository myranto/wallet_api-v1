
--table client
CREATE SEQUENCE customer_id_seq;
create  table customer(
    id varchar(30)  primary key default  'CUS' || LPAD(NEXTVAL('customer_id_seq')::text,5,'0') ,
    name varchar(100) not null,
    mail varchar(100) not null unique,
    phone varchar(20) not null,
    role varchar(1) not null DEFAULT 'C',
    password VARCHAR(100) not null ,
    creation_date timestamp not null default current_timestamp
);


--table pour le type de compte
--principale, épargne...
CREATE SEQUENCE account_type_id_seq;
create  table account_type(
    id varchar(30)  primary key default  'ACT' || LPAD(NEXTVAL('account_type_id_seq')::text,5,'0') ,
    type varchar(60) NOT NULL unique,
    code varchar(3) not null unique,
    creation_date timestamp not null default current_timestamp
);

--table ou se pour indiquer le montant du solde client dans le 
--permettant l'insertion manuel de solde
CREATE SEQUENCE account_seq;
create  table account(
    id varchar(30)  primary key default  'ACC' || LPAD(NEXTVAL('account_seq')::text,5,'0') ,
    current_amount numeric(10,2) default 0,
    date_amount timestamp default current_timestamp,
    customer_id varchar(30) references customer(id) not null,
    type_id varchar(30) not null references account_type(id),
    creation_date timestamp not null default current_timestamp
);

--table pour indiquer le type d'opération lors des transactions
--fixe, variable, soudain
CREATE SEQUENCE type_operation_seq;
create  table type_operation(
    id varchar(30)  primary key default  'TOP' || LPAD(NEXTVAL('type_operation_seq')::text,5,'0') ,
    libelle varchar(100) not null unique,
    code varchar(3) not null unique,
    creation_date timestamp not null default current_timestamp
);

--table pour indiquer si la transaction est en interne ou en externe
--exemple pour le transfert entre compte épargne <->principale
CREATE SEQUENCE type_charge_seq;
create  table type_charge(
    id varchar(30)  primary key default  'TCH' || LPAD(NEXTVAL('type_charge_seq')::text,5,'0') ,
    libelle varchar(100) not null unique,
    code varchar(3) not null unique,
    creation_date timestamp not null default current_timestamp  
);

--table charge
--raha vao tsy vide ny type charge dia operation en interne izy zay
CREATE SEQUENCE charge_seq;
create  table charge(
    id varchar(30)  primary key default  'CHA' || LPAD(NEXTVAL('charge_seq')::text,5,'0') ,
    customer_id varchar(30) references customer(id) not null,
    start_date timestamp not null,
    end_date timestamp default null,
    amount numeric(10,2) not null check(amount > 0),
    type_charge varchar(30) references type_charge(id),
    account_id varchar(30) references account_type(id),
    operation_id varchar(30) not null references type_operation(id),
    creation_date timestamp not null default current_timestamp
);

--table credit"
CREATE SEQUENCE credit_seq;
create  table credit(
    id varchar(30)  primary key default  'CRD' || LPAD(NEXTVAL('credit_seq')::text,5,'0') ,
    customer_id varchar(30) references customer(id) not null,
    start_date timestamp not null,
    end_date timestamp default null,
    amount numeric(10,2) not null check(amount > 0),
    account_id varchar(30) default null references account_type(id),
    operation_id varchar(30) not null references type_operation(id),
    creation_date timestamp not null default current_timestamp
);

--table pour indiquer le montant  transfert entre les comptes
CREATE SEQUENCE transfer_seq;
create  table transfer(
    id varchar(30)  primary key default  'TRS' || LPAD(NEXTVAL('transfer_seq')::text,5,'0') ,
    debit_account varchar(30) default null references account_type(id),
    credit_account varchar(30) default null references account_type(id) check( credit_account != debit_account),
    amount numeric(10,2) not null check(amount > 0),
    start_date timestamp not null,
    end_date timestamp default null,
    creation_date timestamp not null default current_timestamp
);

alter table customer add column status int default 0;
alter table account add column status int default 0;
alter table account_type add column status int default 0;
alter table type_operation add column status int default 0;
alter table type_charge add column status int default 0;
alter table charge add column status int default 0;
alter table credit add column status int default 0;
alter table transfer add column status int default 0;
alter table transfer add column customer varchar(30) not null references customer(id);

insert into customer(
name,
mail,
phone,
role,
creation_date,
password) values
 ('My Ranto', 'my.randrianantoandro@gmail.com', '0348549237','A',current_timestamp,'myranto'),
 ('Larry Hasinjato', 'larry.fah@gmail.com', '0346580050','C',current_timestamp,'Larry'),
 ('Betty', 'betty@gmail.com', '0348549239','C',current_timestamp,'betty');

SELECT a.*
FROM account a
WHERE a.customer_id = 'CUS00004'
and a.status=0
AND a.date_amount = (
    SELECT MAX(date_amount)
    FROM account sub_a
    WHERE sub_a.customer_id = 'CUS00004'
    AND sub_a.type_id = a.type_id
)

https://melodic-gumdrop-9ee334.netlify.app/home/account_type