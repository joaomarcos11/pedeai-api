package org.jfm.infra.payment.adaptermock;

import java.util.UUID;

import org.jfm.domain.ports.PedidoPayment;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoPaymentImpl implements PedidoPayment {

    @Override
    public byte[] criarPagamento(int valor) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().getBytes();        
    }

    @Override
    public boolean pagar(byte[] informacao) {
        return true;
    }
    
}
