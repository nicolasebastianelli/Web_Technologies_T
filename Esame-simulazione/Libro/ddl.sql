drop table autori;
drop table libri;




create table autori (id bigint not null, codiceFiscale varchar(255) not null unique, nome varchar(255) not null, cognome varchar(255) not null, primary key (id));
create table libri (id bigint not null, isbn varchar(255) not null unique, titolo varchar(255), idautore bigint, primary key (id));
alter table libri add constraint FK623237CA645D921 foreign key (idautore) references autori;
