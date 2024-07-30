package org.jfm.domain.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.Exceptions.InvalidEntityException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoStatus {
    private UUID id;
    private UUID idPedido;
    private Status anterior;
    private Status atual;
    private Instant data;

    private static final List<Status> LISTA_STATUS = new ArrayList<>(Arrays.asList(
        // Status.AGUARDANDO_PAGAMENTO,
        Status.CANCELADO,
        Status.CONCLUIDO,
        Status.DISPONIVEL,
        Status.PAGO,
        Status.PREPARANDO
    ));

    public PedidoStatus() {}

    public PedidoStatus(
        UUID id, 
        UUID idPedido,
        Status anterior,
        Status atual
     ) {
        this.id = id;
        this.idPedido = idPedido;
        this.anterior = anterior;
        this.atual = atual;
        this.data = Instant.now();

        validar(); //TODO: aqui mesmo?
    }

    public void validar() {

        if (this.idPedido == null) {
            throw new InvalidEntityException("Pedido inválido");
        }

        if (this.atual == null) {
            throw new InvalidEntityException("Status atual inválido");
        }
        
        if (LISTA_STATUS.contains(this.atual) && this.anterior == null) {
            throw new InvalidEntityException("Status anterior inválido");
        }
    }

}
