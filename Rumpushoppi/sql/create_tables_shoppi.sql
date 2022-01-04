CREATE TABLE tuote(
id int auto_increment not null,
nimi varchar(40) not null,
kuvaus varchar(100) not null,
hinta decimal(10, 2) not null,
primary key(id)
);