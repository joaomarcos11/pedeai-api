package org.jfm.infra.payments;

import org.jfm.domain.ports.PedidoPayment;

public class PedidoPaymentImpl implements PedidoPayment {

    @Override
    public boolean pagar(int valor) {
        return true;
    }
    
}
