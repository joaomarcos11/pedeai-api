package org.jfm.domain.services;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.usecases.ClienteUseCase;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.domain.ports.PedidoPayment;
import org.jfm.domain.usecases.PedidoUseCase;
import org.jfm.domain.valueobjects.ItemPedido;

public class PedidoService implements PedidoUseCase {

    PedidoRepository pedidoRepository;

    ItemUseCase itemUseCase;

    ClienteUseCase clienteUseCase;

    PedidoPayment pedidoPayment;

    public PedidoService(PedidoRepository pedidoRepository, ClienteUseCase clienteUseCase, ItemUseCase itemUseCase,
            PedidoPayment pedidoPayment) {
        this.pedidoRepository = pedidoRepository;
        this.clienteUseCase = clienteUseCase;
        this.itemUseCase = itemUseCase;
        this.pedidoPayment = pedidoPayment;
    }

    @Override
    public Pedido criar(Pedido pedido) {
        pedido.validar();

        Pedido novoPedido = new Pedido();

        if (pedido.getIdCliente() != null) {
            clienteUseCase.buscarPorId(pedido.getIdCliente());
            novoPedido.setIdCliente(pedido.getIdCliente());
        }

        novoPedido.setId(UUID.randomUUID());
        novoPedido.setDataCriacao(Instant.now());
        novoPedido.setStatus(Status.AGUARDANDO_PAGAMENTO);

        for (ItemPedido ip : pedido.getItens()) {
            Item item = itemUseCase.buscarPorId(ip.getItem().getId());
            ItemPedido itemPedido = new ItemPedido(item, pedido, ip.getQuantidade());
            novoPedido.getItens().add(itemPedido);
        }

        pedidoRepository.criar(novoPedido);

        return novoPedido;
    };

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.listar();
    };

    @Override
    public Pedido buscarPorId(UUID id) {
        return pedidoRepository.buscarPorId(id);
    };

    @Override
    public List<Pedido> listarPorStatus(Status status) {
        return pedidoRepository.listarPorStatus(status);
    }

    @Override
    public void editar(Pedido pedido) {
        pedidoRepository.editar(pedido);
    };

    private void pagar(UUID idPedido, int valor) {
        byte[] info = pedidoPayment.criarPagamento(valor);
        pedidoPayment.pagar(info);
    }

}
