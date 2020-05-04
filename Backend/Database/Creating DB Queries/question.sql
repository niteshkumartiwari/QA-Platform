-- Table: public.question

-- DROP TABLE public.question;

CREATE TABLE public.question
(
    id bigint NOT NULL DEFAULT nextval('question_id_seq'::regclass),
    title character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description character varying(500) COLLATE pg_catalog."default" NOT NULL,
    asked_by bigint NOT NULL,
    edited_by bigint,
    is_image smallint DEFAULT 0,
    res_link character varying(500) COLLATE pg_catalog."default",
    upkudo bigint DEFAULT 0,
    downkudo bigint DEFAULT 0,
    is_deleted smallint DEFAULT 0,
    is_banned smallint DEFAULT 0,
    created_on timestamp without time zone DEFAULT timezone('utc'::text, now()),
    last_updated timestamp without time zone,
    thumbnail character varying(500) COLLATE pg_catalog."default",
    CONSTRAINT question_pkey PRIMARY KEY (id),
    CONSTRAINT question_title_key UNIQUE (title),
    CONSTRAINT question_asked_by_fkey FOREIGN KEY (asked_by)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.question
    OWNER to postgres;