package org.jfm.bootloader;

import org.jfm.domain.ports.ClienteRepository;
import org.jfm.domain.ports.ItemPedidoRepository;
import org.jfm.domain.ports.ItemRepository;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.services.ClienteService;
import org.jfm.domain.services.ItemPedidoService;
import org.jfm.domain.services.ItemService;
import org.jfm.domain.services.PedidoService;

import jakarta.enterprise.inject.Produces;

public class AppContext {

    @Produces
    public ClienteService clienteService(ClienteRepository clienteRepository) {
        return new ClienteService(clienteRepository);
    };

    @Produces
    public PedidoService pedidoService(PedidoRepository pedidoRepository) {
        return new PedidoService(pedidoRepository);
    }
    
    @Produces
    public ItemService itemService(ItemRepository itemRepository) {
        return new ItemService(itemRepository);
    };
    
    @Produces
    public ItemPedidoService itemPedidoService(ItemService itemService, PedidoService pedidoService) {
        return new ItemPedidoService(itemService, pedidoService);
    };

    // // TODO: implementar
    // // @Produces
    // // public PagamentoService pagamentoService(PagamentoRepository
    // // pagamentoRepository) {
    // // return new PagamentoService(pagamentoRepository);
    // // };

    // @Produces
    // public PedidoService pedidoService(PedidoRepository pedidoRepository, ItemPedidoService itemPedidoService,
    //         ItemService itemService) {
    //     return new PedidoService(pedidoRepository, itemPedidoService, itemService);
    // };

}