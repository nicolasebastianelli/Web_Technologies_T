drop table TipoAccertamento_Struttura;
drop table accertamento;
drop table paziente;
drop table richiesta_medica;
drop table struttura;
drop table tipo_accertamento;




create table TipoAccertamento_Struttura (tipo_accertamento_ids bigint not null, struttura_ids bigint not null, primary key (struttura_ids, tipo_accertamento_ids));
create table accertamento (id bigint not null, codice integer not null unique, esito varchar(255), tipo_accertamento_id bigint, richiesta_medica_id bigint, primary key (id));
create table paziente (id bigint not null, codiceFiscale varchar(255) not null unique, nome varchar(255) not null, cognome varchar(255) not null, sesso char(1) not null, primary key (id));
create table richiesta_medica (id bigint not null, codicePaziente varchar(255) not null, data timestamp not null, nomeMedico varchar(255) not null, paziente_id bigint, primary key (id), unique (codicePaziente, data));
create table struttura (id bigint not null, codice integer not null unique, nome varchar(255) not null, indirizzo varchar(255) not null, primary key (id));
create table tipo_accertamento (id bigint not null, codice integer not null unique, descrizione varchar(255), primary key (id));
alter table TipoAccertamento_Struttura add constraint FK9B8ED42BA06FA326 foreign key (tipo_accertamento_ids) references tipo_accertamento;
alter table TipoAccertamento_Struttura add constraint FK9B8ED42BE85F54B7 foreign key (struttura_ids) references struttura;
alter table accertamento add constraint FK5C75D3D6B42C4DC5 foreign key (tipo_accertamento_id) references tipo_accertamento;
alter table accertamento add constraint FK5C75D3D65332E109 foreign key (richiesta_medica_id) references richiesta_medica;
alter table richiesta_medica add constraint FK1B8B013250993AFC foreign key (paziente_id) references paziente;
