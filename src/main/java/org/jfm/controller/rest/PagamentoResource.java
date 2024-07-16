package org.jfm.controller.rest;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jfm.controller.rest.dto.PagamentoGatewayWebhookDto;
import org.jfm.domain.usecases.PedidoUseCase;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Pagamento", description = "Operações relacionadas ao pagamento")
public class PagamentoResource {

    @Inject
    PedidoUseCase pedidoUseCase;

    @POST
    public Response get(@QueryParam("data_id") String  dataId, @QueryParam("type") String type, PagamentoGatewayWebhookDto pagamentoData) {
        System.out.println(pagamentoData);
        this.pedidoUseCase.pagamentoPedido(pagamentoData.data().id(), type);
        return Response.status(Response.Status.OK).build();
    }

}
