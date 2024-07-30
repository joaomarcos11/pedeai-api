package org.jfm.domain.ports;

import org.jfm.domain.entities.Cliente;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.IdentificacaoPagamento;
import org.jfm.domain.valueobjects.Pagamento;

public interface PagamentoGateway {
    public Pagamento criarPagamento(int valor, String descricao, IdentificacaoPagamento tipoIdentificacao, String identificacao);

    public Pagamento criarPagamento2(Cliente cliente, Pedido pedido, int valor);
}
