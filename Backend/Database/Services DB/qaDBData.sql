--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-06-10 01:00:48

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

--
-- TOC entry 2960 (class 0 OID 16676)
-- Dependencies: 207
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.question
    (id, title, description, asked_by, edited_by, is_image, res_link, upkudo, downkudo, is_deleted, is_banned, created_on, last_updated, thumbnail)
VALUES
    (3, 'What is the best way to introduce myself in an interview', 'I quite a low struggle in an interview', 5, NULL, 0, NULL, 0, 0, 0, 0, '2020-05-02 20:56:15.401088', NULL, NULL);
INSERT INTO public.question
    (id, title, description, asked_by, edited_by, is_image, res_link, upkudo, downkudo, is_deleted, is_banned, created_on, last_updated, thumbnail)
VALUES
    (4, 'How you spend time during lockdown productively', 'Feeling bored, just wanted something to get motivated ', 3, NULL, 0, NULL, 0, 0, 0, 0, '2020-05-02 21:00:08.095081', NULL, NULL);
INSERT INTO public.question
    (id, title, description, asked_by, edited_by, is_image, res_link, upkudo, downkudo, is_deleted, is_banned, created_on, last_updated, thumbnail)
VALUES
    (5, 'What is your stand on china behavior on coronavirus', 'China has been neglecting and hiding the original data', 1, NULL, 0, NULL, 0, 0, 0, 0, '2020-05-02 21:02:02.789175', NULL, NULL);
INSERT INTO public.question
    (id, title, description, asked_by, edited_by, is_image, res_link, upkudo, downkudo, is_deleted, is_banned, created_on, last_updated, thumbnail)
VALUES
    (6, 'How do you look when you smile', 'Smile Please :)', 1, NULL, 0, NULL, 0, 0, 0, 0, '2020-05-02 21:02:36.56848', NULL, NULL);
INSERT INTO public.question
    (id, title, description, asked_by, edited_by, is_image, res_link, upkudo, downkudo, is_deleted, is_banned, created_on, last_updated, thumbnail)
