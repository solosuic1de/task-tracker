CREATE SEQUENCE tasks_id_seq;

CREATE TYPE public.task_status AS ENUM
    ('DONE', 'IN_PROGRESS', 'VIEW');

    CREATE TABLE public.tasks
(
    id bigint NOT NULL DEFAULT nextval('tasks_id_seq'::regclass),
    description character varying COLLATE pg_catalog."default",
    title character varying COLLATE pg_catalog."default" NOT NULL,
    user_id bigint NOT NULL,
    status task_status NOT NULL,
    CONSTRAINT tasks_pkey PRIMARY KEY (id),
    CONSTRAINT user_fk FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)