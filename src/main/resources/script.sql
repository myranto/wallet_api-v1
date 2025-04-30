
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
--maka solde farany ao anaty account

create or replace function get_manual_solde(idcustomer varchar)
    returns table(
        id varchar ,
        current_amount numeric,
        date_amount timestamp,
        customer_id varchar,
        type_id varchar,
        creation_date timestamp,
        status int
    )
    language plpgsql
    as $$
    begin
        return query 
        SELECT  a.*
        FROM account a
        WHERE a.customer_id = idcustomer
        and a.status=0
        AND a.date_amount = (
            SELECT MAX(sub_a.date_amount)
            FROM account sub_a
            WHERE sub_a.customer_id = idcustomer
            -- AND sub_a.date_amount <= current_timestamp
            AND date_trunc('month', sub_a.date_amount) = date_trunc('month', current_timestamp)
            AND sub_a.type_id = a.type_id
        );
    end; $$
--requete ilaina amle credit sy debit
create or replace view v_list_charge as
SELECT
    c.id,
    c.customer_id,
    -- Affiche le premier jour du mois généré
    date_trunc('month', gs.a)::date AS month,
    c.start_date,
    c.end_date,
    c.amount,
    c.account_id,
    c.operation_id,
    c.type_charge,--miala fotsiny ty raha ny credit
    c.status,
    c.creation_date
FROM
    charge c
JOIN
    generate_series(
        date_trunc('month', c.start_date),
        c.end_date,
        '1 month'::interval
    ) as gs(a) ON TRUE
ORDER BY
    c.id,
    mois_du_charge;--mois du credit raha credit

/*
    zavatra atao manaraka:
        -maka solde du mois :
            -> dans credit: -> 
select customer_id, sum(amount) amount,account_id from v_list_credit where  
(date_part('year', current_timestamp) = date_part('year', mois_du_credit))
and date_part('month', mois_du_credit) in (date_part('month', current_timestamp) - 1, date_part('month', current_timestamp) )
group by customer_id, account_id; 

            -> dans débit: ->
select customer_id, sum(amount) amount,account_id from v_list_charge where  
(date_part('year', current_timestamp) = date_part('year', mois_du_charge))
and date_part('month', mois_du_charge) in (date_part('month', current_timestamp) - 1, date_part('month', current_timestamp) )
group by customer_id, account_id;

            -> solde actuel: -> 
select * from get_manual_solde('CUS00004') mety ty
            -> sum(solde_actuel, solde credit) - solde_debit = solde du mois
    done.

    visualisation solde chaque mois:
        .zavatra misy:
            -liste solde credit dans un range date (credit par mois)
            -liste charge dans un range date (charge par mois)
        .Formule:
            for each account
                Solde nextMonth = solde month-1 + (credit of month - charge of month)
        .départ current month
            currentSolde = getCurrentAmount(String customer); --java
            liste credit = select * from v_list_credit  WHERE mois_du_credit > date_trunc('month', current_timestamp) + interval '1 month';
            liste charge = SELECT * FROM v_list_charge WHERE mois_du_charge > date_trunc('month', current_timestamp) + interval '1 month';
            mamorona Visualisation:
                -List<Credit>
                -List<Charge>
                -Month
                -TotalAmount

*/

https://melodic-gumdrop-9ee334.netlify.app/home/account_type