-- TODO: incluir o dump aqui e remover o dro-create no application.properties

INSERT INTO clientes (id, nome, cpf, email, ativo) VALUES ('63a59178-39f8-4a28-a2c7-989a57ca7b54', 'FILIPE ANDRADE', '123.123.123-12', 'filipe@email.com', TRUE);
INSERT INTO itens (id, nome, preco, categoria) VALUES ('257ae14b-8bb7-4a80-9a68-22197f72ff47', 'SANDUICHE', 550, 0);
INSERT INTO itens (id, nome, preco, categoria) VALUES ('23e52205-4d9d-41e6-a7f3-271af4f5316b', 'REFRIGERANTE', 350, 2);
INSERT INTO itens (id, nome, preco, categoria) VALUES ('f66d8bf8-f350-46ba-8893-902bfd3e556e', 'BATATA FRITA', 675, 1);
INSERT INTO itens (id, nome, preco, categoria) VALUES ('9b5b286e-e617-4f20-80ad-1824c97ec71b', 'SORVETE', 460, 3);
INSERT INTO pedidos (id, cliente_id, status, data_criacao) VALUES ('c215b5a1-9421-4cfd-982a-00f64f470252', '63a59178-39f8-4a28-a2c7-989a57ca7b54', 1, NOW());
INSERT INTO itens_pedidos (item_id, pedido_id, quantidade) VALUES ('257ae14b-8bb7-4a80-9a68-22197f72ff47', 'c215b5a1-9421-4cfd-982a-00f64f470252', 1);
INSERT INTO itens_pedidos (item_id, pedido_id, quantidade) VALUES ('23e52205-4d9d-41e6-a7f3-271af4f5316b', 'c215b5a1-9421-4cfd-982a-00f64f470252', 2);
INSERT INTO itens_pedidos (item_id, pedido_id, quantidade) VALUES ('9b5b286e-e617-4f20-80ad-1824c97ec71b', 'c215b5a1-9421-4cfd-982a-00f64f470252', 3);