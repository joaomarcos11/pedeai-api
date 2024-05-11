package org.jfm.controller.rest;

import java.util.List;
import java.util.UUID;

import org.jfm.controller.rest.dto.ItemDTO;
import org.jfm.controller.rest.dto.PedidoDTO;
import org.jfm.controller.rest.mapper.ItemMapper;
import org.jfm.controller.rest.mapper.PedidoMapper;
import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.domain.usecases.PedidoUseCase;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
public class PedidoResource {

    @Inject
    PedidoUseCase pedidoUseCase;

    @Inject
    PedidoMapper pedidoMapper;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criar(PedidoDTO pedido) {
        Pedido pedidoEntity = pedidoMapper.toDomain(pedido);
        UUID idPedido = pedidoUseCase.criar(pedidoEntity);
        return Response.status(Response.Status.CREATED).entity(idPedido).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buscar() {
        List<Pedido> pedidos = pedidoUseCase.listar();
        return Response.status(Response.Status.OK).entity(pedidos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") UUID id) {
        Pedido pedido = pedidoUseCase.buscarPorId(id);
        return Response.status(Response.Status.OK).entity(pedido).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@PathParam("id") UUID id, PedidoDTO pedido) {
        Pedido pedidoEntity = pedidoMapper.toDomain(pedido);
        pedidoEntity.setId(id);
        pedidoUseCase.editar(pedidoEntity);
        return Response.status(Response.Status.OK).build();
    }

}
