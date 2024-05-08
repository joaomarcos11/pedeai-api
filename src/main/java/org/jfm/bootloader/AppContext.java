package org.jfm.bootloader;

import org.jfm.domain.ports.ClienteRepository;
import org.jfm.domain.ports.IngredienteRepository;
import org.jfm.domain.ports.ItemIngredienteRepository;
import org.jfm.domain.ports.ItemPedidoRepository;
import org.jfm.domain.ports.ItemRepository;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.services.ClienteService;
import org.jfm.domain.services.IngredienteService;
import org.jfm.domain.services.ItemIngredienteService;
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
    public IngredienteService ingredienteService(IngredienteRepository ingredienteRepository) {
        return new IngredienteService(ingredienteRepository);
    };

    @Produces
    public ItemIngredienteService itemIngredienteService(ItemIngredienteRepository itemIngredienteRepository) {
        return new ItemIngredienteService(itemIngredienteRepository);
    };

    @Produces
    public ItemPedidoService itemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        return new ItemPedidoService(itemPedidoRepository);
    };

    @Produces
    public ItemService itemService(ItemRepository itemRepository, ItemIngredienteService itemIngredienteService,
            IngredienteService ingredienteService) {
        return new ItemService(itemRepository, itemIngredienteService, ingredienteService);
    };

    // TODO: implementar
    // @Produces
    // public PagamentoService pagamentoService(PagamentoRepository
    // pagamentoRepository) {
    // return new PagamentoService(pagamentoRepository);
    // };

    @Produces
    public PedidoService pedidoService(PedidoRepository pedidoRepository, ItemPedidoService itemPedidoService,
            ItemService itemService) {
        return new PedidoService(pedidoRepository, itemPedidoService, itemService);
    };

}