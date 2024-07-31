INSERT INTO clientes (id, nome, cpf, email, ativo) VALUES ('63a59178-39f8-4a28-a2c7-989a57ca7b54', 'FILIPE ANDRADE', '123.123.123-12', 'filipe@email.com', TRUE);
INSERT INTO itens (categoria,preco, id, nome, descricao, imagem) VALUES
(0,	550,	'257ae14b-8bb7-4a80-9a68-22197f72ff47',	'SANDUICHE', 'um delicioso sanduiche', '/var/app/imagens/sanduiche.jpg'),
(2,	350,	'23e52205-4d9d-41e6-a7f3-271af4f5316b',	'REFRIGERANTE', 'um delicioso refrigerante', '/var/app/imagens/refrigerante.jpg'),
(1,	675,	'f66d8bf8-f350-46ba-8893-902bfd3e556e',	'BATATA FRITA', 'batata-frita salgada', '/var/app/imagens/batata-frita.jpg'),
(3,	460,	'9b5b286e-e617-4f20-80ad-1824c97ec71b',	'SORVETE', 'uma deliciosa sobremesa', '/var/app/imagens/sorvete.jpg'),
(0,	450,	'6907dc62-e579-4178-ba30-3d7e4cea021d',	'X-VEGETARIANO', 'um delicioso lanche vegetariano', '/var/app/imagens/x-vegetariano.jpg'),
(2,	350,	'dd494312-7c6c-40c0-8449-0574c715325d',	'SUCO DE LARANJA', 'um suco feito da fruta fresca', '/var/app/imagens/suco-de-laranja.jpg'),
(1,	650,	'4e1bb65c-b3c0-4229-964b-c10241b7aca4',	'DADINHO DE TAPIOCA', 'um lanche saboroso', '/var/app/imagens/dadinho-de-tapioca.jpg'),
(3,	710,	'082db643-11a4-4bf8-8115-72148e24261d',	'PUDIM', 'uma sobremesa deliciosa', '/var/app/imagens/pudim.jpg');
INSERT INTO pedidos (id, cliente_id, status, data_criacao) VALUES ('c215b5a1-9421-4cfd-982a-00f64f470252', '63a59178-39f8-4a28-a2c7-989a57ca7b54', 1, NOW());
INSERT INTO itens_pedidos (item_id, pedido_id, quantidade) VALUES ('257ae14b-8bb7-4a80-9a68-22197f72ff47', 'c215b5a1-9421-4cfd-982a-00f64f470252', 1);
INSERT INTO itens_pedidos (item_id, pedido_id, quantidade) VALUES ('23e52205-4d9d-41e6-a7f3-271af4f5316b', 'c215b5a1-9421-4cfd-982a-00f64f470252', 2);
INSERT INTO itens_pedidos (item_id, pedido_id, quantidade) VALUES ('9b5b286e-e617-4f20-80ad-1824c97ec71b', 'c215b5a1-9421-4cfd-982a-00f64f470252', 3);
INSERT INTO pedidos_pagamentos (pedido_id, pagamento_id, data_criacao) VALUES ('c215b5a1-9421-4cfd-982a-00f64f470252', 'c215b5a1-9421-4cfd-982a-00f64f470253', NOW());