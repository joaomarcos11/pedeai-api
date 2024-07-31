\connect "pedeai";

CREATE TABLE "public"."clientes" (
    "ativo" boolean NOT NULL,
    "id" uuid NOT NULL,
    "cpf" character varying(255),
    "email" character varying(255),
    "nome" character varying(255),
    CONSTRAINT "clientes_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "clientes" ("ativo", "id", "cpf", "email", "nome") VALUES
('t',	'63a59178-39f8-4a28-a2c7-989a57ca7b54',	'12312312312',	'filipe@email.com',	'FILIPE ANDRADE'),
('f',	'5793fc61-8d22-4183-9b20-079e624074a3',	'78978978978',	'murilo@email.com',	'MURILO MARTINS'),
('t',	'b57b4dcc-c47f-40f0-8331-6185bb9b3568',	'45645645645',	'joao@email.com',	'JOAO MARCOS'),
('t',	'b57b4dcc-c47f-40f0-8331-6185bb343443',	'35645645644',	'caio@email.com',	'CAIO MATOS');

CREATE TABLE "public"."itens" (
    "categoria" smallint,
    "preco" integer NOT NULL,
    "id" uuid NOT NULL,
    "nome" character varying(255),
    "descricao" character varying(255),
    "imagem" character varying(255),
    CONSTRAINT "itens_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "itens" ("categoria", "preco", "id", "nome", "descricao", "imagem") VALUES
(0,	550,	'257ae14b-8bb7-4a80-9a68-22197f72ff47',	'SANDUICHE', 'um delicioso sanduiche', '/var/app/imagens/sanduiche.jpg'),
(2,	350,	'23e52205-4d9d-41e6-a7f3-271af4f5316b',	'REFRIGERANTE', 'um delicioso refrigerante', '/var/app/imagens/refrigerante.jpg'),
(1,	675,	'f66d8bf8-f350-46ba-8893-902bfd3e556e',	'BATATA FRITA', 'batata-frita salgada', '/var/app/imagens/batata-frita.jpg'),
(3,	460,	'9b5b286e-e617-4f20-80ad-1824c97ec71b',	'SORVETE', 'uma deliciosa sobremesa', '/var/app/imagens/sorvete.jpg'),
(0,	450,	'6907dc62-e579-4178-ba30-3d7e4cea021d',	'X-VEGETARIANO', 'um delicioso lanche vegetariano', '/var/app/imagens/x-vegetariano.jpg'),
(2,	350,	'dd494312-7c6c-40c0-8449-0574c715325d',	'SUCO DE LARANJA', 'um suco feito da fruta fresca', '/var/app/imagens/suco-de-laranja.jpg'),
(1,	650,	'4e1bb65c-b3c0-4229-964b-c10241b7aca4',	'DADINHO DE TAPIOCA', 'um lanche saboroso', '/var/app/imagens/dadinho-de-tapioca.jpg'),
(3,	710,	'082db643-11a4-4bf8-8115-72148e24261d',	'PUDIM', 'uma sobremesa deliciosa', '/var/app/imagens/pudim.jpg'),
(3,	210,	'082db643-11a4-4bf8-8115-72148e24262d',	'PASTEL DE BELEM', 'uma sobremesa deliciosa', '/var/app/imagens/belem.jpg');

CREATE TABLE "public"."itens_pedidos" (
    "quantidade" integer NOT NULL,
    "item_id" uuid NOT NULL,
    "pedido_id" uuid NOT NULL,
    CONSTRAINT "itens_pedidos_pkey" PRIMARY KEY ("item_id", "pedido_id")
) WITH (oids = false);

INSERT INTO "itens_pedidos" ("quantidade", "item_id", "pedido_id") VALUES
(1,	'257ae14b-8bb7-4a80-9a68-22197f72ff47',	'c215b5a1-9421-4cfd-982a-00f64f470252'),
(2,	'23e52205-4d9d-41e6-a7f3-271af4f5316b',	'c215b5a1-9421-4cfd-982a-00f64f470252'),
(3,	'9b5b286e-e617-4f20-80ad-1824c97ec71b',	'c215b5a1-9421-4cfd-982a-00f64f470252'),
(4,	'23e52205-4d9d-41e6-a7f3-271af4f5316b',	'f529cc61-8dfb-4a1d-ab49-8eb846b55f27'),
(2,	'082db643-11a4-4bf8-8115-72148e24261d',	'5084a08e-a6d8-46fd-8150-71d49e964cbd'),
(1,	'dd494312-7c6c-40c0-8449-0574c715325d',	'5035ea42-8ec0-4d65-8887-4e8eab5b9391'),
(1,	'4e1bb65c-b3c0-4229-964b-c10241b7aca4',	'5035ea42-8ec0-4d65-8887-4e8eab5b9391'),
(1,	'6907dc62-e579-4178-ba30-3d7e4cea021d',	'5035ea42-8ec0-4d65-8887-4e8eab5b9391'),
(1,	'f66d8bf8-f350-46ba-8893-902bfd3e556e',	'ed20791f-416d-4037-a300-6130a9abfbbc');

CREATE TABLE "public"."pedidos" (
    "status" smallint,
    "data_criacao" timestamptz(6),
    "cliente_id" uuid,
    "id" uuid NOT NULL,
    CONSTRAINT "pedidos_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "pedidos" ("status", "data_criacao", "cliente_id", "id") VALUES
(1,	'2024-05-26 13:34:10.17325+00',	'63a59178-39f8-4a28-a2c7-989a57ca7b54',	'c215b5a1-9421-4cfd-982a-00f64f470252'),
(4,	'2024-05-27 20:48:15.064807+00',	'63a59178-39f8-4a28-a2c7-989a57ca7b54',	'f529cc61-8dfb-4a1d-ab49-8eb846b55f27'),
(0,	'2024-05-27 21:23:50.465097+00',	'5793fc61-8d22-4183-9b20-079e624074a3',	'5084a08e-a6d8-46fd-8150-71d49e964cbd'),
(2,	'2024-05-27 21:25:31.589084+00',	'b57b4dcc-c47f-40f0-8331-6185bb9b3568',	'5035ea42-8ec0-4d65-8887-4e8eab5b9391'),
(3,	'2024-05-27 21:26:24.211358+00',	NULL,	'ed20791f-416d-4037-a300-6130a9abfbbc');

ALTER TABLE ONLY "public"."itens_pedidos" ADD CONSTRAINT "fkd2vejrqnlcjm5qys9nw3g3ol" FOREIGN KEY (item_id) REFERENCES itens(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."itens_pedidos" ADD CONSTRAINT "fkjg75o3y4r665tq9f72gyk64ut" FOREIGN KEY (pedido_id) REFERENCES pedidos(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."pedidos" ADD CONSTRAINT "fkg7202lk0hwxn04bmdl2thth5b" FOREIGN KEY (cliente_id) REFERENCES clientes(id) NOT DEFERRABLE;

CREATE TABLE "public"."pedidos_status" (
    "id" uuid NOT NULL,
    "anterior" smallint,
    "atual" smallint NOT NULL,
    "data" timestamptz(6) NOT NULL,
    "pedido_id" uuid NOT NULL,
    CONSTRAINT "pedidos_status_pkey" PRIMARY KEY  ("id"),
    CONSTRAINT "pedidos_status_pedido_id_fkey" FOREIGN KEY ("pedido_id") REFERENCES "public"."pedidos" ("id")
) WITH (oids = false);
