package org.jfm.controller.rest;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
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
@Tag(name = "Item", description = "Operações relacionadas ao item")
public class ItemResource {

    @Inject
    ItemUseCase itemUseCase;

    @Inject
    ItemMapper itemMapper;

    @Operation(summary = "Criar item", description = "Cria um novo item")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UUID.class)) }),
            @APIResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Bad Request"))
            }),
            @APIResponse(responseCode = "404", description = "Item não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Item não encontrado"))
            }),
            @APIResponse(responseCode = "409", description = "Conflito", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Conflito"))
            })
    })
    @POST
    public Response criar(
            @RequestBody(description = "Dados do item para criação", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemCreateUpdateDto.class))) ItemCreateUpdateDto item) {
        Item itemEntity = itemMapper.toDomain(item);
        UUID idItem = itemUseCase.criar(itemEntity);
        return Response.status(Response.Status.CREATED).entity(idItem).build();
    }

    @Operation(summary = "Buscar itens", description = "Busca itens por categoria")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class)) }),
            @APIResponse(responseCode = "404", description = "Itens não encontrados", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Itens não encontrados"))
            })
    })
    @GET
    public Response buscar(
            @QueryParam("categoria") @Parameter(description = "Categoria do item") Categoria categoria) {
        if (categoria == null) {
            List<Item> itens = itemUseCase.listar();
            return Response.status(Response.Status.OK).entity(itens).build();
        }

        List<Item> itens = itemUseCase.listarCategoria(categoria);
        return Response.status(Response.Status.OK).entity(itens).build();
    }

    @Operation(summary = "Buscar item por ID", description = "Busca um item pelo ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class)) }),
            @APIResponse(responseCode = "404", description = "Item não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Item não encontrado"))
            })
    })
    @GET
    @Path("/{id}")
    public Response buscarPorId(
            @PathParam("id") @Parameter(description = "ID do item", example = "257ae14b-8bb7-4a80-9a68-22197f72ff47") UUID id) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Item item = itemUseCase.buscarPorId(id);
        return Response.status(Response.Status.OK).entity(item).build();
    }

    @Operation(summary = "Atualizar item", description = "Atualiza um item existente")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class)) }),
            @APIResponse(responseCode = "404", description = "Item não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Item não encontrado"))
            })
    })
    @PUT
    @Path("/{id}")
    public Response editar(
            @PathParam("id") @Parameter(description = "ID do item", example = "63a59178-39f8-4a28-a2c7-989a57ca7b54") UUID id,
            @RequestBody(description = "Dados do item para atualização", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemCreateUpdateDto.class))) ItemCreateUpdateDto item) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Item itemEntity = itemMapper.toDomain(item);
        itemEntity.setId(id);
        itemUseCase.editar(itemEntity);
        return Response.status(Response.Status.OK).build();
    }

    @Operation(summary = "Remover item", description = "Remove um item pelo ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Item removido com sucesso")) }),
            @APIResponse(responseCode = "404", description = "Item não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Item não encontrado"))
            })
    })
    @DELETE
    @Path("/{id}")
    public Response remover(
            @PathParam("id") @Parameter(description = "ID do item", example = "082db643-11a4-4bf8-8115-72148e24262d") UUID id) {
        if (id == null || id.toString().isEmpty()) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        itemUseCase.remover(id);
        return Response.status(Response.Status.OK).build();
    }

}
