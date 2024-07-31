package org.jfm.controller.rest;

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
import org.jfm.controller.rest.dto.PagamentoGatewayWebhookDto;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.usecases.PedidoUseCase;

import io.vertx.core.cli.annotations.Hidden;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Hidden
@Path("/pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Pagamento", description = "Operações relacionadas ao pagamento")
public class PagamentoResource {

    @Inject
    PedidoUseCase pedidoUseCase;

    @Operation(summary = "Webhook de pagamento", description = "Recebe notificações de pagamento do gateway")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "OK")) }),
            @APIResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Bad Request"))
            }),
            @APIResponse(responseCode = "404", description = "Pagamento não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Pagamento não encontrado"))
            }),
            @APIResponse(responseCode = "409", description = "Conflito", content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.STRING, example = "Conflito"))
            })
    })
    @POST
    public Response pagamentoWebhook(
            @QueryParam("data_id") @Parameter(description = "ID dos dados de pagamento", example = "12345") String dataId,
            @QueryParam("type") @Parameter(description = "Tipo de notificação", example = "payment") String type,
            @RequestBody(description = "Dados do pagamento", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagamentoGatewayWebhookDto.class))) PagamentoGatewayWebhookDto pagamentoData) {
        UUID uuid = UUID.fromString(pagamentoData.data().id());
        this.pedidoUseCase.pagamentoPedido(uuid, Status.PAGO);
        return Response.status(Response.Status.OK).build();
    }

}
