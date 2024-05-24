package org.jfm.controller.rest;

import java.util.List;
import java.util.UUID;

import org.jfm.controller.rest.dto.ItemCreateUpdateDto;
import org.jfm.controller.rest.mapper.ItemMapper;
import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.ParamException;
import org.jfm.domain.usecases.ItemUseCase;

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

@Path("/itens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {

    @Inject
    ItemUseCase itemUseCase;

    @Inject
    ItemMapper itemMapper;

    @POST
    public Response criar(ItemCreateUpdateDto item) {
        Item itemEntity = itemMapper.toDomain(item);
        UUID idItem = itemUseCase.criar(itemEntity);
        return Response.status(Response.Status.CREATED).entity(idItem).build();
    }

    @GET
    public Response buscar(@QueryParam("categoria") Categoria categoria) {
        if (categoria == null) {
            List<Item> itens = itemUseCase.listar();
            return Response.status(Response.Status.OK).entity(itens).build();
        }

        List<Item> itens = itemUseCase.listarCategoria(categoria);
        return Response.status(Response.Status.OK).entity(itens).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") UUID id) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Item item = itemUseCase.buscarPorId(id);
        return Response.status(Response.Status.OK).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    public Response editar(@PathParam("id") UUID id, ItemCreateUpdateDto item) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Item itemEntity = itemMapper.toDomain(item);
        itemEntity.setId(id);
        itemUseCase.editar(itemEntity);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") UUID id) {
        if (id == null || id.toString().isEmpty()) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        itemUseCase.remover(id);
        return Response.status(Response.Status.OK).build();
    }

}
