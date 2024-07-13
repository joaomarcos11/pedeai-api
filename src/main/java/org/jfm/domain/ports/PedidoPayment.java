package org.jfm.domain.ports;

import org.jfm.domain.entities.enums.IdentificacaoPagamento;
import org.jfm.domain.valueobjects.PagamentoPix;

public interface PedidoPayment {
    public PagamentoPix criarPagamento2(int valor, String descricao, IdentificacaoPagamento tipoIdentificacao, String identificacao);
    
    public byte[] criarPagamento(int valor);

    public boolean pagar(byte[] informacao);
}
