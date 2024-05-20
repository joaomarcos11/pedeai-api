package org.jfm.infra.repository.adaptersql.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itens_pedidos")
@NamedQueries({
    @NamedQuery(name = "ItemPedido.findByPedidoId", query = "SELECT i FROM ItemPedidoEntity i WHERE i.pedido = :pedido_id"),
})
@Getter
@Setter
public class ItemPedidoEntity {
    
    // @Id
    // UUID id;

    @EmbeddedId
    ItemPedidoKey id;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    ItemEntity item;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    PedidoEntity pedido;

    int quantidade;

}

// import jakarta.persistence.Entity;
// import jakarta.persistence.Table;
// import lombok.Getter;
// import lombok.Setter;
// import jakarta.persistence.EmbeddedId;

// @Entity
// @Table(name = "itens_pedidos")
// @Getter
// @Setter
// public class ItemPedidoEntity {

//     @EmbeddedId
//     private ItemPedidoKey id = new ItemPedidoKey();

//     private Integer quantity;

//     public ItemPedidoEntity () {
//     }

//     public ItemPedidoEntity(ItemEntity item, PedidoEntity pedido, Integer quantity) {
//         id.setItem(item);
//         id.setPedido(pedido);
//         this.quantity = quantity;
//     }

// }
