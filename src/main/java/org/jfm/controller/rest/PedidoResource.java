package org.jfm.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.controller.rest.dto.PedidoCreateDto;
import org.jfm.controller.rest.dto.PedidoDto;
import org.jfm.controller.rest.dto.PedidoUpdateDto;
import org.jfm.controller.rest.mapper.PedidoMapper;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.ParamException;
// import org.jfm.domain.usecases.ItemPedidoUseCase;
import org.jfm.domain.usecases.PedidoUseCase;
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

    // @Inject
    // ItemPedidoUseCase itemPedidoUseCase;

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
        // if (status == null) {
        //     throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        // }
        
        List<Pedido> pedidos = new ArrayList<>();

        if (status == null) {
            pedidos = pedidoUseCase.listar();
        } else {
            pedidos = pedidoUseCase.listarPorStatus(status);
        }

        System.out.println("buscar PedidoResource: " + pedidos.get(0).getItens());

        List<PedidoDto> pedidosDto = pedidos.stream().map(p -> pedidoMapper.toDto(p)).collect(Collectors.toList());
        // for (PedidoDto pedido : pedidosDto) { // TODO: forma mais elegante de forEach?
        //     pedido.setItens(itemPedidoUseCase.listarItensDoPedidoPeloId(pedido.getId()));
        // }

        return Response.status(Response.Status.OK).entity(pedidosDto).build();
    }

    // TODO: quando lista tá dando ignore, criar um DTO que não mostre itens

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") UUID id) {
        if (id == null || id.toString().isEmpty()) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Pedido pedido = pedidoUseCase.buscarPorId(id);
        // PedidoDto pedidoDto = pedidoMapper.toDto(pedido); // TODO: melhor isso aqui
        // pedidoDto.setItens(itemPedidoUseCase.listarItensDoPedidoPeloId(id));
        return Response.status(Response.Status.OK).entity(pedido).build();
    }

    @PUT
    @Path("/{id}")
    public Response editar(@PathParam("id") UUID id, PedidoUpdateDto pedido) {
        if (id == null || id.toString().isEmpty()) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Pedido pedidoEntity = pedidoMapper.toDomain(pedido);
        pedidoEntity.setId(id);
        pedidoUseCase.editar(pedidoEntity);
        return Response.status(Response.Status.OK).build();
    }

    // @POST
    // @Path("/{id}/adicionar-item")
    // public Response adicionarItem(@PathParam("id") UUID id, UUID idItem) {
    //     if (id == null || id.toString().isEmpty()) {
    //         throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
    //     }

    //     ItemPedido itemPedido = new ItemPedido(idItem, id);
    //     itemPedidoUseCase.adicionarItemAoPedido(itemPedido);
    //     return Response.status(Response.Status.OK).build();
    // }

    // @POST
    // @Path("/{id}/remover-item")
    // public Response removerItem(@PathParam("id") UUID id, UUID idItem) {
    //     if (id == null || id.toString().isEmpty()) {
    //         throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
    //     }

    //     ItemPedido itemPedido = new ItemPedido(idItem, id);
    //     itemPedidoUseCase.removerItemDoPedido(itemPedido);
    //     return Response.status(Response.Status.OK).build();
    // }

    @POST
    @Path("/{id}/editar-itens")
    public Response editarItens(@PathParam("id") UUID id, Map<UUID, Integer> itemQuantidade) {
        if (id == null || id.toString().isEmpty()) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        pedidoUseCase.editarItensDoPedido(id, itemQuantidade);

        return Response.status(Response.Status.OK).build();
    }

    // @GET
    // @Path("/{id}/pagar")
    // public Response pagar(@PathParam("id") UUID id) {
    //     itemPedidoUseCase.pagar(id); // TODO: implementar
    //     return Response.status(Response.Status.OK).build();
    // }

}
