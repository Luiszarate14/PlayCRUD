# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table pregunta (
  id                            bigint auto_increment not null,
  texto                         varchar(255),
  tipo                          varchar(255),
  requerida                     tinyint(1) default 0,
  texto_ayuda                   varchar(255),
  constraint pk_pregunta primary key (id)
);


# --- !Downs

drop table if exists pregunta;

