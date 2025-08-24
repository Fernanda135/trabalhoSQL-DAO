-- Database: midiasdao

-- DROP DATABASE IF EXISTS midiasdao;

CREATE DATABASE midiasdao
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE IF NOT EXISTS public.midias
(
    id integer NOT NULL DEFAULT nextval('midias_id_seq'::regclass),
    titulo character varying(50) COLLATE pg_catalog."default" NOT NULL,
    tipo character varying(10) COLLATE pg_catalog."default" NOT NULL,
    franquia character varying(50) COLLATE pg_catalog."default",
    nota integer,
    CONSTRAINT midias_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.midias
    OWNER to postgres;