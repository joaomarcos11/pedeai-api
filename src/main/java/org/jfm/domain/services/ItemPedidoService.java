package org.jfm.domain.services;

// import java.util.List;
// import java.util.UUID;

// import org.jfm.domain.entities.Item;
// import org.jfm.domain.entities.Pedido;
// import org.jfm.domain.ports.ItemPedidoRepository;
// import org.jfm.domain.usecases.ItemPedidoUseCase;
// import org.jfm.domain.valueobjects.ItemPedido;

// public class ItemPedidoService implements ItemPedidoUseCase {

//     ItemPedidoRepository itemPedidoRepository;
    
//     ItemService itemService;

//     PedidoService pedidoService;

    
//     public ItemPedidoService(ItemPedidoRepository itemPedidoRepository, ItemService itemService, PedidoService pedidoService) {
//         this.itemPedidoRepository = itemPedidoRepository;
//         this.itemService = itemService;
//         this.pedidoService = pedidoService;
//     }

//     @Override
//     public void adicionarItemAoPedido(ItemPedido itemPedido) {
//         Item item = this.itemService.buscarPorId(itemPedido.getIdItem());
//         Pedido pedido = this.pedidoService.buscarPorId(itemPedido.getIdPedido()     );
//         this.itemPedidoRepository.criar(item, pedido);
//     };

//     @Override
//     public List<Item> listarItensDoPedidoPeloId(UUID idPedido) {
//         Pedido pedido = this.pedidoService.buscarPorId(idPedido);
//         return this.itemPedidoRepository.listarPorPedido(pedido);
//     }

//     @Override
//     public void removerItemDoPedido(ItemPedido itemPedido) {
//         Item item = this.itemService.buscarPorId(itemPedido.getIdItem());
//         Pedido pedido = this.pedidoService.buscarPorId(itemPedido.getIdPedido()     );
//         this.itemPedidoRepository.remover(item, pedido);
//     }

// }
