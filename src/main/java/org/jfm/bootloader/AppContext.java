package org.jfm.bootloader;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jfm.domain.ports.ClienteRepository;
import org.jfm.domain.ports.ItemRepository;
import org.jfm.domain.ports.PedidoPagamentoRepository;
import org.jfm.domain.ports.Notificacao;
import org.jfm.domain.ports.PagamentoGateway;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.services.ClienteService;
import org.jfm.domain.services.ItemService;
import org.jfm.domain.services.PedidoService;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.domain.usecases.ClienteUseCase;

import jakarta.enterprise.inject.Produces;

public class AppContext {

    @ConfigProperty(name = "gateway.pagamento.mock") 
    String GATEWAY_PAGAMENTO_MOCK;

    @Produces
    public ClienteService clienteService(ClienteRepository clienteRepository) {
        return new ClienteService(clienteRepository);
    };
    
    @Produces
    public ItemService itemService(ItemRepository itemRepository) {
        return new ItemService(itemRepository);
    };

    @Produces
    public PedidoService pedidoService(PedidoRepository pedidoRepository, PedidoPagamentoRepository pedidoPagamentoRepository, ClienteUseCase clienteUseCase, ItemUseCase itemUseCase, Notificacao notificacao) {
        PagamentoGateway gatewayPagamento;
        
        if (!GATEWAY_PAGAMENTO_MOCK.equals("true")) {
            gatewayPagamento = new org.jfm.infra.payment.adaptermercadopago.PagamentoGatewayImpl();
        } else {
            gatewayPagamento = new org.jfm.infra.payment.adaptermock.PagamentoGatewayImpl();
        }

        return new PedidoService(pedidoRepository, pedidoPagamentoRepository, clienteUseCase, itemUseCase, gatewayPagamento, notificacao);
    };

}