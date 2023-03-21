To make this project work, you need to create a Postgres database with the following setup:

```
Postgres <domain>:<port>: localhost:5432
Database name: db_loja
Username: ebac_user 
Password: secret
```

Then, you need to create some tables following the SQL sample. You can do it by executing one by one, but you can put these SQL commands in a `script.sql` and run the command below. 

```bash
    psql -U ebac_user postgres -aqf script.sql
```

```SQL
CREATE TABLE tb_cliente (
    id BIGINT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cpf BIGINT NOT NULL,
    tel BIGINT NOT NULL,
    endereco VARCHAR(50) NOT NULL,
    numero BIGINT NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    email VARCHAR(40) NOT NULL
);

CREATE TABLE tb_produto (
    id BIGINT NOT NULL,
    codigo VARCHAR(10) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    valor NUMERIC(10,2) NOT NULL,
    unidade_de_mensuracao VARCHAR(40) NOT NULL
);

CREATE TABLE tb_venda (
    id BIGINT NOT NULL,
    codigo VARCHAR(10) NOT NULL,
    id_cliente_fk BIGINT NOT NULL,
    valor_total NUMERIC(10,2) NOT NULL,
    data_venda TIMESTAMP NOT NULL,
    status_venda VARCHAR(50) NOT NULL
);

CREATE TABLE tb_estoque (
    id BIGINT NOT NULL,
    codigo_nota_fiscal_fornecedor VARCHAR(30) NOT NULL,
    id_produto_quantidade_fk BIGINT NOT NULL,
    quantidade INTEGER NOT NULL
);

CREATE TABLE tb_produto_quantidade (
    id BIGINT NOT NULL,
    id_produto_fk BIGINT NOT NULL,
    id_venda_fk BIGINT NOT NULL,
    quantidade INTEGER NOT NULL,
    valor_total NUMERIC(10,2) NOT NULL
);

CREATE SEQUENCE sq_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE sq_cliente
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE sq_estoque
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE sq_produto_quantidade
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE sq_venda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE tb_cliente ADD CONSTRAINT pk_id_cliente PRIMARY KEY (id);

ALTER TABLE ONLY tb_produto_quantidade ADD CONSTRAINT pk_id_prod_venda PRIMARY KEY (id);

ALTER TABLE ONLY tb_produto ADD CONSTRAINT pk_id_produto PRIMARY KEY (id);

ALTER TABLE ONLY tb_venda ADD CONSTRAINT pk_id_venda PRIMARY KEY (id);

ALTER TABLE ONLY tb_estoque ADD CONSTRAINT tb_estoque_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_produto ADD CONSTRAINT uk_codigo_produto UNIQUE (codigo);

ALTER TABLE ONLY tb_venda ADD CONSTRAINT uk_codigo_venda UNIQUE (codigo);

ALTER TABLE ONLY tb_cliente ADD CONSTRAINT uk_cpf_cliente UNIQUE (cpf);

ALTER TABLE ONLY tb_venda ADD CONSTRAINT fk_id_cliente_venda FOREIGN KEY (id_cliente_fk) REFERENCES tb_cliente(id);

ALTER TABLE ONLY tb_produto_quantidade ADD CONSTRAINT fk_id_prod_venda FOREIGN KEY (id_produto_fk) REFERENCES tb_produto(id);

ALTER TABLE ONLY tb_produto_quantidade ADD CONSTRAINT fk_id_prod_venda_venda FOREIGN KEY (id_venda_fk) REFERENCES tb_venda(id);

ALTER TABLE ONLY tb_estoque ADD CONSTRAINT fk_id_produto_quantidade FOREIGN KEY (id_produto_quantidade_fk) REFERENCES tb_produto_quantidade(id);
```
Now, you need to download a gradle jar by using the following command on the project root folder:

```bash
gradle wrapper 
```

I made some basic tests that can be run with the command below:

```bash
 ./gradlew clean test
```
