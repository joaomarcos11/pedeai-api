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
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.ParamException;
import org.jfm.domain.usecases.PedidoUseCase;
import org.jfm.infra.repository.adaptersql.entities.PedidoEntity;

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
    PedidoMapper pedidoMapper;

    @POST
    public Response criar(PedidoDto pedidoDto) {
        Pedido pedido = pedidoMapper.toDomain(pedidoDto);
        pedidoUseCase.criar(pedido);
        return Response.status(Response.Status.CREATED).entity(pedidoDto).build();
    }

    // TODO: quando lista tá dando ignore, criar um DTO que não mostre itens

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") UUID id) {
        if (id == null || id.toString().isEmpty()) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }
        Pedido pedido = pedidoUseCase.buscarPorId(id);
        PedidoDto pedidoDto = pedidoMapper.toDto(pedido);
        return Response.status(Response.Status.OK).entity(pedidoDto).build();
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
        return Response.status(Response.Status.OK).entity(pedidoEntity).build();
    }
}
