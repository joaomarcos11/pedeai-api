package org.jfm.domain.ports;

public interface PedidoPayment {
    public byte[] criarPagamento(int valor);

    public boolean pagar(byte[] informacao);
}
