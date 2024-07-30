package org.jfm.domain.ports;

import org.jfm.domain.entities.Cliente;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.valueobjects.Pagamento;

public interface PagamentoGateway {
    public Pagamento criarPagamento(Cliente cliente, Pedido pedido, int valor);
}
