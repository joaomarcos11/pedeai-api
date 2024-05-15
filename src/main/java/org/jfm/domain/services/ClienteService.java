package org.jfm.domain.services;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Cliente;
import org.jfm.domain.exceptions.EntityConflictException;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.ports.ClienteRepository;
import org.jfm.domain.usecases.ClienteUseCase;

public class ClienteService implements ClienteUseCase {

    ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UUID criar(Cliente cliente) {
        cliente.validar();
        
        Cliente clienteBuscadoPorCpf = this.clienteRepository.buscarPorCpf(cliente.getCpf());
        if (clienteBuscadoPorCpf != null) {
            throw new EntityConflictException(ErrosSistemaEnum.CLIENTE_CPF_EMAIL_CONFLICT.getMessage());
        }

        Cliente clienteBuscadoPorEmail = this.clienteRepository.buscarPorEmail(cliente.getEmail());
        if (clienteBuscadoPorEmail != null) {
            throw new EntityConflictException(ErrosSistemaEnum.CLIENTE_CPF_EMAIL_CONFLICT.getMessage());
        }

        cliente.setId(UUID.randomUUID());
        cliente.validar();

        // TODO: verificar se não tem duplicata.

        clienteRepository.criar(cliente);

        return cliente.getId();
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.listar();
    };

    @Override
    public Cliente buscarPorId(UUID id) {
        return clienteRepository.buscarPorId(id);
    };

    @Override
    public Cliente buscarPorCpf(String cpf) {
        Cliente cliente = clienteRepository.buscarPorCpf(cpf);
        if (cliente == null) {
            throw new EntityNotFoundException(ErrosSistemaEnum.CLIENTE_NOT_FOUND.getMessage());
        }

        return cliente;
    };

    @Override
    public Cliente buscarPorEmail(String email) {
        Cliente cliente = clienteRepository.buscarPorEmail(email);
        if (cliente == null) {
            throw new EntityNotFoundException(ErrosSistemaEnum.CLIENTE_NOT_FOUND.getMessage());
        }

        return cliente;
    };

    @Override
    public void editar(Cliente cliente) {
        clienteRepository.editar(cliente);
    };

    @Override
    public void remover(UUID id) {
        Cliente cliente = clienteRepository.buscarPorId(id);
        clienteRepository.remover(cliente);
    };
}
