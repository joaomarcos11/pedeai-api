package org.jfm.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jfm.controller.rest.dto.PedidoCreateDto;
import org.jfm.controller.rest.dto.PedidoDto;
import org.jfm.controller.rest.dto.PedidoUpdateDto;
import org.jfm.controller.rest.mapper.PedidoMapper;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.ParamException;
import org.jfm.domain.usecases.PedidoUseCase;
import org.jfm.domain.valueobjects.Pagamento;

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
@Tag(name = "Pedido", description = "Operações relacionadas ao pedido")
public class PedidoResource {

    @Inject
    PedidoUseCase pedidoUseCase;

    @Inject
    PedidoMapper pedidoMapper;

    @Operation(summary = "Criar pedido", description = "Cria um novo pedido")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Pagamento.class)) }),
            @APIResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Bad Request"))
            }),
            @APIResponse(responseCode = "404", description = "Cliente não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Cliente não encontrado"))
            }),
            @APIResponse(responseCode = "409", description = "Conflito", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Conflito"))
            })
    })
    @POST
    public Response criar(
            @RequestBody(description = "Dados do pedido para criação", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoCreateDto.class))) PedidoCreateDto pedido) {
        Pedido pedidoEntity = pedidoMapper.toDomain(pedido);
        Pagamento pedidoPagamento = pedidoUseCase.criar(pedidoEntity);
        return Response.status(Response.Status.CREATED).entity(pedidoPagamento).build();
    }

    @Operation(summary = "Buscar pedidos", description = "Busca pedidos por status")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDto.class)) }),
            @APIResponse(responseCode = "404", description = "Pedidos não encontrados", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Pedidos não encontrados"))
            })
    })
    @GET
    public Response buscar(
            @QueryParam("status") @Parameter(description = "Status do pedido", example = "PENDENTE") Status status) {
        List<Pedido> pedidos = new ArrayList<>();

        if (status == null) {
            pedidos = pedidoUseCase.listar();
        } else {
            pedidos = pedidoUseCase.listarPorStatus(status);
        }

        List<PedidoDto> pedidosDto = pedidos.stream().map(p -> pedidoMapper.toDto(p)).collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(pedidosDto).build();
    }

    @Operation(summary = "Buscar pedidos em andamento", description = "Busca pedidos que estão em andamento")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDto.class)) }),
            @APIResponse(responseCode = "404", description = "Pedidos não encontrados", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Pedidos não encontrados"))
            })
    })
    @GET
    @Path("/em-andamento")
    public Response buscarEmAndamento() {
        List<Pedido> pedidos = pedidoUseCase.listarEmAndamento();

        List<PedidoDto> pedidosDto = pedidos.stream().map(p -> pedidoMapper.toDto(p)).collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(pedidosDto).build();
    }

    @Operation(summary = "Buscar pedido por ID", description = "Busca um pedido pelo ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDto.class)) }),
            @APIResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Pedido não encontrado"))
            })
    })
    @GET
    @Path("/{id}")
    public Response buscarPorId(
            @PathParam("id") @Parameter(description = "ID do pedido", example = "c215b5a1-9421-4cfd-982a-00f64f470252") UUID id) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Pedido pedido = pedidoUseCase.buscarPorId(id);
        PedidoDto pedidoDto = pedidoMapper.toDto(pedido);
        return Response.status(Response.Status.OK).entity(pedidoDto).build();
    }

    @Operation(summary = "Atualizar pedido", description = "Atualiza um pedido existente")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDto.class)) }),
            @APIResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Pedido não encontrado"))
            })
    })
    @PUT
    @Path("/{id}")
    public Response editar(
            @PathParam("id") @Parameter(description = "ID do pedido", example = "c215b5a1-9421-4cfd-982a-00f64f470252") UUID id,
            @RequestBody(description = "Dados do pedido para atualização", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoUpdateDto.class))) PedidoUpdateDto pedido) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Pedido pedidoEntity = pedidoMapper.toDomain(pedido);
        pedidoEntity.setId(id);
        pedidoEntity.setStatus(pedido.status());
        pedidoUseCase.editar(pedidoEntity);

        return Response.status(Response.Status.OK).build();
    }

    @Operation(summary = "Buscar histórico de status do pedido", description = "Busca o histórico de status de um pedido pelo ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoStatus.class)) }),
            @APIResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Pedido não encontrado"))
            })
    })
    @GET
    @Path("/{id}/status")
    public Response buscarHistoricoStatusPorId(
            @PathParam("id") @Parameter(description = "ID do pedido", example = "c215b5a1-9421-4cfd-982a-00f64f470252") UUID id) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        List<PedidoStatus> pedidosStatus = pedidoUseCase.buscarHistoricoStatus(id);

        return Response.status(Response.Status.OK).entity(pedidosStatus).build();
    }

    @Operation(summary = "Verificar se o pedido está pago", description = "Verifica se um pedido está pago pelo ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.BOOLEAN, example = "true")) }),
            @APIResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Pedido não encontrado"))
            })
    })
    @GET
    @Path("/{id}/esta-pago")
    public Response buscarEstaPago(
            @PathParam("id") @Parameter(description = "ID do pedido", example = "c215b5a1-9421-4cfd-982a-00f64f470252") UUID id) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        boolean pedidoEstaPago = pedidoUseCase.estaPago(id);

        return Response.status(Response.Status.OK).entity(pedidoEstaPago).build();
    }

}
