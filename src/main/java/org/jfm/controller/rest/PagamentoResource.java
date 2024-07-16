package org.jfm.controller.rest;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jfm.domain.usecases.PedidoUseCase;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;

@Path("/pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Pagamento", description = "Operações relacionadas ao pagamento")
public class PagamentoResource {

    @Inject
    PedidoUseCase pedidoService;

    // TODO: ver qual verbo http é utilizado para notificar o pagamento e remover abaixo.
    
    @GET
    public Response get(Request request) {
        System.out.println("GET: " + request.toString());
        this.pedidoService.pagamentoPedido(request.toString(), request.toString());
        return Response.status(Response.Status.OK).build();
    }
    
    @POST
    public Response post(Request request) {
        System.out.println("POST: " + request.toString());
        this.pedidoService.pagamentoPedido(request.toString(), request.toString());
        return Response.status(Response.Status.OK).build();
    }

}