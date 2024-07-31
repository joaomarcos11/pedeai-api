package org.jfm.infra.repository.adaptersql.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedidos_pagamentos")
@NamedQueries({
    @NamedQuery(name = "PedidoPagamento.findByPedidoId", query = "SELECT p FROM PedidoPagamentoEntity p WHERE p.id.pedidoId = :pedido_id"),
})
@Getter
@Setter
public class PedidoPagamentoEntity {
    
    @EmbeddedId
    PedidoPagamentoKey id;

    @Column(name = "data_criacao")
    private Instant dataCriacao;

}