VALUES
    (15, 'why is modi so slow', 'dummy description', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-05-14 02:17:54.437', '2020-05-14 02:17:54.437', NULL);
INSERT INTO public.question
    (id, title, description, asked_by, edited_by, is_image, res_link, upkudo, downkudo, is_deleted, is_banned, created_on, last_updated, thumbnail)
VALUES
    (1, 'why is modi so slow while giving speech', 'dummy description', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.question
    (id, title, description, asked_by, edited_by, is_image, res_link, upkudo, downkudo, is_deleted, is_banned, created_on, last_updated, thumbnail)
VALUES
    (17, 'What is the best programming language for competitive programming', 'for codeforces', 3, NULL, 0, NULL, 0, 0, 0, 0, '2020-05-15 22:29:39.722', '2020-05-15 22:29:39.722', NULL);
INSERT INTO public.question
    (id, title, description, asked_by, edited_by, is_image, res_link, upkudo, downkudo, is_deleted, is_banned, created_on, last_updated, thumbnail)
VALUES
    (18, 'What is the best practice for competitive programming', 'for codeforces', 3, NULL, 0, NULL, 0, 0, 0, 0, '2020-05-15 23:43:30.223', '2020-05-15 23:43:30.223', NULL);
INSERT INTO public.question
    (id, title, description, asked_by, edited_by, is_image, res_link, upkudo, downkudo, is_deleted, is_banned, created_on, last_updated, thumbnail)
VALUES
    (19, 'What is the best practice for interviews at amazon', 'for codeforces', 5, NULL, 0, NULL, 0, 0, 0, 0, '2020-05-15 23:47:48.381', '2020-05-15 23:47:48.381', NULL);


--
-- TOC entry 2958 (class 0 OID 16630)
-- Dependencies: 205
-- Data for Name: answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.answer
    (id, is_image, res_link, caption, views, answered_by, upkudo, downkudo, is_deleted, is_shattered, created_on, last_updated, thumbnail, question_id)
VALUES
    (1, 0, 'placeholder for resource-link', NULL, 0, 2, 0, 0, 0, 0, '2020-05-02 22:00:59.748025', '2020-05-02 22:00:59.748025', NULL, 1);
INSERT INTO public.answer
    (id, is_image, res_link, caption, views, answered_by, upkudo, downkudo, is_deleted, is_shattered, created_on, last_updated, thumbnail, question_id)
VALUES
    (3, 0, 'placeholder for resource-link', NULL, 0, 3, 0, 2, 0, 0, '2020-05-03 12:15:53.764806', '2020-05-03 12:15:53.764806', 'thumnail placeholder', 3);
INSERT INTO public.answer
    (id, is_image, res_link, caption, views, answered_by, upkudo, downkudo, is_deleted, is_shattered, created_on, last_updated, thumbnail, question_id)
VALUES
    (5, NULL, 'some link', 'Here is a video of how I use to introduce across all my interviews', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3);
INSERT INTO public.answer
    (id, is_image, res_link, caption, views, answered_by, upkudo, downkudo, is_deleted, is_shattered, created_on, last_updated, thumbnail, question_id)
VALUES
    (7, 0, 'some link', 'changed caption', 0, 4, 0, 0, 0, 0, '2020-05-15 19:55:15.21', '2020-05-15 19:55:15.21', NULL, 3);
INSERT INTO public.answer
    (id, is_image, res_link, caption, views, answered_by, upkudo, downkudo, is_deleted, is_shattered, created_on, last_updated, thumbnail, question_id)
VALUES
    (17, 0, 'www.blah.com', 'Wow! you really did great job explaining.', 0, 6, 0, 0, 0, 0, '2020-05-21 01:00:39.354', '2020-05-21 01:00:39.354', NULL, 4);


--
-- TOC entry 2966 (class 0 OID 16838)
-- Dependencies: 216
-- Data for Name: answer_user_kudo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.answer_user_kudo
    (answer_id, user_id, created_on)
VALUES
    (1, 3, '2020-05-20 06:00:48.050918');
INSERT INTO public.answer_user_kudo
    (answer_id, user_id, created_on)
VALUES
    (7, 2, '2020-05-20 15:15:42.45');
INSERT INTO public.answer_user_kudo
    (answer_id, user_id, created_on)
VALUES
    (7, 6, '2020-05-20 15:37:44.808');


--
-- TOC entry 2968 (class 0 OID 16872)
-- Dependencies: 218
-- Data for Name: comment_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.comment_answer
    (id, content, upkudo, answer_id, replied_by, created_on)
VALUES
    (13, 'Wow! you really did awesome job explaining.', 0, 17, 6, '2020-05-21 01:21:38.089');


--
-- TOC entry 2970 (class 0 OID 16895)
-- Dependencies: 220
-- Data for Name: comment_question; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.comment_question
    (id, content, upkudo, question_id, replied_by, created_on)
VALUES
    (2, 'nice-video', 1, 3, 3, '2020-05-03 13:09:47.098392');


--
-- TOC entry 2963 (class 0 OID 16793)
-- Dependencies: 213
-- Data for Name: question_follower; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.question_follower
    (user_id, question_id)
VALUES
    (3, 1);


--
-- TOC entry 2964 (class 0 OID 16808)
-- Dependencies: 214
-- Data for Name: question_interest; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.question_interest
    (question_id, interest_id)
VALUES
    (1, 1);


--
-- TOC entry 2965 (class 0 OID 16823)
-- Dependencies: 215
-- Data for Name: question_user_kudo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.question_user_kudo
    (question_id, user_id, created_on)
VALUES
    (3, 1, '2020-05-20 06:01:44.771886');
INSERT INTO public.question_user_kudo
    (question_id, user_id, created_on)
VALUES
    (6, 4, '2020-05-20 20:59:34.442');


--
-- TOC entry 2961 (class 0 OID 16712)
-- Dependencies: 208
-- Data for Name: seen_answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.seen_answer
    (user_id, answer_id, created_on)
VALUES
    (1, 1, '2020-05-20 06:04:09.543574');


--
-- TOC entry 2962 (class 0 OID 16727)
-- Dependencies: 209
-- Data for Name: seen_question; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.seen_question
    (user_id, question_id, created_on)
VALUES
    (1, 1, '2020-05-20 06:04:21.823943');


--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 204
-- Name: answer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.answer_id_seq', 17, true);


--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 217
-- Name: comment_answer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comment_answer_id_seq', 13, true);


--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 219
-- Name: comment_question_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comment_question_id_seq', 3, true);


--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 206
-- Name: question_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.question_id_seq', 19, true);


-- Completed on 2020-06-10 01:00:54

--
-- PostgreSQL database dump complete
--

