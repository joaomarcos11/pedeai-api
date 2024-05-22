package org.jfm.domain.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
        if (pedido.getIdCliente() == null) {
            throw new EntityNotFoundException("id do cliente não pode ser nulo"); // TODO: renomear
        }
        clienteUseCase.buscarPorId(pedido.getIdCliente());
        
        pedido.setId(UUID.randomUUID());
        pedido.setStatus(Status.INICIADO);
        pedidoRepository.criar(pedido);

        return pedido.getId();
    };

    @Override
    public List<Pedido> listar() {
        // return pedidoRepository.listar();
        List<Pedido> pedidos = pedidoRepository.listar();
        System.out.println("listar PedidoService: " + pedidos.get(0).getItens()); // TODO: remover
        return pedidos;
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
        // Pedido pedido = pedidoRepository.buscarPorId(idPedido);
        // int valorTotal = pedido.getItens().stream().map(i -> i.getPreco()).reduce(0,
        //         (subtotal, element) -> subtotal + element);

        int valorTotal = 10; // FIXME: arrumar aqui
        byte[] info = pedidoPayment.criarPagamento(valorTotal); // TODO: mock simples
        return pedidoPayment.pagar(info);
    }

    @Override
    public void editarItensDoPedido(UUID idPedido, Map<UUID, Integer> itemQuantidade) {
        Pedido pedido = pedidoRepository.buscarPorId(idPedido);
        // List<UUID> itens = itemUseCase.listar().stream().map(i -> i.getId()).collect(Collectors.toList());

        // System.out.println("PedidoService: " + pedido.getItens());

        pedido.setItens(itemQuantidade);

        // for (UUID id : itemQuantidade.keySet()) {
        //     Integer quantidade = itemQuantidade.get(id);
        //     if (quantidade < 0) {
        //         throw new EntityNotFoundException("quantidade não pode ser menor que zero."); // TODO: trocar exception
        //     } else if (quantidade > 0) {
        //         if (!itens.contains(id)) {
        //             throw new EntityNotFoundException("item " + id.toString() + " não encontrado.");
        //         }
        //         pedido.getItens().put(id, quantidade);
        //     } else {
        //         if (pedido.getItens().containsKey(id)) {
        //             pedido.getItens().remove(id);
        //         } else {
        //             throw new EntityNotFoundException("item não consta no pedido."); // TODO: trocar exception
        //         }
        //     }
        // }

        // System.out.println("PedidoService depois tratamento: " + pedido.getItens());

        pedidoRepository.editarItensDoPedido(pedido);

    }

    /*
    public void editarItensDoPedido(UUID idPedido, Map<UUID, Integer> itemQuantidade) {
        Pedido pedido = pedidoRepository.buscarPorId(idPedido);
        Map<Item, Integer> itensDoPedido = pedidoRepository.listarItensDoPedido(pedido);
        Map<UUID, Integer> itensDoPedidoUuid = itensDoPedido.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().getId(), e -> e.getValue()));
        
        for (UUID id : itemQuantidade.keySet()) {
            Integer quantidade = itemQuantidade.get(id);

            if (quantidade < 0) {
                throw new EntityNotFoundException("quantidade não pode ser negativa"); // TODO: trocar a exception
            } else if (quantidade > 0) {
                // TODO: verificar se é diferente o valor em quantidade para evitar várias queries no itemUseCase
                // TODO: melhor pegar todos items de antemão??
                Item item = itemUseCase.buscarPorId(id);
                pedido.getItens().put(item, quantidade);
            } else {
                if (itensDoPedidoUuid.containsKey(id)) {
                    Item item = itemUseCase.buscarPorId(id);
                    pedido.getItens().remove(item);
                } else {
                    throw new EntityNotFoundException("item não consta no pedido"); // TODO: trocar a exception
                }
            }
        }
        
        pedidoRepository.editarItensDoPedido(pedido);
    }
    */
    
    // @Override
    // public void adicionarItemAoPedido(UUID idItem, UUID idPedido, int quantidade) {
    //     Pedido pedido = pedidoRepository.buscarPorId(idPedido);
    //     Item item = itemUseCase.buscarPorId(idItem);
    //     pedidoRepository.adicionarItemAoPedido(item, pedido, quantidade);
    // };

    // @Override
    // public List<Item> listarItensDoPedidoPeloId(UUID idPedido) {
    //     Pedido pedido = pedidoRepository.buscarPorId(idPedido);
    //     return pedidoRepository.listarItensDoPedido(pedido);
    // }

    // @Override
    // public void removerItemDoPedido(UUID idItem, UUID idPedido, int quantidade) {
    //     Pedido pedido = pedidoRepository.buscarPorId(idPedido);
    //     Item item = itemUseCase.buscarPorId(idItem);
    //     pedidoRepository.removerItemDoPedido(item, pedido, quantidade);
    // }

}
