package org.jfm.usecase.pedido;

import org.jfm.domain.entities.Pedido;
import java.util.List;

public interface PedidoRepository {
    public Pedido Criar(Pedido pedido);
    public Pedido BuscarPorId(int id);
    public List<Pedido> Listar();
    public List<Pedido> ListarPorStatus(int status);
    public Pedido Editar(Pedido pedido);
    public void Remover(int id);
};


github.com/programadriano/ts-hexagon-architecture/tree/main/src