--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-06-01 11:32:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2930 (class 0 OID 16759)
-- Dependencies: 212
-- Data for Name: interest; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.interest (id, name, is_deleted) VALUES (1, 'science', 0);
INSERT INTO public.interest (id, name, is_deleted) VALUES (3, 'technology', 0);


--
-- TOC entry 2927 (class 0 OID 16564)
-- Dependencies: 203
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."user" (id, username, first_name, last_name, email, bio, about_me, profile_pic, reputations, is_deleted, is_banned, created_on, last_login, password) VALUES (2, 'pawan41', 'pawan', 'kumar', 'pawan1469', NULL, NULL, NULL, 0, 0, 0, '2020-05-02 20:34:26.050014', NULL, '$2a$10$7UdXSYZs5ahrCwMzhN09POb4/vOHRkG/insRnR59K1oEVPN.Q4sMC');
INSERT INTO public."user" (id, username, first_name, last_name, email, bio, about_me, profile_pic, reputations, is_deleted, is_banned, created_on, last_login, password) VALUES (3, 'parveen', 'parveen', 'kumar', 'parveen', NULL, NULL, NULL, 0, 0, 0, '2020-05-02 20:35:38.265736', NULL, '$2a$10$7UdXSYZs5ahrCwMzhN09POb4/vOHRkG/insRnR59K1oEVPN.Q4sMC');
INSERT INTO public."user" (id, username, first_name, last_name, email, bio, about_me, profile_pic, reputations, is_deleted, is_banned, created_on, last_login, password) VALUES (4, 'ajay78', 'ajay', 'pandey', 'ajay@gmail.com', NULL, NULL, NULL, 0, 0, 0, '2020-05-02 20:36:13.501697', NULL, '$2a$10$7UdXSYZs5ahrCwMzhN09POb4/vOHRkG/insRnR59K1oEVPN.Q4sMC');
INSERT INTO public."user" (id, username, first_name, last_name, email, bio, about_me, profile_pic, reputations, is_deleted, is_banned, created_on, last_login, password) VALUES (5, 'sunand77', 'sunand', 'pal', 'sunand@yahoo.com', NULL, NULL, NULL, 0, 0, 0, '2020-05-02 20:40:02.148601', NULL, '$2a$10$7UdXSYZs5ahrCwMzhN09POb4/vOHRkG/insRnR59K1oEVPN.Q4sMC');
INSERT INTO public."user" (id, username, first_name, last_name, email, bio, about_me, profile_pic, reputations, is_deleted, is_banned, created_on, last_login, password) VALUES (6, 'happy', 'anuj', 'gupta', 'abc@gmail.com', NULL, NULL, NULL, 25, NULL, NULL, '2020-05-03 13:55:09.974401', '2020-05-03 13:55:09.974401', '$2a$10$7UdXSYZs5ahrCwMzhN09POb4/vOHRkG/insRnR59K1oEVPN.Q4sMC');
INSERT INTO public."user" (id, username, first_name, last_name, email, bio, about_me, profile_pic, reputations, is_deleted, is_banned, created_on, last_login, password) VALUES (1, 'nitesh12', 'nitesh', 'tiwari', 'niteesh555786', NULL, NULL, NULL, 0, 0, 0, '2020-05-02 20:33:58.050545', NULL, 'okdone12');


--
-- TOC entry 2928 (class 0 OID 16742)
-- Dependencies: 210
-- Data for Name: people_follower; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.people_follower (follower_id, followee_id) VALUES (2, 3);


--
-- TOC entry 2933 (class 0 OID 16988)
-- Dependencies: 223
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (id, name) VALUES (1, 'admin');
INSERT INTO public.role (id, name) VALUES (2, 'user');
INSERT INTO public.role (id, name) VALUES (3, 'guest');


--
-- TOC entry 2931 (class 0 OID 16916)
-- Dependencies: 221
-- Data for Name: user_interest_reputation; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_interest_reputation (user_id, interest_id, reputation) VALUES (1, 3, 0);


--
-- TOC entry 2934 (class 0 OID 16997)
-- Dependencies: 224
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_role (user_id, role_id) VALUES (4, 2);


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 211
-- Name: interest_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.interest_id_seq', 5, true);


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 222
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 4, true);


--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 202
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 6, true);


-- Completed on 2020-06-01 11:32:13

--
-- PostgreSQL database dump complete
--

