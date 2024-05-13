package org.jfm.controller.rest;

import java.util.List;
import java.util.UUID;

import org.jfm.controller.rest.dto.ClienteDto;
import org.jfm.controller.rest.mapper.ClienteMapper;
import org.jfm.domain.entities.Cliente;
import org.jfm.domain.usecases.ClienteUseCase;
import org.jfm.exception.ApiException;
import org.jfm.util.ValidatorUtil;

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

        ValidatorUtil.validateClienteDto(cliente);
        validateClienteExists(cliente);

        try {
            Cliente clienteEntity = clienteMapper.toDomain(cliente);
            UUID idCliente = clienteUseCase.criar(clienteEntity);
            return Response.status(Response.Status.CREATED).entity(idCliente).build();
        } catch (Exception e) {
            throw new ApiException("Aconteceu um erro, tente novamente", Response.Status.INTERNAL_SERVER_ERROR, e);
        }
    };

    @GET
    public Response buscar(@QueryParam("cpf") String cpf) {
        // if (cpf == null) {
        //     List<Cliente> clientes = clienteUseCase.listar();
        //     return Response.status(Response.Status.OK).entity(clientes).build();
        // }

        Cliente cliente = clienteUseCase.buscarPorCpf(cpf);
        if (cliente == null) {
            throw new ApiException("Dados inválidos (COD1014 - cpf nao encontrado)", Response.Status.NOT_FOUND);
        }

        return Response.status(Response.Status.OK).entity(cliente).build();
    };

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") UUID id) {
        Cliente cliente = clienteUseCase.buscarPorId(id);

        if (cliente == null) {
            throw new ApiException("Dados inválidos (COD1010 - id nao encontrado)", Response.Status.NOT_FOUND);
        }

        return Response.status(Response.Status.OK).entity(cliente).build();
    };

    @PUT
    @Path("/{id}")
    public Response editar(@PathParam("id") UUID id, ClienteDto clienteDto) {
        Cliente cliente = clienteUseCase.buscarPorId(id);

        if (cliente == null) {
            throw new ApiException("Dados inválidos (COD1010 - id nao encontrado)", Response.Status.NOT_FOUND);
        }
        
        if (!cliente.getAtivo()) {
            throw new ApiException("Operação inválida (COD1013 - cliente inativo)", Response.Status.METHOD_NOT_ALLOWED);
        }

        Cliente clienteEntity = clienteMapper.toDomain(clienteDto);
        clienteEntity.setId(id);
        clienteUseCase.editar(clienteEntity);
        return Response.status(Response.Status.OK).build();
    };

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") UUID id) {
        Cliente cliente = clienteUseCase.buscarPorId(id);
        if (cliente == null) {
            throw new ApiException("Dados inválidos (COD1010 - id nao encontrado)", Response.Status.NOT_FOUND);
        }

        clienteUseCase.remover(id);
        return Response.status(Response.Status.OK).build();
    };

    private void validateClienteExists(ClienteDto clienteDto) {
        validateCpfExists(clienteDto.cpf());
        validateEmailExists(clienteDto.email());
    }

    private void validateCpfExists(String cpf) {
        Cliente cliente = clienteUseCase.buscarPorCpf(cpf);
        if (cliente == null) {
            return;
        }
        
        throw new ApiException("Dados inválidos (COD1011 - cpf ja existe)", Response.Status.NOT_FOUND);
    }
    
    private void validateEmailExists(String email) {
        Cliente cliente = clienteUseCase.buscarPorEmail(email);
        if (cliente == null) {
            return;
        }

        throw new ApiException("Dados inválidos (COD1012 - email ja existe)", Response.Status.NOT_FOUND);
    }

}