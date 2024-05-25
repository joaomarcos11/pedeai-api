package org.jfm.domain.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.usecases.ClienteUseCase;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.domain.ports.PedidoPayment;
import org.jfm.domain.usecases.PedidoUseCase;

public class PedidoService implements PedidoUseCase {

    PedidoRepository pedidoRepository;

    ItemUseCase itemUseCase;

    ClienteUseCase clienteUseCase;

    PedidoPayment pedidoPayment;

    public PedidoService(PedidoRepository pedidoRepository, ClienteUseCase clienteUseCase, ItemUseCase itemUseCase, PedidoPayment pedidoPayment) {
        this.pedidoRepository = pedidoRepository;
        this.clienteUseCase = clienteUseCase;
        this.itemUseCase = itemUseCase;
        this.pedidoPayment = pedidoPayment;
    }

    @Override
    public UUID criar(Pedido pedido) {
        pedido.validar();

        if (pedido.getIdCliente() != null) {
            clienteUseCase.buscarPorId(pedido.getIdCliente());
        }

        List<Item> itens = itemUseCase.listar();

        int precoFinal = 0;

        for (Item item : pedido.getItens().keySet()) {
            if (!itens.contains(item)) {
                throw new EntityNotFoundException("item " + item + " não existe");
            }

            // gambiarra // TODO: remover isso aqui?
            item.setNome(itens.get(itens.indexOf(item)).getNome());
            item.setCategoria(itens.get(itens.indexOf(item)).getCategoria());
            item.setPreco(itens.get(itens.indexOf(item)).getPreco());

            precoFinal = precoFinal + item.getPreco();
        }

        pedido.setId(UUID.randomUUID());
        pedido.setDataCriacao(Instant.now());
        
        pagar(pedido.getId(), precoFinal);
        pedido.setStatus(Status.PAGO);

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

    private void pagar(UUID idPedido, int valor) {
        byte[] info = pedidoPayment.criarPagamento(valor);
        pedidoPayment.pagar(info);
    }

}
