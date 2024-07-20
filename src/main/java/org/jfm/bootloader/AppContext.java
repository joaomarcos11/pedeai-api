package org.jfm.bootloader;

import org.jfm.domain.ports.ClienteRepository;
import org.jfm.domain.ports.ItemRepository;
import org.jfm.domain.ports.PedidoPagamentoRepository;
import org.jfm.domain.ports.Notificacao;
import org.jfm.domain.ports.PagamentoGateway;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.ports.PedidoStatusRepository;
import org.jfm.domain.services.ClienteService;
import org.jfm.domain.services.ItemService;
import org.jfm.domain.services.PedidoService;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.domain.usecases.ClienteUseCase;

import jakarta.enterprise.inject.Produces;

public class AppContext {

    @Produces
    public ClienteService clienteService(ClienteRepository clienteRepository) {
        return new ClienteService(clienteRepository);
    };
    
    @Produces
    public ItemService itemService(ItemRepository itemRepository) {
        return new ItemService(itemRepository);
    };

    @Produces
    public PedidoService pedidoService(PedidoRepository pedidoRepository, PedidoStatusRepository pedidoStatusRepository, PedidoPagamentoRepository pedidoPagamentoRepository, ClienteUseCase clienteUseCase, ItemUseCase itemUseCase, PagamentoGateway pagamentoGateway, Notificacao notificacao) {
        return new PedidoService(pedidoRepository, pedidoStatusRepository, pedidoPagamentoRepository, clienteUseCase, itemUseCase, pagamentoGateway, notificacao);
    };

}