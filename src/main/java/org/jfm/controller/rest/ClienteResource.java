package org.jfm.controller.rest;

import java.util.List;
import java.util.UUID;

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
public class ClienteResource {

    @Inject
    ClienteUseCase clienteUseCase;

    @Inject
    ClienteMapper clienteMapper;

    @POST
    public Response criar(ClienteDto cliente) {
        Cliente clienteEntity = clienteMapper.toDomain(cliente);
        UUID idCliente = clienteUseCase.criar(clienteEntity);
        return Response.status(Response.Status.CREATED).entity(idCliente).build();
    };

    @GET
    public Response buscar(@QueryParam("cpf") String cpf) {
        if (cpf == null) {
            List<Cliente> clientes = clienteUseCase.listar();
            return Response.status(Response.Status.OK).entity(clientes).build();
        }

        Cliente cliente = clienteUseCase.buscarPorCpf(cpf);
        return Response.status(Response.Status.OK).entity(cliente).build();
    };

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") UUID id) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Cliente cliente = clienteUseCase.buscarPorId(id);
        return Response.status(Response.Status.OK).entity(cliente).build();
    };

    @PUT
    @Path("/{id}")
    public Response editar(@PathParam("id") UUID id, ClienteDto clienteDto) {
        if (id == null || id.toString().isEmpty()) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        Cliente clienteEntity = clienteMapper.toDomain(clienteDto);
        clienteEntity.setId(id);
        clienteUseCase.editar(clienteEntity);
        return Response.status(Response.Status.OK).build();
    };

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") UUID id) {
        if (id == null) {
            throw new ParamException(ErrosSistemaEnum.PARAM_INVALID.getMessage());
        }

        clienteUseCase.remover(id);
        return Response.status(Response.Status.OK).build();
    };

}
