drop table lesUtilisateurs;

create table lesUtilisateurs (
pseudo varchar(40) NOT NULL,
nom varchar(30)  NOT NULL,
prenom varchar(30)  NOT NULL ,
adresseMail varchar(100)  NOT NULL,
password varchar(60)  NOT NULL,
Constraint LUt_PK PRIMARY KEY (pseudo),
Constraint LUt_1 UNIQUE (adresseMail)
);
