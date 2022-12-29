--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1 (Ubuntu 15.1-1.pgdg22.04+1)
-- Dumped by pg_dump version 15.1 (Ubuntu 15.1-1.pgdg22.04+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: tb_cliente; Type: TABLE; Schema: public; Owner: loja_adm
--

CREATE TABLE public.tb_cliente (
    id bigint NOT NULL,
    nome character varying(50) NOT NULL,
    cpf bigint NOT NULL,
    tel bigint NOT NULL,
    endereco character varying(50) NOT NULL,
    numero bigint NOT NULL,
    cidade character varying(50) NOT NULL,
    estado character varying(50) NOT NULL,
    email character varying(40) NOT NULL
);


ALTER TABLE public.tb_cliente OWNER TO loja_adm;

--
-- Name: sq_cliente; Type: SEQUENCE; Schema: public; Owner: loja_adm
--

CREATE SEQUENCE public.sq_cliente
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sq_cliente OWNER TO loja_adm;

--
-- Name: sq_cliente; Type: SEQUENCE OWNED BY; Schema: public; Owner: loja_adm
--

ALTER SEQUENCE public.sq_cliente OWNED BY public.tb_cliente.id;


--
-- Name: sq_estoque; Type: SEQUENCE; Schema: public; Owner: loja_adm
--

CREATE SEQUENCE public.sq_estoque
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sq_estoque OWNER TO loja_adm;

--
-- Name: tb_produto; Type: TABLE; Schema: public; Owner: loja_adm
--

CREATE TABLE public.tb_produto (
    id bigint NOT NULL,
    codigo character varying(10) NOT NULL,
    nome character varying(50) NOT NULL,
    descricao character varying(100) NOT NULL,
    valor numeric(10,2) NOT NULL,
    unidade_de_mensuracao character varying(40) NOT NULL
);


ALTER TABLE public.tb_produto OWNER TO loja_adm;

--
-- Name: sq_produto; Type: SEQUENCE; Schema: public; Owner: loja_adm
--

CREATE SEQUENCE public.sq_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sq_produto OWNER TO loja_adm;

--
-- Name: sq_produto; Type: SEQUENCE OWNED BY; Schema: public; Owner: loja_adm
--

ALTER SEQUENCE public.sq_produto OWNED BY public.tb_produto.id;


--
-- Name: tb_produto_quantidade; Type: TABLE; Schema: public; Owner: loja_adm
--

CREATE TABLE public.tb_produto_quantidade (
    id bigint NOT NULL,
    id_produto_fk bigint NOT NULL,
    id_venda_fk bigint NOT NULL,
    quantidade integer NOT NULL,
    valor_total numeric(10,2) NOT NULL
);


ALTER TABLE public.tb_produto_quantidade OWNER TO loja_adm;

--
-- Name: sq_produto_quantidade; Type: SEQUENCE; Schema: public; Owner: loja_adm
--

CREATE SEQUENCE public.sq_produto_quantidade
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sq_produto_quantidade OWNER TO loja_adm;

--
-- Name: sq_produto_quantidade; Type: SEQUENCE OWNED BY; Schema: public; Owner: loja_adm
--

ALTER SEQUENCE public.sq_produto_quantidade OWNED BY public.tb_produto_quantidade.id;


--
-- Name: tb_venda; Type: TABLE; Schema: public; Owner: loja_adm
--

CREATE TABLE public.tb_venda (
    id bigint NOT NULL,
    codigo character varying(10) NOT NULL,
    id_cliente_fk bigint NOT NULL,
    valor_total numeric(10,2) NOT NULL,
    data_venda timestamp with time zone NOT NULL,
    status_venda character varying(50) NOT NULL
);


ALTER TABLE public.tb_venda OWNER TO loja_adm;

--
-- Name: sq_venda; Type: SEQUENCE; Schema: public; Owner: loja_adm
--

CREATE SEQUENCE public.sq_venda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sq_venda OWNER TO loja_adm;

--
-- Name: sq_venda; Type: SEQUENCE OWNED BY; Schema: public; Owner: loja_adm
--

ALTER SEQUENCE public.sq_venda OWNED BY public.tb_venda.id;


--
-- Name: tb_estoque; Type: TABLE; Schema: public; Owner: loja_adm
--

CREATE TABLE public.tb_estoque (
    id bigint NOT NULL,
    codigo_nota_fiscal_fornecedor character varying(30) NOT NULL,
    id_produto_quantidade_fk bigint NOT NULL,
    quantidade integer NOT NULL
);


ALTER TABLE public.tb_estoque OWNER TO loja_adm;

--
-- Data for Name: tb_cliente; Type: TABLE DATA; Schema: public; Owner: loja_adm
--

COPY public.tb_cliente (id, nome, cpf, tel, endereco, numero, cidade, estado, email) FROM stdin;
\.


--
-- Data for Name: tb_estoque; Type: TABLE DATA; Schema: public; Owner: loja_adm
--

COPY public.tb_estoque (id, codigo_nota_fiscal_fornecedor, id_produto_quantidade_fk, quantidade) FROM stdin;
\.


--
-- Data for Name: tb_produto; Type: TABLE DATA; Schema: public; Owner: loja_adm
--

COPY public.tb_produto (id, codigo, nome, descricao, valor, unidade_de_mensuracao) FROM stdin;
\.


--
-- Data for Name: tb_produto_quantidade; Type: TABLE DATA; Schema: public; Owner: loja_adm
--

COPY public.tb_produto_quantidade (id, id_produto_fk, id_venda_fk, quantidade, valor_total) FROM stdin;
\.


--
-- Data for Name: tb_venda; Type: TABLE DATA; Schema: public; Owner: loja_adm
--

COPY public.tb_venda (id, codigo, id_cliente_fk, valor_total, data_venda, status_venda) FROM stdin;
\.


--
-- Name: sq_cliente; Type: SEQUENCE SET; Schema: public; Owner: loja_adm
--

SELECT pg_catalog.setval('public.sq_cliente', 3752, true);


--
-- Name: sq_estoque; Type: SEQUENCE SET; Schema: public; Owner: loja_adm
--

SELECT pg_catalog.setval('public.sq_estoque', 47, true);


--
-- Name: sq_produto; Type: SEQUENCE SET; Schema: public; Owner: loja_adm
--

SELECT pg_catalog.setval('public.sq_produto', 4201, true);


--
-- Name: sq_produto_quantidade; Type: SEQUENCE SET; Schema: public; Owner: loja_adm
--

SELECT pg_catalog.setval('public.sq_produto_quantidade', 1999, true);


--
-- Name: sq_venda; Type: SEQUENCE SET; Schema: public; Owner: loja_adm
--

SELECT pg_catalog.setval('public.sq_venda', 2178, true);


--
-- Name: tb_cliente pk_id_cliente; Type: CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_cliente
    ADD CONSTRAINT pk_id_cliente PRIMARY KEY (id);


--
-- Name: tb_produto_quantidade pk_id_prod_venda; Type: CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_produto_quantidade
    ADD CONSTRAINT pk_id_prod_venda PRIMARY KEY (id);


--
-- Name: tb_produto pk_id_produto; Type: CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_produto
    ADD CONSTRAINT pk_id_produto PRIMARY KEY (id);


--
-- Name: tb_venda pk_id_venda; Type: CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_venda
    ADD CONSTRAINT pk_id_venda PRIMARY KEY (id);


--
-- Name: tb_estoque tb_estoque_pkey; Type: CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_estoque
    ADD CONSTRAINT tb_estoque_pkey PRIMARY KEY (id);


--
-- Name: tb_produto uk_codigo_produto; Type: CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_produto
    ADD CONSTRAINT uk_codigo_produto UNIQUE (codigo);


--
-- Name: tb_venda uk_codigo_venda; Type: CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_venda
    ADD CONSTRAINT uk_codigo_venda UNIQUE (codigo);


--
-- Name: tb_cliente uk_cpf_cliente; Type: CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_cliente
    ADD CONSTRAINT uk_cpf_cliente UNIQUE (cpf);


--
-- Name: tb_venda fk_id_cliente_venda; Type: FK CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_venda
    ADD CONSTRAINT fk_id_cliente_venda FOREIGN KEY (id_cliente_fk) REFERENCES public.tb_cliente(id);


--
-- Name: tb_produto_quantidade fk_id_prod_venda; Type: FK CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_produto_quantidade
    ADD CONSTRAINT fk_id_prod_venda FOREIGN KEY (id_produto_fk) REFERENCES public.tb_produto(id);


--
-- Name: tb_produto_quantidade fk_id_prod_venda_venda; Type: FK CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_produto_quantidade
    ADD CONSTRAINT fk_id_prod_venda_venda FOREIGN KEY (id_venda_fk) REFERENCES public.tb_venda(id);


--
-- Name: tb_estoque fk_id_produto_quantidade; Type: FK CONSTRAINT; Schema: public; Owner: loja_adm
--

ALTER TABLE ONLY public.tb_estoque
    ADD CONSTRAINT fk_id_produto_quantidade FOREIGN KEY (id_produto_quantidade_fk) REFERENCES public.tb_produto_quantidade(id);


--
-- PostgreSQL database dump complete
--

