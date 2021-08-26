
-- DROP DATABASE testetecnico;

CREATE DATABASE testetecnico;

CREATE SCHEMA testetecnicoelotech;

CREATE TABLE testetecnicoelotech.pessoa
(
  id bigserial NOT NULL PRIMARY KEY,
  nome character varying(150),
  cpf character varying(60),
  datanascimento character varying(60)
);

Create Table testetecnicoelotech.contatos(
  id bigserial NOT NULL PRIMARY KEY,
  nome character varying(150),
  telefone character varying(60),
  email character varying(100),
  id_pessoa bigserial references testetecnicoelotech.pessoa (id)
);