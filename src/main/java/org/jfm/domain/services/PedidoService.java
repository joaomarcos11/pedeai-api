package org.jfm.domain.services;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.ports.ClienteRepository;
import org.jfm.domain.ports.ItemRepository;
import org.jfm.domain.ports.PedidoPayment;
import org.jfm.domain.usecases.PedidoUseCase;
import org.jfm.domain.valueobjects.ItemPedido;

public class PedidoService implements PedidoUseCase {

    PedidoRepository pedidoRepository;

    ItemRepository itemRepository;

    ClienteRepository clienteRepository;

    PedidoPayment pedidoPayment;

    public PedidoService(PedidoRepository pedidoRepository, ItemRepository itemRepository, PedidoPayment pedidoPayment) {
        this.pedidoRepository = pedidoRepository;
        this.itemRepository = itemRepository;
        this.pedidoPayment = pedidoPayment;
    }

    @Override
    public UUID criar(Pedido pedido) {
        if (pedido.getIdCliente() != null) {
            clienteRepository.buscarPorId(pedido.getIdCliente());
        }

        pedido.setId(UUID.randomUUID());
        pedidoRepository.criar(pedido);

        return pedido.getId();
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

    @Override
    public boolean pagar(UUID idPedido) {
        Pedido pedido = pedidoRepository.buscarPorId(idPedido);
        int valorTotal = pedido.getItens().stream().map(i -> i.getPreco()).reduce(0,
                (subtotal, element) -> subtotal + element);

        byte[] info = pedidoPayment.criarPagamento(valorTotal); // TODO: mock simples
        return pedidoPayment.pagar(info);
    }

    @Override
    public void adicionarItemAoPedido(UUID idItem, UUID idPedido, int quantidade) {
        Pedido pedido = pedidoRepository.buscarPorId(idPedido);
        Item item = itemRepository.buscarPorId(idItem);
        pedidoRepository.adicionarItemAoPedido(item, pedido, quantidade);
    };

    @Override
    public List<Item> listarItensDoPedidoPeloId(UUID idPedido) {
        Pedido pedido = pedidoRepository.buscarPorId(idPedido);
        return pedidoRepository.listarItensDoPedido(pedido);
    }

    @Override
    public void removerItemDoPedido(UUID idItem, UUID idPedido, int quantidade) {
        Pedido pedido = pedidoRepository.buscarPorId(idPedido);
        Item item = itemRepository.buscarPorId(idItem);
        pedidoRepository.removerItemDoPedido(item, pedido, quantidade);
    }

}
