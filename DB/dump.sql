--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.14
-- Dumped by pg_dump version 9.5.14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    c text
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_seq OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- Name: comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment (
    id integer NOT NULL,
    sender_id integer NOT NULL,
    date text NOT NULL,
    text text,
    news_id integer
);


ALTER TABLE public.comment OWNER TO postgres;

--
-- Name: comment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comment_id_seq OWNER TO postgres;

--
-- Name: comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;


--
-- Name: file; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.file (
    id integer NOT NULL,
    news_id integer
);


ALTER TABLE public.file OWNER TO postgres;

--
-- Name: file_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.file_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.file_id_seq OWNER TO postgres;

--
-- Name: file_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.file_id_seq OWNED BY public.file.id;


--
-- Name: news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news (
    id integer NOT NULL,
    author_id integer,
    topic_id integer,
    header text NOT NULL,
    text text NOT NULL,
    accepted boolean NOT NULL,
    date character varying(30) NOT NULL,
    category integer
);


ALTER TABLE public.news OWNER TO postgres;

--
-- Name: news_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_id_seq OWNER TO postgres;

--
-- Name: news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.news_id_seq OWNED BY public.news.id;


--
-- Name: tag; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tag (
    id integer NOT NULL,
    t text
);


ALTER TABLE public.tag OWNER TO postgres;

--
-- Name: tag_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tag_id_seq OWNER TO postgres;

--
-- Name: tag_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tag_id_seq OWNED BY public.tag.id;


--
-- Name: tags_to_news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tags_to_news (
    id integer NOT NULL,
    tag_id integer NOT NULL,
    news_id integer NOT NULL
);


ALTER TABLE public.tags_to_news OWNER TO postgres;

--
-- Name: tags_to_news_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tags_to_news_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tags_to_news_id_seq OWNER TO postgres;

--
-- Name: tags_to_news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tags_to_news_id_seq OWNED BY public.tags_to_news.id;


--
-- Name: theme; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.theme (
    id integer NOT NULL,
    topic_id integer,
    creator_id integer,
    name text NOT NULL,
    date text
);


ALTER TABLE public.theme OWNER TO postgres;

--
-- Name: theme_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.theme_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.theme_id_seq OWNER TO postgres;

--
-- Name: theme_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.theme_id_seq OWNED BY public.theme.id;


--
-- Name: topic; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topic (
    id integer NOT NULL,
    nam text,
    link text
);


ALTER TABLE public.topic OWNER TO postgres;

--
-- Name: topic_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.topic_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.topic_id_seq OWNER TO postgres;

