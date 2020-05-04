CREATE TABLE public.answer
(
    id bigint NOT NULL DEFAULT nextval('answer_id_seq'::regclass),
    is_image smallint DEFAULT 0,
    res_link character varying(500) COLLATE pg_catalog."default" NOT NULL,
    caption character varying(500) COLLATE pg_catalog."default",
    views bigint DEFAULT 0,
    answered_by bigint NOT NULL,
    upkudo bigint DEFAULT 0,
    downkudo bigint DEFAULT 0,
    is_deleted smallint DEFAULT 0,
    is_shattered smallint DEFAULT 0,
    created_on timestamp without time zone DEFAULT timezone('utc'::text, now()),
    last_updated timestamp without time zone,
    thumbnail character varying(500) COLLATE pg_catalog."default",
    question_id bigint NOT NULL,
    CONSTRAINT answer_pkey PRIMARY KEY (id),
    CONSTRAINT unique_answer_per_person UNIQUE (question_id, answered_by),
    CONSTRAINT answer_answered_by_fkey FOREIGN KEY (answered_by)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT answer_question_id FOREIGN KEY (question_id)
        REFERENCES public.question (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)