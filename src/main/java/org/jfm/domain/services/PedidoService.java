package org.jfm.domain.services;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.jfm.domain.entities.Cliente;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.ports.PedidoStatusRepository;
import org.jfm.domain.usecases.ClienteUseCase;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.domain.ports.PedidoPagamentoRepository;
import org.jfm.domain.ports.Notificacao;
import org.jfm.domain.ports.PagamentoGateway;
import org.jfm.domain.usecases.PedidoUseCase;
import org.jfm.domain.valueobjects.Pagamento;

public class PedidoService implements PedidoUseCase {

    PedidoRepository pedidoRepository;

    PedidoPagamentoRepository pedidoPagamentoRepository;

    PedidoStatusRepository pedidoStatusRepository;

    ItemUseCase itemUseCase;

    ClienteUseCase clienteUseCase;

    PagamentoGateway pagamentoGateway;

    Notificacao notificacao;

    private static final Set<Status> STATUS_EM_ANDAMENTO = EnumSet.of(Status.PAGO, Status.PREPARANDO,
            Status.DISPONIVEL);

    public PedidoService(PedidoRepository pedidoRepository, PedidoStatusRepository pedidoStatusRepository,
            PedidoPagamentoRepository pedidoPagamentoRepository, ClienteUseCase clienteUseCase, ItemUseCase itemUseCase,
            PagamentoGateway pagamentoGateway, Notificacao notificacao) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoStatusRepository = pedidoStatusRepository;
        this.pedidoPagamentoRepository = pedidoPagamentoRepository;
        this.clienteUseCase = clienteUseCase;
        this.itemUseCase = itemUseCase;
        this.pagamentoGateway = pagamentoGateway;
        this.notificacao = notificacao;
    }

    @Override
    public Pagamento criar(Pedido pedido) {
        pedido.validar();

        if (pedido.getIdCliente() != null) {
            clienteUseCase.buscarPorId(pedido.getIdCliente());
        }

        List<Item> itens = itemUseCase.listar();
        
        UUID itemUuid = UUID.fromString("6907dc62-e579-4178-ba30-3d7e4cea021d");
        Item itemEscolhido = new Item();

        for (Item item : itens) {
            if (item.getId().equals(itemUuid)) {
                itemEscolhido = item;
            }
        }

        Map<Item,Integer> mapItens = new HashMap<>();
        mapItens.put(itemEscolhido, 2);

        pedido.setItens(mapItens);

        // for (Item item : pedido.getItens().keySet()) {
        //     if (!itens.contains(item)) {
        //         throw new EntityNotFoundException("item " + item + " não existe");
        //     }

        //     item.setNome(itens.get(itens.indexOf(item)).getNome());
        //     item.setCategoria(itens.get(itens.indexOf(item)).getCategoria());
        //     item.setPreco(itens.get(itens.indexOf(item)).getPreco());
        // }

        UUID id = UUID.randomUUID();
        pedido.setId(id);
        pedido.setDataCriacao(Instant.now());
        pedido.setStatus(Status.AGUARDANDO_PAGAMENTO);

        System.out.println(pedido.getItens().size());
        System.out.println(pedido.getItens().toString());

        pedidoRepository.criar(pedido);

        pedidoStatusRepository.criar(new PedidoStatus(UUID.randomUUID(), id, null, pedido.getStatus()));

        Pagamento pagamento = criarPagamento(pedido);

        System.out.println(pagamento.toString());

        // pedidoPagamentoRepository.criar(pedido, pagamento.id(), Instant.now());

        // return pagamento;
        return new Pagamento(UUID.randomUUID(), 123L, "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");
    };

    @Override
    public void pagamentoPedido(UUID id, Status status) {
        if (status != Status.PAGO) {
            this.notificacao.notificacaoPagamento(id, status);

            // TODO: throw exception?

            return;
        }

        Pedido pedido = this.pedidoRepository.buscarPorId(id);
        PedidoStatus pedidoStatus = new PedidoStatus(UUID.randomUUID(), pedido.getId(), pedido.getStatus(),
                Status.PAGO);
        pedido.setStatus(Status.PAGO);
        this.pedidoRepository.editar(pedido);
        this.pedidoStatusRepository.criar(pedidoStatus);
        this.notificacao.notificacaoPagamento(id, status);
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
                .filter(pedido -> STATUS_EM_ANDAMENTO.contains(pedido.getStatus()))
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
        PedidoStatus pedidoStatus = new PedidoStatus(UUID.randomUUID(), pedidoEditar.getId(), pedidoEditar.getStatus(),
                pedido.getStatus());

        pedidoEditar.setStatus(pedido.getStatus());

        pedidoRepository.editar(pedidoEditar);
        pedidoStatusRepository.criar(pedidoStatus);
    };

    private Pagamento criarPagamento(Pedido pedido) {
        Cliente cliente = this.clienteUseCase.buscarPorId(pedido.getIdCliente());

        int valorFinal = 0;
        StringBuilder descricao = new StringBuilder();

        for (Item item : pedido.getItens().keySet()) {
            descricao.append(item.getId() + " // " + item.getNome() + " // " + item.getPreco());
            valorFinal += pedido.getItens().get(item);
        }

        return pagamentoGateway.criarPagamento(cliente, pedido, valorFinal);
    }

    @Override
    public List<PedidoStatus> buscarHistoricoStatus(UUID id) {
        List<PedidoStatus> pedidosStatus = pedidoStatusRepository.listarPorPedidoId(id);

        Collections.sort(pedidosStatus, new Comparator<PedidoStatus>() {
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
