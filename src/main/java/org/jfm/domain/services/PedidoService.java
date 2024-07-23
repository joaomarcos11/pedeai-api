package org.jfm.domain.services;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Cliente;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.entities.enums.IdentificacaoPagamento;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.ports.PedidoStatusRepository;
import org.jfm.domain.usecases.ClienteUseCase;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.domain.ports.PedidoPagamentoRepository;
import org.jfm.domain.ports.Notificacao;
import org.jfm.domain.ports.PagamentoGateway;
import org.jfm.domain.usecases.PedidoUseCase;
import org.jfm.domain.valueobjects.PagamentoPix;

public class PedidoService implements PedidoUseCase {

    PedidoRepository pedidoRepository;
    
    PedidoPagamentoRepository pedidoPagamentoRepository;

    PedidoStatusRepository pedidoStatusRepository;

    ItemUseCase itemUseCase;

    ClienteUseCase clienteUseCase;

    PagamentoGateway pagamentoGateway;

    Notificacao notificacao;

    public PedidoService(PedidoRepository pedidoRepository, PedidoStatusRepository pedidoStatusRepository, PedidoPagamentoRepository pedidoPagamentoRepository, ClienteUseCase clienteUseCase, ItemUseCase itemUseCase, PagamentoGateway pagamentoGateway, Notificacao notificacao) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoStatusRepository = pedidoStatusRepository;
        this.pedidoPagamentoRepository = pedidoPagamentoRepository;
        this.clienteUseCase = clienteUseCase;
        this.itemUseCase = itemUseCase;
        this.pagamentoGateway = pagamentoGateway;
        this.notificacao = notificacao;
    }

    @Override
    public PagamentoPix criar(Pedido pedido) {
        pedido.validar();

        if (pedido.getIdCliente() != null) {
            clienteUseCase.buscarPorId(pedido.getIdCliente());
        }

        List<Item> itens = itemUseCase.listar();

        for (Item item : pedido.getItens().keySet()) {
            if (!itens.contains(item)) {
                throw new EntityNotFoundException("item " + item + " não existe");
            }

            // gambiarra // TODO: remover isso aqui?
            item.setNome(itens.get(itens.indexOf(item)).getNome());
            item.setCategoria(itens.get(itens.indexOf(item)).getCategoria());
            item.setPreco(itens.get(itens.indexOf(item)).getPreco());
        }

        UUID id = UUID.randomUUID();
        pedido.setId(id);
        pedido.setDataCriacao(Instant.now());
        pedido.setStatus(Status.AGUARDANDO_PAGAMENTO);
        pedidoRepository.criar(pedido);

        pedidoStatusRepository.criar(new PedidoStatus(UUID.randomUUID(), id, null, pedido.getStatus()));

        PagamentoPix pagamento = criarPagamento(pedido);
        // TODO: ver se pega a data de criação do pedido ou gera aqui agora (metodo para tentar pagar pedido já feito?)
        pedidoPagamentoRepository.criar(pedido, pagamento.id(), Instant.now());      

        return pagamento;
    };

    @Override
    public void pagamentoPedido(String id, String status) {
        // TODO: trocar abaixo
        if (status == "PAGO") {
            UUID pedidoId = UUID.fromString(id);
            Pedido pedido = this.pedidoRepository.buscarPorId(pedidoId);
            PedidoStatus pedidoStatus = new PedidoStatus(UUID.randomUUID(), pedido.getId(), pedido.getStatus(), Status.PAGO);
            
            pedido.setStatus(Status.PAGO);
            this.pedidoRepository.editar(pedido);
            this.pedidoStatusRepository.criar(pedidoStatus);

            this.notificacao.notificacaoPagamento(pedidoId, "pago");
            this.notificacao.notificarCozinha(pedidoId);
        }
        // TODO: outro tipo de tratamento
    }

    @Override
    public List<Pedido> listar() {
        List<Pedido> pedidos = pedidoRepository.listar();

        Collections.sort(pedidos, new Comparator<Pedido>() {
            public int compare(Pedido ped1, Pedido ped2) {
                return ped1.getDataCriacao().compareTo(ped2.getDataCriacao());
            }
        });

        return pedidos;
    };

    @Override
    public List<Pedido> listarEmAndamento() {
        List<Pedido> pedidos = pedidoRepository.listar();

        pedidos = pedidos.stream()
            .filter(pedido -> pedido.getStatus() != Status.CONCLUIDO && pedido.getStatus() != Status.CANCELADO)
            .sorted(Comparator.comparing(Pedido::getStatus, Comparator.comparingInt(this::getStatusPrioridade))
                        .thenComparing(Pedido::getDataCriacao))
            .collect(Collectors.toList());
        
        return pedidos;
    }

    @Override
    public Pedido buscarPorId(UUID id) {
        return pedidoRepository.buscarPorId(id);
    };

    @Override
    public List<Pedido> listarPorStatus(Status status) {
        List<Pedido> pedidos = pedidoRepository.listarPorStatus(status);

        Collections.sort(pedidos, new Comparator<Pedido>() {
            public int compare(Pedido ped1, Pedido ped2) {
                return ped1.getDataCriacao().compareTo(ped2.getDataCriacao());
            }
        });

        return pedidos;
    }

    @Override
    public void editar(Pedido pedido) {
        Pedido pedidoEditar = pedidoRepository.buscarPorId(pedido.getId());
        PedidoStatus pedidoStatus = new PedidoStatus(UUID.randomUUID(), pedidoEditar.getId(), pedidoEditar.getStatus(), pedido.getStatus());

        pedidoEditar.setStatus(pedido.getStatus());
        pedidoRepository.editar(pedidoEditar);
        pedidoStatusRepository.criar(pedidoStatus);
    };

    private PagamentoPix criarPagamento(Pedido pedido) {
        Cliente cliente = this.clienteUseCase.buscarPorId(pedido.getIdCliente());
        
        int valorFinal = 0;
        StringBuilder descricao = new StringBuilder();
        
        for (Item item : pedido.getItens().keySet()) {
            descricao.append(item.getId() + " // " + item.getNome() + " // " + item.getPreco());
            valorFinal += pedido.getItens().get(item);
        }

        // TODO: utilizar forma dinamica abaixo de configurar a identificacao de pagamento
        return pagamentoGateway.criarPagamento2(valorFinal, descricao.toString(), IdentificacaoPagamento.EMAIL, cliente.getEmail());
    }

    @Override
    public List<PedidoStatus> buscarHistoricoStatus(UUID id) {
        List<PedidoStatus> pedidosStatus = pedidoStatusRepository.listarPorPedidoId(id);

        Collections.sort(pedidosStatus, new Comparator<PedidoStatus> () {
            public int compare(PedidoStatus ped1, PedidoStatus ped2) {
                return ped2.getData().compareTo(ped1.getData());
            }
        });

        return pedidosStatus;
    }

    @Override
    public boolean estaPago(UUID id) {
        Pedido pedido = pedidoRepository.buscarPorId(id);

        return pedido.getStatus() == Status.PAGO;
    }

    private int getStatusPrioridade(Status status) {
        switch (status) {
            case DISPONIVEL:
                return 1;
            case PREPARANDO:
                return 2;
            case PAGO:
                return 3;
            default:
                return 4;
        }
    }
}
