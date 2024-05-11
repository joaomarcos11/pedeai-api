package org.jfm.domain.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Cliente;
import org.jfm.domain.ports.ClienteRepository;
import org.jfm.domain.usecases.ClienteUseCase;

public class ClienteService implements ClienteUseCase {

    ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UUID criar(Cliente cliente) {
        cliente.setId(UUID.randomUUID());
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
        return clienteRepository.buscarPorCpf(cpf);
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
