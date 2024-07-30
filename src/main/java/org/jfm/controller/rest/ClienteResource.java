package org.jfm.controller.rest;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jfm.controller.rest.dto.ClienteDto;
import org.jfm.controller.rest.mapper.ClienteMapper;
import org.jfm.domain.entities.Cliente;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.ParamException;
import org.jfm.domain.usecases.ClienteUseCase;

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

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Cliente", description = "Operações relacionadas ao cliente")
public class ClienteResource {

    @Inject
    ClienteUseCase clienteUseCase;

    @Inject
    ClienteMapper clienteMapper;

    @Operation(summary = "Criar cliente", description = "Cria um novo cliente")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UUID.class)) }),
            @APIResponse(responseCode = "400", description = "Bad Request"),
            @APIResponse(responseCode = "404", description = "Cliente não encontrado"),
            @APIResponse(responseCode = "409", description = "Conflito")
    })
    @POST
    public Response criar(ClienteDto cliente) {
        Cliente clienteEntity = clienteMapper.toDomain(cliente);
        System.out.println(clienteEntity.getNome());
        UUID idCliente = clienteUseCase.criar(clienteEntity);
        return Response.status(Response.Status.CREATED).entity(idCliente).build();
    }

    @Operation(summary = "Buscar cliente por CPF", description = "Busca um cliente por CPF")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDto.class)) }),
            @APIResponse(responseCode = "404", description = "Cliente não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Cliente não encontrado"))
            })
    })
    @GET
    public Response buscar(
            @QueryParam("cpf") @Parameter(description = "CPF do cliente", example = "78978978978") String cpf) {
        if (cpf == null) {
            List<Cliente> clientes = clienteUseCase.listar();
            return Response.status(Response.Status.OK).entity(clientes).build();
        }

        Cliente cliente = clienteUseCase.buscarPorCpf(cpf);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
        }
        return Response.status(Response.Status.OK).entity(cliente).build();
    }

    @Operation(summary = "Buscar cliente por Id", description = "Busca um cliente por Id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDto.class)) }),
            @APIResponse(responseCode = "404", description = "Cliente não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Cliente não encontrado"))
            })
    })
    @GET
    @Path("/{id}")
    public Response buscarPorId(
            @PathParam("id") @Parameter(description = "Id do cliente", example = "63a59178-39f8-4a28-a2c7-989a57ca7b54") UUID id) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Cliente cliente = clienteUseCase.buscarPorId(id);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
        }
        return Response.status(Response.Status.OK).entity(cliente).build();
    }

    @Operation(summary = "Atualizar cliente", description = "Atualiza um cliente")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDto.class)) }),
            @APIResponse(responseCode = "404", description = "Cliente não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Cliente não encontrado"))
            })
    })
    @PUT
    @Path("/{id}")
    public Response editar(
            @PathParam("id") @Parameter(description = "Id do cliente", example = "63a59178-39f8-4a28-a2c7-989a57ca7b54") UUID id,
            @RequestBody(description = "Dados do cliente para atualização", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDto.class), examples = {
                    @ExampleObject(name = "clienteAtualizacao", summary = "Exemplo de dados de atualização do cliente", value = "{\"nome\": \"Jorge\", \"cpf\": \"12345678900\", \"email\": \"jorge@example.com\", \"ativo\": true}")
            })) ClienteDto clienteDto) {
        if (id == null || id.toString().isEmpty()) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Cliente clienteEntity = clienteMapper.toDomain(clienteDto);
        clienteEntity.setId(id);
        clienteUseCase.editar(clienteEntity);
        return Response.status(Response.Status.OK).build();
    };

    @Operation(summary = "Deletar cliente", description = "Remove um cliente")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDto.class)) }),
            @APIResponse(responseCode = "404", description = "Cliente não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Cliente não encontrado"))
            })
    })
    @DELETE
    @Path("/{id}")
    public Response remover(
            @PathParam("id") @Parameter(description = "Id do cliente", example = "63a59178-39f8-4a28-a2c7-989a57ca7b54") UUID id) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        clienteUseCase.remover(id);
        return Response.status(Response.Status.OK).build();
    };

}