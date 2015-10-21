drop table lesSeancesDistances;
drop table lesJoueurs;
drop table lesMatchs;
drop table lesSeances;
drop view viewLesSports;
drop table lesSports;
drop table lesUtilisateurs;
drop table lesParcours;




create table lesUtilisateurs (
pseudo varchar(40) NOT NULL,
nom varchar(30)  NOT NULL,
prenom varchar(30)  NOT NULL ,
adresseMail varchar(100)  NOT NULL,
password varchar(60)  NOT NULL,
codeVal varchar(10) NOT NULL,
Constraint LUt_PK PRIMARY KEY (pseudo),
Constraint LUt_1 UNIQUE (adresseMail)
);



create table lesSports (
nom varchar(30) NOT NULL,
typeSport varchar(30) NOT NULL,
utilisateur varchar(30) NOT NULL,
photo varchar(50), 
Constraint LSp_PK PRIMARY KEY (nom,utilisateur),
Constraint LSp_FK foreign key (utilisateur) references lesUtilisateurs(pseudo)
);




create table lesSeances(
idSeance int NOT NULL,
nomSeance varchar(50) NOT NULL,
lieu varchar(30),
jour DATE NOT NULL,
description varchar(150),
meteo varchar(30),
sport varchar(30) NOT NULL,
utilisateur varchar(30) NOT NULL,
duree int,
Constraint LSe_PK PRIMARY KEY (idSeance),
Constraint LSe_FK Foreign Key (sport,utilisateur) references lesSports(nom,utilisateur)
);


create view viewLesSports(nom,typeSport,utilisateur, photo,nbSeance) as 
SELECT nom,typeSport,lSp.utilisateur,photo,count(idSeance) as nbSeance
FROM lesSports lSp
LEFT OUTER JOIN lesSeances lSe ON (lSp.utilisateur=lSe.utilisateur AND nom=sport)
GROUP BY nom,lSp.utilisateur;

create table lesParcours(
idParcours int NOT NULL,
nomParcours varchar(50) NOT NULL,
distance int NOT NULL,
denivele float,
utilisateur varchar(30),
description varchar(150),
traceGPX varchar(30),
Constraint LPa_PK PRIMARY KEY (idParcours),
Constraint LPa_FK Foreign Key (utilisateur) references lesUtilisateurs (pseudo)
);


create table lesMatchs(
idMatch int NOT NULL,
idSeance int NOT NULL,
scoreJoueur double,
scoreAdv double,
Constraint LMa_PK PRIMARY KEY (idMatch),
Constraint LMa_FK Foreign Key (idSeance) references lesSeances(idSeance)
);

create table lesJoueurs(
nomJoueur varchar(20) NOT NULL,
idMatch int NOT NULL,
estAdv int NOT NULL,
Constraint LJo_PK PRIMARY KEY (nomJoueur,idMatch),
COnstraint LJo_FK Foreign Key (idMatch) references lesMatchs(idMatch)
);

create table lesSeancesDistances(
idSeance int NOT NULL,
idParcours int,
distance int NOT NULL,
denivele float,
Constraint LSeDi_PK PRIMARY KEY (idSeance),
COnstraint LSeDi_FK1 Foreign Key (idSeance) references lesSeances(idSeance),
COnstraint LSeDi_FK2 Foreign Key (idParcours) references lesParcours(idParcours)
);


