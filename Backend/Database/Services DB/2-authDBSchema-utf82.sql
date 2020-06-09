--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-06-01 11:19:19

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
-- TOC entry 212 (class 1259 OID 16759)
-- Name: interest; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.interest
(
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    is_deleted smallint DEFAULT 0
);

--
-- TOC entry 211 (class 1259 OID 16757)
-- Name: interest_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.interest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 211
-- Name: interest_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.interest_id_seq
OWNED BY public.interest.id;


--
-- TOC entry 210 (class 1259 OID 16742)
-- Name: people_follower; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.people_follower
(
    follower_id bigint NOT NULL,
    followee_id bigint NOT NULL
);



--
-- TOC entry 223 (class 1259 OID 16988)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role
(
    id bigint NOT NULL,
    name character varying(50) NOT NULL
);



--
-- TOC entry 222 (class 1259 OID 16986)
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 222
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq
OWNED BY public.role.id;


--
-- TOC entry 203 (class 1259 OID 16564)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user"
(
    id bigint NOT NULL,
    username character varying(50) NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    bio character varying(100),
    about_me character varying(250),
    profile_pic character varying(250),
    reputations bigint DEFAULT 0,
    is_deleted smallint DEFAULT 0,
    is_banned smallint DEFAULT 0,
    created_on timestamp
    without time zone DEFAULT timezone
    ('utc'::text, now
    ()),
    last_login timestamp without time zone,
    password character varying
    (200) NOT NULL
);

    --
    -- TOC entry 202 (class 1259 OID 16562)
    -- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
    --

    CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



    --
    -- TOC entry 2933 (class 0 OID 0)
    -- Dependencies: 202
    -- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
    --


    --
    -- TOC entry 221 (class 1259 OID 16916)
    -- Name: user_interest_reputation; Type: TABLE; Schema: public; Owner: postgres
    --

    CREATE TABLE public.user_interest_reputation
    (
        user_id bigint NOT NULL,
        interest_id bigint NOT NULL,
        reputation bigint DEFAULT 0
    );



    --
    -- TOC entry 224 (class 1259 OID 16997)
    -- Name: user_role; Type: TABLE; Schema: public; Owner: postgres
    --

    CREATE TABLE public.user_role
    (
        user_id bigint NOT NULL,
        role_id bigint NOT NULL
    );



    --
    -- TOC entry 2768 (class 2604 OID 16762)
    -- Name: interest id; Type: DEFAULT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.interest
    ALTER COLUMN id
    SET
    DEFAULT nextval
    ('public.interest_id_seq'::regclass);


    --
    -- TOC entry 2771 (class 2604 OID 16991)
    -- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.role
    ALTER COLUMN id
    SET
    DEFAULT nextval
    ('public.role_id_seq'::regclass);


    --
    -- TOC entry 2763 (class 2604 OID 16567)
    -- Name: user id; Type: DEFAULT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public."user"
    ALTER COLUMN id
    SET
    DEFAULT nextval
    ('public.user_id_seq'::regclass);


    --
    -- TOC entry 2781 (class 2606 OID 16767)
    -- Name: interest interest_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.interest
    ADD CONSTRAINT interest_name_key UNIQUE
    (name);


    --
    -- TOC entry 2783 (class 2606 OID 16765)
    -- Name: interest interest_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.interest
    ADD CONSTRAINT interest_pkey PRIMARY KEY
    (id);


    --
    -- TOC entry 2779 (class 2606 OID 16746)
    -- Name: people_follower people_follower_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.people_follower
    ADD CONSTRAINT people_follower_pkey PRIMARY KEY
    (follower_id, followee_id);


    --
    -- TOC entry 2789 (class 2606 OID 16995)
    -- Name: role role_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_name_key UNIQUE
    (name);


    --
    -- TOC entry 2791 (class 2606 OID 16993)
    -- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY
    (id);


    --
    -- TOC entry 2773 (class 2606 OID 16580)
    -- Name: user user_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_email_key UNIQUE
    (email);


    --
    -- TOC entry 2785 (class 2606 OID 16921)
    -- Name: user_interest_reputation user_interest_reputation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.user_interest_reputation
    ADD CONSTRAINT user_interest_reputation_pkey PRIMARY KEY
    (user_id, interest_id);


    --
    -- TOC entry 2787 (class 2606 OID 16965)
    -- Name: user_interest_reputation user_interest_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.user_interest_reputation
    ADD CONSTRAINT user_interest_unique UNIQUE
    (user_id, interest_id);


    --
    -- TOC entry 2775 (class 2606 OID 16576)
    -- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY
    (id);


    --
    -- TOC entry 2793 (class 2606 OID 17001)
    -- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY
    (user_id, role_id);


    --
    -- TOC entry 2777 (class 2606 OID 16578)
    -- Name: user user_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_username_key UNIQUE
    (username);


    --
    -- TOC entry 2795 (class 2606 OID 16752)
    -- Name: people_follower people_follower_followee_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.people_follower
    ADD CONSTRAINT people_follower_followee_id_fkey FOREIGN KEY
    (followee_id) REFERENCES public."user"
    (id);


    --
    -- TOC entry 2794 (class 2606 OID 16747)
    -- Name: people_follower people_follower_follower_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.people_follower
    ADD CONSTRAINT people_follower_follower_id_fkey FOREIGN KEY
    (follower_id) REFERENCES public."user"
    (id);


    --
    -- TOC entry 2797 (class 2606 OID 16927)
    -- Name: user_interest_reputation user_interest_reputation_interest_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.user_interest_reputation
    ADD CONSTRAINT user_interest_reputation_interest_id_fkey FOREIGN KEY
    (interest_id) REFERENCES public.interest
    (id);


    --
    -- TOC entry 2796 (class 2606 OID 16922)
    -- Name: user_interest_reputation user_interest_reputation_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.user_interest_reputation
    ADD CONSTRAINT user_interest_reputation_user_id_fkey FOREIGN KEY
    (user_id) REFERENCES public."user"
    (id);


    --
    -- TOC entry 2799 (class 2606 OID 17007)
    -- Name: user_role user_role_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_role_id_fkey FOREIGN KEY
    (role_id) REFERENCES public.role
    (id);


    --
    -- TOC entry 2798 (class 2606 OID 17002)
    -- Name: user_role user_role_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
    --

    ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_user_id_fkey FOREIGN KEY
    (user_id) REFERENCES public."user"
    (id);


-- Completed on 2020-06-01 11:19:23

--
-- PostgreSQL database dump complete
--

