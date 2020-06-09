--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-06-10 00:59:37

SET statement_timeout
= 0;
SET lock_timeout
= 0;
SET idle_in_transaction_session_timeout
= 0;
SET client_encoding
= 'UTF8';
SET standard_conforming_strings
= on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies
= false;
SET xmloption
= content;
SET client_min_messages
= warning;
SET row_security
= off;

SET default_tablespace
= '';

--
-- TOC entry 205 (class 1259 OID 16630)
-- Name: answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.answer
(
    id bigint NOT NULL,
    is_image smallint DEFAULT 0,
    res_link character varying(500) NOT NULL,
    caption character varying(500),
    views bigint DEFAULT 0,
    answered_by bigint NOT NULL,
    upkudo bigint DEFAULT 0,
    downkudo bigint DEFAULT 0,
    is_deleted smallint DEFAULT 0,
    is_shattered smallint DEFAULT 0,
    created_on timestamp
    without time zone DEFAULT timezone
    ('utc'::text, now
    ()),
    last_updated timestamp without time zone,
    thumbnail character varying
    (500),
    question_id bigint NOT NULL
);


    --
    -- TOC entry 204 (class 1259 OID 16628)
    -- Name: answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
    --

    CREATE SEQUENCE public.answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


    --
    -- TOC entry 2962 (class 0 OID 0)
    -- Dependencies: 204
    -- Name: answer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
    --


    --
    -- TOC entry 216 (class 1259 OID 16838)
    -- Name: answer_user_kudo; Type: TABLE; Schema: public; Owner: postgres
    --

    CREATE TABLE public.answer_user_kudo
    (
        answer_id bigint NOT NULL,
        user_id bigint NOT NULL,
        created_on timestamp
        without time zone DEFAULT timezone
        ('utc'::text, now
        ())
);



        --
        -- TOC entry 218 (class 1259 OID 16872)
        -- Name: comment_answer; Type: TABLE; Schema: public; Owner: postgres
        --

        CREATE TABLE public.comment_answer
        (
            id bigint NOT NULL,
            content character varying(500) NOT NULL,
            upkudo bigint DEFAULT 0,
            answer_id bigint NOT NULL,
            replied_by bigint NOT NULL,
            created_on timestamp
            without time zone DEFAULT timezone
            ('utc'::text, now
            ())
);



            --
            -- TOC entry 217 (class 1259 OID 16870)
            -- Name: comment_answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
            --

            CREATE SEQUENCE public.comment_answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



            --
            -- TOC entry 2963 (class 0 OID 0)
            -- Dependencies: 217
            -- Name: comment_answer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
            --



            --
            -- TOC entry 220 (class 1259 OID 16895)
            -- Name: comment_question; Type: TABLE; Schema: public; Owner: postgres
            --

            CREATE TABLE public.comment_question
            (
                id bigint NOT NULL,
                content character varying(500) NOT NULL,
                upkudo bigint DEFAULT 0,
                question_id bigint NOT NULL,
                replied_by bigint NOT NULL,
                created_on timestamp
                without time zone DEFAULT timezone
                ('utc'::text, now
                ())
);



                --
                -- TOC entry 219 (class 1259 OID 16893)
                -- Name: comment_question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
                --

                CREATE SEQUENCE public.comment_question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



                --
                -- TOC entry 2964 (class 0 OID 0)
                -- Dependencies: 219
                -- Name: comment_question_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
                --




                --
                -- TOC entry 207 (class 1259 OID 16676)
                -- Name: question; Type: TABLE; Schema: public; Owner: postgres
                --

                CREATE TABLE public.question
                (
                    id bigint NOT NULL,
                    title character varying(100) NOT NULL,
                    description character varying(500) NOT NULL,
                    asked_by bigint NOT NULL,
                    edited_by bigint,
                    is_image smallint DEFAULT 0,
                    res_link character varying(500),
                    upkudo bigint DEFAULT 0,
                    downkudo bigint DEFAULT 0,
                    is_deleted smallint DEFAULT 0,
                    is_banned smallint DEFAULT 0,
                    created_on timestamp
                    without time zone DEFAULT timezone
                    ('utc'::text, now
                    ()),
    last_updated timestamp without time zone,
    thumbnail character varying
                    (500)
);



                    --
                    -- TOC entry 213 (class 1259 OID 16793)
                    -- Name: question_follower; Type: TABLE; Schema: public; Owner: postgres
                    --

                    CREATE TABLE public.question_follower
                    (
                        user_id bigint NOT NULL,
                        question_id bigint NOT NULL
                    );


                    --
                    -- TOC entry 206 (class 1259 OID 16674)
                    -- Name: question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
                    --

                    CREATE SEQUENCE public.question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



                    --
                    -- TOC entry 2965 (class 0 OID 0)
                    -- Dependencies: 206
                    -- Name: question_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
                    --



                    --
                    -- TOC entry 214 (class 1259 OID 16808)
                    -- Name: question_interest; Type: TABLE; Schema: public; Owner: postgres
                    --

                    CREATE TABLE public.question_interest
                    (
                        question_id bigint NOT NULL,
                        interest_id bigint NOT NULL
                    );



                    --
                    -- TOC entry 215 (class 1259 OID 16823)
                    -- Name: question_user_kudo; Type: TABLE; Schema: public; Owner: postgres
                    --

                    CREATE TABLE public.question_user_kudo
                    (
                        question_id bigint NOT NULL,
                        user_id bigint NOT NULL,
                        created_on timestamp
                        without time zone DEFAULT timezone
                        ('utc'::text, now
                        ())
);



                        --
                        -- TOC entry 208 (class 1259 OID 16712)
                        -- Name: seen_answer; Type: TABLE; Schema: public; Owner: postgres
                        --

                        CREATE TABLE public.seen_answer
                        (
                            user_id bigint NOT NULL,
                            answer_id bigint NOT NULL,
                            created_on timestamp
                            without time zone DEFAULT timezone
                            ('utc'::text, now
                            ())
);



                            --
                            -- TOC entry 209 (class 1259 OID 16727)
                            -- Name: seen_question; Type: TABLE; Schema: public; Owner: postgres
                            --

                            CREATE TABLE public.seen_question
                            (
                                user_id bigint NOT NULL,
                                question_id bigint NOT NULL,
                                created_on timestamp
                                without time zone DEFAULT timezone
                                ('utc'::text, now
                                ())
);



                                --
                                -- TOC entry 2763 (class 2604 OID 16633)
                                -- Name: answer id; Type: DEFAULT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.answer
                                ALTER COLUMN id
                                SET
                                DEFAULT nextval
                                ('public.answer_id_seq'::regclass);


                                --
                                -- TOC entry 2782 (class 2604 OID 16875)
                                -- Name: comment_answer id; Type: DEFAULT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.comment_answer
                                ALTER COLUMN id
                                SET
                                DEFAULT nextval
                                ('public.comment_answer_id_seq'::regclass);


                                --
                                -- TOC entry 2785 (class 2604 OID 16898)
                                -- Name: comment_question id; Type: DEFAULT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.comment_question
                                ALTER COLUMN id
                                SET
                                DEFAULT nextval
                                ('public.comment_question_id_seq'::regclass);


                                --
                                -- TOC entry 2771 (class 2604 OID 16679)
                                -- Name: question id; Type: DEFAULT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.question
                                ALTER COLUMN id
                                SET
                                DEFAULT nextval
                                ('public.question_id_seq'::regclass);


                                --
                                -- TOC entry 2789 (class 2606 OID 16645)
                                -- Name: answer answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.answer
                                ADD CONSTRAINT answer_pkey PRIMARY KEY
                                (id);


                                --
                                -- TOC entry 2807 (class 2606 OID 16842)
                                -- Name: answer_user_kudo answer_user_kudo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.answer_user_kudo
                                ADD CONSTRAINT answer_user_kudo_pkey PRIMARY KEY
                                (answer_id, user_id);


                                --
                                -- TOC entry 2809 (class 2606 OID 16882)
                                -- Name: comment_answer comment_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.comment_answer
                                ADD CONSTRAINT comment_answer_pkey PRIMARY KEY
                                (id);


                                --
                                -- TOC entry 2811 (class 2606 OID 16905)
                                -- Name: comment_question comment_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.comment_question
                                ADD CONSTRAINT comment_question_pkey PRIMARY KEY
                                (id);


                                --
                                -- TOC entry 2801 (class 2606 OID 16797)
                                -- Name: question_follower question_follower_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.question_follower
                                ADD CONSTRAINT question_follower_pkey PRIMARY KEY
                                (user_id, question_id);


                                --
                                -- TOC entry 2803 (class 2606 OID 16812)
                                -- Name: question_interest question_interest_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.question_interest
                                ADD CONSTRAINT question_interest_pkey PRIMARY KEY
                                (question_id, interest_id);


                                --
                                -- TOC entry 2793 (class 2606 OID 16690)
                                -- Name: question question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.question
                                ADD CONSTRAINT question_pkey PRIMARY KEY
                                (id);


                                --
                                -- TOC entry 2795 (class 2606 OID 16692)
                                -- Name: question question_title_key; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.question
                                ADD CONSTRAINT question_title_key UNIQUE
                                (title);


                                --
                                -- TOC entry 2805 (class 2606 OID 16827)
                                -- Name: question_user_kudo question_user_kudo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.question_user_kudo
                                ADD CONSTRAINT question_user_kudo_pkey PRIMARY KEY
                                (question_id, user_id);


                                --
                                -- TOC entry 2797 (class 2606 OID 16716)
                                -- Name: seen_answer seen_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.seen_answer
                                ADD CONSTRAINT seen_answer_pkey PRIMARY KEY
                                (user_id, answer_id);


                                --
                                -- TOC entry 2799 (class 2606 OID 16731)
                                -- Name: seen_question seen_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.seen_question
                                ADD CONSTRAINT seen_question_pkey PRIMARY KEY
                                (user_id, question_id);


                                --
                                -- TOC entry 2791 (class 2606 OID 16947)
                                -- Name: answer unique_answer_per_person; Type: CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.answer
                                ADD CONSTRAINT unique_answer_per_person UNIQUE
                                (question_id, answered_by);


                                --
                                -- TOC entry 2812 (class 2606 OID 16646)
                                -- Name: answer answer_answered_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --


                                --
                                -- TOC entry 2813 (class 2606 OID 16932)
                                -- Name: answer answer_question_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.answer
                                ADD CONSTRAINT answer_question_id FOREIGN KEY
                                (question_id) REFERENCES public.question
                                (id);


                                --
                                -- TOC entry 2825 (class 2606 OID 16843)
                                -- Name: answer_user_kudo answer_user_kudo_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --




                                --
                                -- TOC entry 2826 (class 2606 OID 16848)
                                -- Name: answer_user_kudo answer_user_kudo_question_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.answer_user_kudo
                                ADD CONSTRAINT answer_user_kudo_question_id_fkey FOREIGN KEY
                                (answer_id) REFERENCES public.answer
                                (id);


                                --
                                -- TOC entry 2827 (class 2606 OID 16883)
                                -- Name: comment_answer comment_answer_answer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.comment_answer
                                ADD CONSTRAINT comment_answer_answer_id_fkey FOREIGN KEY
                                (answer_id) REFERENCES public.answer
                                (id);


                                --
                                -- TOC entry 2828 (class 2606 OID 16888)
                                -- Name: comment_answer comment_answer_replied_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --




                                --
                                -- TOC entry 2829 (class 2606 OID 16906)
                                -- Name: comment_question comment_question_question_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.comment_question
                                ADD CONSTRAINT comment_question_question_id_fkey FOREIGN KEY
                                (question_id) REFERENCES public.question
                                (id);


                                --
                                -- TOC entry 2830 (class 2606 OID 16911)
                                -- Name: comment_question comment_question_replied_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --




                                --
                                -- TOC entry 2814 (class 2606 OID 16693)
                                -- Name: question question_asked_by_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --




                                --
                                -- TOC entry 2820 (class 2606 OID 16803)
                                -- Name: question_follower question_follower_question_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.question_follower
                                ADD CONSTRAINT question_follower_question_id_fkey FOREIGN KEY
                                (question_id) REFERENCES public.question
                                (id);


                                --
                                -- TOC entry 2819 (class 2606 OID 16798)
                                -- Name: question_follower question_follower_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --




                                --
                                -- TOC entry 2821 (class 2606 OID 16813)
                                -- Name: question_interest question_interest_interest_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --




                                --
                                -- TOC entry 2822 (class 2606 OID 16818)
                                -- Name: question_interest question_interest_question_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.question_interest
                                ADD CONSTRAINT question_interest_question_id_fkey FOREIGN KEY
                                (question_id) REFERENCES public.question
                                (id);


                                --
                                -- TOC entry 2823 (class 2606 OID 16828)
                                -- Name: question_user_kudo question_user_kudo_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --




                                --
                                -- TOC entry 2824 (class 2606 OID 16833)
                                -- Name: question_user_kudo question_user_kudo_question_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.question_user_kudo
                                ADD CONSTRAINT question_user_kudo_question_id_fkey FOREIGN KEY
                                (question_id) REFERENCES public.question
                                (id);


                                --
                                -- TOC entry 2816 (class 2606 OID 16722)
                                -- Name: seen_answer seen_answer_answer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.seen_answer
                                ADD CONSTRAINT seen_answer_answer_id_fkey FOREIGN KEY
                                (answer_id) REFERENCES public.answer
                                (id);


                                --
                                -- TOC entry 2815 (class 2606 OID 16717)
                                -- Name: seen_answer seen_answer_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --




                                --
                                -- TOC entry 2818 (class 2606 OID 16737)
                                -- Name: seen_question seen_question_question_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --

                                ALTER TABLE ONLY public.seen_question
                                ADD CONSTRAINT seen_question_question_id_fkey FOREIGN KEY
                                (question_id) REFERENCES public.question
                                (id);


                                --
                                -- TOC entry 2817 (class 2606 OID 16732)
                                -- Name: seen_question seen_question_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
                                --

                            


-- Completed on 2020-06-10 00:59:44

--
-- PostgreSQL database dump complete
--

