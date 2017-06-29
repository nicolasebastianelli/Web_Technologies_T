drop table TipoAccertamento_Ospedale;
drop table accertamenti;
drop table ospedali;
drop table tipo_accertamento;




create table TipoAccertamento_Ospedale (idtipiaccertamento bigint not null, idospedale bigint not null, primary key (idospedale, idtipiaccertamento));
create table accertamenti (id bigint not null, codice integer not null unique, nome varchar(255), descrizione varchar(255), tipo_accertamento_id bigint, primary key (id));
create table ospedali (id bigint not null, codice integer not null unique, nome varchar(255) not null, indirizzo varchar(255) not null, citta varchar(255) not null, primary key (id));
create table tipo_accertamento (id bigint not null, codice integer not null unique, descrizione varchar(255), primary key (id));
alter table TipoAccertamento_Ospedale add constraint FK2404E484A66EB7BB foreign key (idospedale) references ospedali;
alter table TipoAccertamento_Ospedale add constraint FK2404E4843624CFCB foreign key (idtipiaccertamento) references tipo_accertamento;
alter table accertamenti add constraint FK5C75D3D0B42C4DC5 foreign key (tipo_accertamento_id) references tipo_accertamento;
