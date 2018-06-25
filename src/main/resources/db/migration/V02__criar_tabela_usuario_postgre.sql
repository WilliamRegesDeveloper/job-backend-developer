CREATE SEQUENCE public.sq_pk_usuario START 1;

CREATE TABLE usuario(
	id BIGINT NOT NULL DEFAULT nextval('public.sq_pk_usuario'),
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(150) NOT NULL,
    grupo VARCHAR(50),
    
	CONSTRAINT pk_usuario PRIMARY KEY(id)
);

INSERT INTO usuario (nome, email, password, grupo) VALUES ('william', 'william@gmail.com.br', '$2a$10$U7EZE2av1jL4mC5EBVUlROpVr5b.BZDfLh/99Wc9RxwnIAj9lr9Rq', 'ROLE_USUARIO');
INSERT INTO usuario (nome, email, password, grupo) VALUES ('joao', 'joao@gmail.com.br', '$2a$10$U7EZE2av1jL4mC5EBVUlROpVr5b.BZDfLh/99Wc9RxwnIAj9lr9Rq', 'ROLE_USUARIO');