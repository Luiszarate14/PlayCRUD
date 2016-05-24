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

create table respuesta (
  id                            bigint auto_increment not null,
  texto                         varchar(255),
  pregunta_id                   bigint,
  constraint pk_respuesta primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  username                      varchar(255),
  password_hash                 varchar(255),
  creation_date                 datetime(6),
  constraint uq_user_username unique (username),
  constraint pk_user primary key (id)
);

alter table respuesta add constraint fk_respuesta_pregunta_id foreign key (pregunta_id) references pregunta (id) on delete restrict on update restrict;
create index ix_respuesta_pregunta_id on respuesta (pregunta_id);


# --- !Downs

alter table respuesta drop foreign key fk_respuesta_pregunta_id;
drop index ix_respuesta_pregunta_id on respuesta;

drop table if exists pregunta;

drop table if exists respuesta;

drop table if exists user;

