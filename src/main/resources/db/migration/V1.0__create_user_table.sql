CREATE SEQUENCE users_user_id_seq;
CREATE TABLE public.users
(
    user_id bigint NOT NULL DEFAULT nextval('users_user_id_seq'::regclass),
    email character varying(256) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying COLLATE pg_catalog."default" NOT NULL,
    last_name character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT uniq_email UNIQUE (email)
)