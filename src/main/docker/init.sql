\connect "pedeai"

CREATE TABLE "public"."clientes" (
    "ativo" boolean NOT NULL,
    "id" uuid NOT NULL,
    "cpf" character varying(255),
    "email" character varying(255),
    "nome" character varying(255),
    CONSTRAINT "clientes_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "clientes" ("ativo", "id", "cpf", "email", "nome") VALUES
('t',	'63a59178-39f8-4a28-a2c7-989a57ca7b54',	'123.123.123-12',	'filipe@email.com',	'FILIPE ANDRADE');

CREATE TABLE "public"."itens" (
    "categoria" smallint,
    "preco" integer NOT NULL,
    "id" uuid NOT NULL,
    "nome" character varying(255),
    CONSTRAINT "itens_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "itens" ("categoria", "preco", "id", "nome") VALUES
(0,	550,	'257ae14b-8bb7-4a80-9a68-22197f72ff47',	'SANDUICHE'),
(2,	350,	'23e52205-4d9d-41e6-a7f3-271af4f5316b',	'REFRIGERANTE'),
(1,	675,	'f66d8bf8-f350-46ba-8893-902bfd3e556e',	'BATATA FRITA'),
(3,	460,	'9b5b286e-e617-4f20-80ad-1824c97ec71b',	'SORVETE');

CREATE TABLE "public"."itens_pedidos" (
    "quantidade" integer NOT NULL,
    "item_id" uuid NOT NULL,
    "pedido_id" uuid NOT NULL,
    CONSTRAINT "itens_pedidos_pkey" PRIMARY KEY ("item_id", "pedido_id")
) WITH (oids = false);

INSERT INTO "itens_pedidos" ("quantidade", "item_id", "pedido_id") VALUES
(1,	'257ae14b-8bb7-4a80-9a68-22197f72ff47',	'c215b5a1-9421-4cfd-982a-00f64f470252'),
(2,	'23e52205-4d9d-41e6-a7f3-271af4f5316b',	'c215b5a1-9421-4cfd-982a-00f64f470252'),
(3,	'9b5b286e-e617-4f20-80ad-1824c97ec71b',	'c215b5a1-9421-4cfd-982a-00f64f470252');

CREATE TABLE "public"."pedidos" (
    "status" smallint,
    "data_criacao" timestamptz(6),
    "cliente_id" uuid,
    "id" uuid NOT NULL,
    CONSTRAINT "pedidos_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "pedidos" ("status", "data_criacao", "cliente_id", "id") VALUES
(1,	'2024-05-26 13:34:10.17325+00',	'63a59178-39f8-4a28-a2c7-989a57ca7b54',	'c215b5a1-9421-4cfd-982a-00f64f470252');

ALTER TABLE ONLY "public"."itens_pedidos" ADD CONSTRAINT "fkd2vejrqnlcjm5qys9nw3g3ol" FOREIGN KEY (item_id) REFERENCES itens(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."itens_pedidos" ADD CONSTRAINT "fkjg75o3y4r665tq9f72gyk64ut" FOREIGN KEY (pedido_id) REFERENCES pedidos(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."pedidos" ADD CONSTRAINT "fkg7202lk0hwxn04bmdl2thth5b" FOREIGN KEY (cliente_id) REFERENCES clientes(id) NOT DEFERRABLE;