--
-- Name: topic_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.topic_id_seq OWNED BY public.topic.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    admin boolean NOT NULL,
    name text NOT NULL,
    login character varying(30) NOT NULL,
    description text,
    passw text NOT NULL,
    picpath text
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment ALTER COLUMN id SET DEFAULT nextval('public.comment_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.file ALTER COLUMN id SET DEFAULT nextval('public.file_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news ALTER COLUMN id SET DEFAULT nextval('public.news_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag ALTER COLUMN id SET DEFAULT nextval('public.tag_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags_to_news ALTER COLUMN id SET DEFAULT nextval('public.tags_to_news_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.theme ALTER COLUMN id SET DEFAULT nextval('public.theme_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic ALTER COLUMN id SET DEFAULT nextval('public.topic_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, c) FROM stdin;
1	No category
3	Nature
2	Politics
4	Celebrities
5	Army/War
6	Tech
\.


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 3, true);


--
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comment (id, sender_id, date, text, news_id) FROM stdin;
3	10	Fri Nov 02 19:46:38 MSK 2018	hello world	1
4	10	Fri Nov 02 19:46:47 MSK 2018	im new here	1
5	10	Fri Nov 02 19:47:13 MSK 2018	wtf is 	1
6	6	Tue Nov 06 21:42:26 MSK 2018	amazing pic bro	15
7	19	Tue Nov 06 21:46:49 MSK 2018	look at my pic boys	1
8	6	Fri Nov 09 14:26:58 MSK 2018	NO!	13
9	6	Fri Nov 09 14:27:54 MSK 2018	s dr	19
2	6	Fri Nov 02 14:43:17 MSK 2018	test	1
\.


--
-- Name: comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comment_id_seq', 9, true);


--
-- Data for Name: file; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.file (id, news_id) FROM stdin;
\.


--
-- Name: file_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.file_id_seq', 1, false);


--
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.news (id, author_id, topic_id, header, text, accepted, date, category) FROM stdin;
2	27	1	&#1054;&#1073;&#1084;&#1077;&#1085; &#1091;&#1076;&#1072;&#1088;&#1072;&#1084;&#1080;: &#1048;&#1079;&#1088;&#1072;&#1080;&#1083;&#1100; &#1080; &#1055;&#1072;&#1083;&#1077;&#1089;&#1090;&#1080;&#1085;&#1072; &#1074;&#1086;&#1079;&#1086;&#1073;&#1085;&#1086;&#1074;&#1083;&#1103;&#1102;&#1090; &#1082;&#1086;&#1085;&#1092;&#1083;&#1080;&#1082;&#1090;	&#1052;&#1077;&#1078;&#1076;&#1091; &#1048;&#1079;&#1088;&#1072;&#1080;&#1083;&#1077;&#1084; &#1080; &#1055;&#1072;&#1083;&#1077;&#1089;&#1090;&#1080;&#1085;&#1086;&#1081; &#1089;&#1085;&#1086;&#1074;&#1072; &#1087;&#1088;&#1086;&#1080;&#1089;&#1093;&#1086;&#1076;&#1080;&#1090; &#1101;&#1089;&#1082;&#1072;&#1083;&#1072;&#1094;&#1080;&#1103; &#1076;&#1083;&#1080;&#1090;&#1077;&#1083;&#1100;&#1085;&#1086;&#1075;&#1086; &#1082;&#1086;&#1085;&#1092;&#1083;&#1080;&#1082;&#1090;&#1072;  &#1089;&#1090;&#1086;&#1088;&#1086;&#1085;&#1099; &#1086;&#1073;&#1084;&#1077;&#1085;&#1103;&#1083;&#1080;&#1089;&#1100; &#1088;&#1072;&#1082;&#1077;&#1090;&#1085;&#1099;&#1084;&#1080; &#1091;&#1076;&#1072;&#1088;&#1072;&#1084;&#1080;, &#1074; &#1088;&#1077;&#1079;&#1091;&#1083;&#1100;&#1090;&#1072;&#1090;&#1077; &#1082;&#1086;&#1090;&#1086;&#1088;&#1099;&#1093; &#1077;&#1089;&#1090;&#1100; &#1087;&#1086;&#1075;&#1080;&#1073;&#1096;&#1080;&#1077;. &#1052;&#1086;&#1089;&#1082;&#1074;&#1072; &#1086;&#1089;&#1091;&#1076;&#1080;&#1083;&#1072; &#1087;&#1088;&#1086;&#1080;&#1089;&#1093;&#1086;&#1076;&#1103;&#1097;&#1077;&#1077; &#1080; &#1087;&#1088;&#1080;&#1079;&#1074;&#1072;&#1083;&#1072; &#1082; &#1076;&#1077;&#1101;&#1089;&#1082;&#1072;&#1083;&#1072;&#1094;&#1080;&#1080; &#1082;&#1086;&#1085;&#1092;&#1083;&#1080;&#1082;&#1090;&#1072;. &#1050;&#1088;&#1086;&#1084;&#1077; &#1090;&#1086;&#1075;&#1086;, &#1056;&#1086;&#1089;&#1089;&#1080;&#1103; &#1087;&#1088;&#1077;&#1076;&#1083;&#1086;&#1078;&#1080;&#1083;&#1072; &#1074;&#1086;&#1089;&#1089;&#1090;&#1072;&#1085;&#1086;&#1074;&#1080;&#1090;&#1100; &#1072;&#1088;&#1072;&#1073;&#1086;-&#1080;&#1079;&#1088;&#1072;&#1080;&#1083;&#1100;&#1089;&#1082;&#1080;&#1081; &#1076;&#1080;&#1072;&#1083;&#1086;&#1075; &#1085;&#1072; &#1073;&#1072;&#1079;&#1077; &#1080;&#1079;&#1074;&#1077;&#1089;&#1090;&#1085;&#1099;&#1093; &#1088;&#1077;&#1096;&#1077;&#1085;&#1080;&#1081; &#1057;&#1086;&#1074;&#1077;&#1090;&#1072; &#1073;&#1077;&#1079;&#1086;&#1087;&#1072;&#1089;&#1085;&#1086;&#1089;&#1090;&#1080; &#1080; &#1043;&#1077;&#1085;&#1077;&#1088;&#1072;&#1083;&#1100;&#1085;&#1086;&#1081; &#1072;&#1089;&#1089;&#1072;&#1084;&#1073;&#1083;&#1077;&#1080; &#1054;&#1054;&#1053;, &#1072; &#1090;&#1072;&#1082;&#1078;&#1077; &#1040;&#1088;&#1072;&#1073;&#1089;&#1082;&#1086;&#1081; &#1084;&#1080;&#1088;&#1085;&#1086;&#1081; &#1080;&#1085;&#1080;&#1094;&#1080;&#1072;&#1090;&#1080;&#1074;&#1099;.	t	Mon Nov 12 23:13:57 MSK 2018	5
13	10	1	IM BEST FRONTEND DEVELOPER	TIMURTIMURTIMURTIMURTIMURTIMURTIMURTIMURTIMURTIMUR	t	Fri Nov 02 19:46:25 MSK 2018	4
1	6	1	EXAMPLE TOPIC	The main reason for going to university is to get an academic qualification, but personally, I think that the social side of things and developing as a person are equally important. The good thing about university life is that you are left to your own devices, unlike school, where you're told what to do and how to do it. It's important to socialise and meet new friends. lt takes some people longer than others. If a student has any problems, about anything, there's student counselling available at all universities, where they try and help out as much as possible. I've been a volunteer for our Student Counselling Scheme for some time now. I've found it very rewarding, not only with helping other students through university life but also as an extra-curricular activity.	t	Yesterday	1
18	26	1	IM DIMA DIMA             	HEHEHEHEHEHEHEEHEH	t	Fri Nov 09 12:55:01 MSK 2018	2
19	6	1	DR ARSA	ARS S DR	t	Fri Nov 09 14:27:21 MSK 2018	4
15	19	1	LOOOK AT THE PIC!!!  	THATS AWESOME                    	t	Tue Nov 06 21:40:22 MSK 2018	4
21	27	1	&#1056;&#1086;&#1089;&#1089;&#1080;&#1081;&#1089;&#1082;&#1080;&#1077; &#1074;&#1086;&#1077;&#1085;&#1085;&#1099;&#1077; &#1079;&#1072;&#1092;&#1080;&#1082;&#1089;&#1080;&#1088;&#1086;&#1074;&#1072;&#1083;&#1080; &#1095;&#1077;&#1090;&#1099;&#1088;&#1077; &#1086;&#1073;&#1089;&#1090;&#1088;&#1077;&#1083;&#1072; &#1074; &#1087;&#1088;&#1080;&#1075;&#1086;&#1088;&#1086;&#1076;&#1072;&#1093; &#1040;&#1083;&#1077;&#1087;&#1087;&#1086;	&#1041;&#1086;&#1077;&#1074;&#1080;&#1082;&#1080; &#1095;&#1077;&#1090;&#1099;&#1088;&#1077; &#1088;&#1072;&#1079;&#1072; &#1074; &#1090;&#1077;&#1095;&#1077;&#1085;&#1080;&#1077; &#1089;&#1091;&#1090;&#1086;&#1082; &#1086;&#1073;&#1089;&#1090;&#1088;&#1077;&#1083;&#1103;&#1083;&#1080; &#1079;&#1072;&#1087;&#1072;&#1076;&#1085;&#1099;&#1081; &#1087;&#1088;&#1080;&#1075;&#1086;&#1088;&#1086;&#1076; &#1040;&#1083;&#1077;&#1087;&#1087;&#1086;, &#1089;&#1086;&#1086;&#1073;&#1097;&#1080;&#1083; &#1088;&#1091;&#1082;&#1086;&#1074;&#1086;&#1076;&#1080;&#1090;&#1077;&#1083;&#1100; &#1088;&#1086;&#1089;&#1089;&#1080;&#1081;&#1089;&#1082;&#1086;&#1075;&#1086; &#1062;&#1077;&#1085;&#1090;&#1088;&#1072; &#1087;&#1086; &#1087;&#1088;&#1080;&#1084;&#1080;&#1088;&#1077;&#1085;&#1080;&#1102; &#1074;&#1088;&#1072;&#1078;&#1076;&#1091;&#1102;&#1097;&#1080;&#1093; &#1089;&#1090;&#1086;&#1088;&#1086;&#1085; &#1074; &#1057;&#1080;&#1088;&#1080;&#1080; &#1075;&#1077;&#1085;&#1077;&#1088;&#1072;&#1083;-&#1083;&#1077;&#1081;&#1090;&#1077;&#1085;&#1072;&#1085;&#1090; &#1042;&#1083;&#1072;&#1076;&#1080;&#1084;&#1080;&#1088; &#1057;&#1072;&#1074;&#1095;&#1077;&#1085;&#1082;&#1086; &#1074; &#1074;&#1086;&#1089;&#1082;&#1088;&#1077;&#1089;&#1077;&#1085;&#1100;&#1077;.	f	Mon Nov 12 23:18:07 MSK 2018	5
\.


--
-- Name: news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_id_seq', 21, true);


--
-- Data for Name: tag; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tag (id, t) FROM stdin;
1	tag1
2	tag2
3	tag3
\.


--
-- Name: tag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tag_id_seq', 3, true);


--
-- Data for Name: tags_to_news; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tags_to_news (id, tag_id, news_id) FROM stdin;
1	1	1
3	2	1
4	3	1
\.


--
-- Name: tags_to_news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tags_to_news_id_seq', 8, true);


--
-- Data for Name: theme; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.theme (id, topic_id, creator_id, name, date) FROM stdin;
\.


--
-- Name: theme_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.theme_id_seq', 1, false);


--
-- Data for Name: topic; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.topic (id, nam, link) FROM stdin;
1	example	example
\.


--
-- Name: topic_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.topic_id_seq', 1, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, admin, name, login, description, passw, picpath) FROM stdin;
6	t	vova	vova	haha	892a9944cf14665375630c06a1902152	../../front/src/img_avatar.png
19	f	Vova	Vova1		5ce0a3d93cc933d8bdcc1a4b0f57b0b2	../UserPics/1541529574767Auth.png
23	f	qq	Defa		eef6231991b4e8be1f7f22867a6441ea	../../front/src/img_avatar.png
10	f	Crt	Timur		43915ea0bfcc4ec0dd3184e75e012d41	../../front/src/img_avatar.png
26	f	Dimoon	Dima		5fed60704b45e9c7a2c3ec4d983b534e	../UserPics/dima.jpg
27	f	MAAAN	ArmyMan		5fed60704b45e9c7a2c3ec4d983b534e	../UserPics/warFace.png
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 27, true);


--
-- Name: category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: comment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- Name: file_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.file
    ADD CONSTRAINT file_pkey PRIMARY KEY (id);


--
-- Name: news_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


--
-- Name: tag_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- Name: tags_to_news_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags_to_news
    ADD CONSTRAINT tags_to_news_pkey PRIMARY KEY (id);


--
-- Name: theme_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.theme
    ADD CONSTRAINT theme_pkey PRIMARY KEY (id);


--
-- Name: topic_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_pkey PRIMARY KEY (id);


--
-- Name: users_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_login_key UNIQUE (login);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: comment_news_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_news_id_fkey FOREIGN KEY (news_id) REFERENCES public.news(id);


--
-- Name: comment_sender_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.users(id);


--
-- Name: file_news_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.file
    ADD CONSTRAINT file_news_id_fkey FOREIGN KEY (news_id) REFERENCES public.news(id);


--
-- Name: news_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.users(id);


--
-- Name: news_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_category_fkey FOREIGN KEY (category) REFERENCES public.category(id);


--
-- Name: news_topic_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_topic_id_fkey FOREIGN KEY (topic_id) REFERENCES public.topic(id);


--
-- Name: tags_to_news_news_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags_to_news
    ADD CONSTRAINT tags_to_news_news_id_fkey FOREIGN KEY (news_id) REFERENCES public.news(id);


--
-- Name: tags_to_news_tag_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tags_to_news
    ADD CONSTRAINT tags_to_news_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES public.tag(id);


--
-- Name: theme_creator_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.theme
    ADD CONSTRAINT theme_creator_id_fkey FOREIGN KEY (creator_id) REFERENCES public.users(id);


--
-- Name: theme_topic_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.theme
    ADD CONSTRAINT theme_topic_id_fkey FOREIGN KEY (topic_id) REFERENCES public.topic(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

