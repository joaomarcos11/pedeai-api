package org.jfm.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.controller.rest.dto.PedidoCreateDto;
import org.jfm.controller.rest.dto.PedidoDto;
import org.jfm.controller.rest.dto.PedidoUpdateDto;
import org.jfm.controller.rest.mapper.PedidoMapper;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.usecases.ItemPedidoUseCase;
import org.jfm.domain.usecases.PedidoUseCase;
import org.jfm.domain.valueObjects.ItemPedido;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoUseCase pedidoUseCase;

    @Inject
    ItemPedidoUseCase itemPedidoUseCase;

    @Inject
    PedidoMapper pedidoMapper;

    @POST
    public Response criar(PedidoCreateDto pedido) {
        Pedido pedidoEntity = pedidoMapper.toDomain(pedido);
        UUID idPedido = pedidoUseCase.criar(pedidoEntity);
        return Response.status(Response.Status.CREATED).entity(idPedido).build();
    }

    @GET
    public Response buscar(@QueryParam("status") Status status) {
        List<Pedido> pedidos = new ArrayList<>();

        if (status == null) {
            pedidos = pedidoUseCase.listar();
        } else {
            pedidos = pedidoUseCase.listarPorStatus(status);
        }

        List<PedidoDto> pedidosDto = pedidos.stream().map(p -> pedidoMapper.toDto(p)).collect(Collectors.toList());
        for (PedidoDto pedido : pedidosDto) { // TODO: forma mais elegante de forEach?
            pedido.setItens(itemPedidoUseCase.listarItensDoPedidoPeloId(pedido.getId()));
        }

        return Response.status(Response.Status.OK).entity(pedidosDto).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") UUID id) {
        Pedido pedido = pedidoUseCase.buscarPorId(id);
        PedidoDto pedidoDto = pedidoMapper.toDto(pedido);
        pedidoDto.setItens(itemPedidoUseCase.listarItensDoPedidoPeloId(id));
        return Response.status(Response.Status.OK).entity(pedido).build();
    }

    @PUT
    @Path("/{id}")
    public Response editar(@PathParam("id") UUID id, PedidoUpdateDto pedido) {
        Pedido pedidoEntity = pedidoMapper.toDomain(pedido);
        pedidoEntity.setId(id);
        pedidoUseCase.editar(pedidoEntity);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/{id}/adicionar-item")
    public Response adicionarItem(@PathParam("id") UUID id, UUID idItem) {
        ItemPedido itemPedido = new ItemPedido(idItem, id);
        itemPedidoUseCase.adicionarItemAoPedido(itemPedido);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/{id}/remover-item")
    public Response removerItem(@PathParam("id") UUID id, UUID idItem) {
        ItemPedido itemPedido = new ItemPedido(idItem, id);
        itemPedidoUseCase.removerItemDoPedido(itemPedido);
        return Response.status(Response.Status.OK).build();
    }

}
