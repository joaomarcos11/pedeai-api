
# Vídeo de apresentação - Fase 3
[YouTube]()

# Modelo Entidades e Relacionamento
![Alt text](./MER.png)

# Arquitetura de Infraestrutura - Fase 3
![Alt text](./ARQUITETURA-INFRA-FASE-3.png)

# Vídeo de apresentação - Fase 2
[YouTube](https://www.youtube.com/watch?v=hrMI5-57YkM)

# Arquitetura de Infraestrutura - Fase 2
![Alt text](./k8s.png)

# Kubernetes Autoscale

guia de como instalar minikube, [clique aqui](https://gist.github.com/wholroyd/748e09ca0b78897750791172b2abb051)!

habilitar metrics-server para utilizar hpa (demora alguns minutos até iniciar o monitoramento):

`minikube addons enable metrics-server`

carregar imagens do ambiente docker local:

`minikube image rm pedeai-api-app:latest; minikube image load pedeai-api-app:latest`

criar infra:

`kubectl apply -f ./deployment`

habilitar túnel para acessar aplicações expostas dentro do cluster:

`minikube service app-svc --url`

testar hpa com carga:

`kubectl run -i --tty load-generator --rm --image=busybox:1.28 --restart=Never -- /bin/sh -c "while sleep 0.01; do wget -q -O- http://app-svc:8080/clientes; done"`

visualizar dashboard minikube:

`minikube dashboard`

# Projeto "pedeai-api"

O projeto “pedeai-api” foi desenvolvido como parte da Fase 1 do Tech Challenge da Pós-graduação em Software Architecture da FIAP.

O objetivo da demanda era o de criar um sistema de pedidos para uma lanchonete, otimizando o atendimento e a gestão interna. 

A ideia é oferecer uma solução eficiente para que os clientes possam fazer pedidos personalizados e acompanhar o processo de preparação e entrega.

## Documentação do Sistema (DDD) com Event Storming

A documentação do sistema no padrão Domain-Driven Design (DDD) pode ser encontrada em: 

https://miro.com/app/board/uXjVKStgq5k=/?share_link_id=621691325318

## Deploy

Para fazer o deploy desse projeto rode:

```bash

docker-compose up

```

Para acessar o banco de dados: http://localhost:8083/?pgsql=db&username=pedeai&db=pedeai

Senha: senha1

## Desafio

O projeto visa atender a necessdiade de uma lanchonete que está expandindo, mas enfrenta dificuldades devido à falta de um sistema de controle de pedidos. 

Sem uma estrutura adequada, os atendentes podem cometer erros, os pedidos podem ser perdidos e a satisfação dos clientes fica comprometida. 

Além disso, a ausência de um sistema de rastreamento dificulta a gestão interna.

## Entregáveis

1. **Documentação do Sistema (DDD) com Event Storming**:
   - Utilização da linguagem ubíqua (termos comuns a todos os envolvidos) para representar os fluxos de pedido e pagamento, preparação e entrega.
   - Diagramas UML seguindo os padrões apresentados nas aulas.
   - Desenhos que sigam as explicações e padrões adequados.

2. **Aplicação Backend (Monolito)**:
   - Desenvolvimento de uma aplicação backend completa.
   - APIs para:
     - Cadastro do cliente.
     - Identificação do cliente via CPF.
     - Gerenciamento de produtos (criação, edição e remoção).
     - Busca de produtos por categoria.
     - "Fake checkout" - criação de pedidos (sem finalização de checkout, apenas para fila).
     - Listagem de pedidos.
   - Utilização do Swagger para documentar as APIs.
   - Trabalho inicial com um único banco de dados.

3. **Dockerfile para subir o ambiente**:
   - 1 instância para banco de dados.
   - 1 instância para executar a aplicação.


## Estutura do projeto

Esta aplicação foi desenvolvida utilizando a linguagem Java com o framework Quarkus e o banco de dados PostgreSQL.

A estrutura da nossa aplicação segue a arquitetura hexagonal, na qual as preocupações do sistema são divididas em três camadas distintas: a camada de aplicação, a camada de domínio e a camada de infraestrutura. 

Cada uma dessas camadas possui responsabilidades específicas e se comunica com as outras camadas de forma bem definida.

**Camada de Aplicação:** Esta é a camada externa do sistema, responsável por receber as entradas do usuário, interpretá-las e acionar as operações adequadas na camada de domínio por meio dos Controladores REST.

**Camada de Domínio:** Esta camada contém as regras de negócio e os objetos de domínio do sistema. É aqui que as entidades e os serviços são definidos. Os serviços representam a lógica de negócio da aplicação, implementando as operações definidas nos casos de uso. As entidades representam os objetos principais do domínio.

**Camada de Infraestrutura:** Esta camada é responsável por fornecer implementações concretas para os detalhes técnicos, como o acesso ao banco de dados, chamadas externas de API, envio de e-mails, etc. Elas implementam as interfaces definidas na camada de domínio, fornecendo a lógica necessária para interagir com os recursos externos.

**Portas e Adaptadores:** Na arquitetura hexagonal, é comum usar interfaces para definir os pontos de interação entre as camadas, permitindo a substituição de implementações sem afetar o restante do sistema. Isso promove a modularidade e a testabilidade do código. No projeto, as portas (interfaces) da camada de domínio estão definidas em `src/main/java/org/jfm/domain/ports`, enquanto os Adaptadores (implementações) estão localizados em `src/main/java/org/jfm/infra/repository/adaptersql`.

Cada camada se comunica com as camadas adjacentes por meio de interfaces definidas, permitindo uma arquitetura flexível e desacoplada.

```plaintext
           +----------------------------------------+
           |             Controladores REST         |
           |                                        |
           |      ClienteResource                   |
           |      ItemResource                      |
           |      PedidoResource                    |
           +----------------------+-----------------+
                                  |
                                  |
                        +---------+----------+
                        |    Casos de Uso    |
                        |                    |
                        |    ClienteUseCase  |
                        |    ItemUseCase     |
                        |    PedidoUseCase   |
                        +---------+----------+
                                  |
                                  |
                        +---------+----------+
                        |   Serviços         |
                        |                    |
                        |   ClienteService   |
                        |   ItemService      |
                        |   PedidoService    |
                        +---------+----------+
                                  |
                                  |
                        +---------+----------+
                        |   Domínio          |
                        |                    |
                        |   Entidades        |
                        |   Exceções         |
                        +---------+----------+
                                  |
                                  |
                        +---------+----------+
                        |   Infraestrutura   |
                        |                    |
                        |   Implementações   |
                        |   Conexão com BD   |
                        |   Adaptadores      |
                        +---------+----------+
```

## Documentação da API

A documentação da API pode ser encontrada em: 
http://localhost:8080/openapi

Para ver o Swagger da aplicação, acesse: http://localhost:8080/swagger-ui

## Endpoints

#### Retorna um cliente específico
```
  GET /clientes/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do cliente que você quer |

#### Retorna todos os clientes
```
  GET /clientes
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `cpf`      | `string` | O CPF do cliente que você quer filtrar (opcional) |

#### Cria um novo cliente
```
  POST /clientes
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `body`      | `ClienteDto` | **Obrigatório**. Os detalhes do cliente que você quer criar |

#### Atualiza um cliente existente
```
  PUT /clientes/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do cliente que você quer atualizar |
| `body`      | `ClienteDto` | **Obrigatório**. Os detalhes atualizados do cliente que você quer atualizar |

#### Remove um cliente específico
```
  DELETE /clientes/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do cliente que você quer remover |

#### Retorna um item específico
```
  GET /itens/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer |

#### Retorna todos os itens
```
  GET /itens
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `categoria`      | `string` | A categoria do item que você quer filtrar (opcional) |

#### Cria um novo item
```
  POST /itens
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `body`      | `ItemCreateUpdateDto` | **Obrigatório**. Os detalhes do item que você quer criar |

#### Atualiza um item existente
```
  PUT /itens/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer atualizar |
| `body`      | `ItemCreateUpdateDto` | **Obrigatório**. Os detalhes atualizados do item que você quer atualizar |

#### Remove um item específico
```
  DELETE /itens/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer remover |

#### Retorna um pedido específico
```
  GET /pedidos/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do pedido que você quer |

#### Retorna todos os pedidos
```
  GET /pedidos
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `status`      | `string` | O status do pedido que você quer filtrar (opcional) |

#### Cria um novo pedido
```
  POST /pedidos
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `body`      | `PedidoCreateDto` | **Obrigatório**. Os detalhes do pedido que você quer criar |

#### Atualiza/Editar um pedido existente
```
  PUT /pedidos/${id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do pedido que você quer atualizar |
| `body`      | `PedidoUpdateDto` | **Obrigatório**. Os detalhes atualizados do pedido que você quer atualizar |

## Autores

- Filipe Andrade - RM356270 | Github: [@filipeandrade6](https://github.com/filipeandrade6) | Discord: @filipeandrade6
- João Marcos - RM356227 | Github: [@joaomarcos11](https://github.com/joaomarcos11) | Discord: @joaomarcos113473
- Murilo Martins - RM356159 | Github: [@murilomartins93](https://github.com/murilomartins93) | Discord: @giarenzzo
