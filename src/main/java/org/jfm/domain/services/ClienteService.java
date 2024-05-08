package org.jfm.domain.services;

import java.time.Instant;
import java.util.List;
import java.util.Random;

import org.jfm.domain.entities.Cliente;
import org.jfm.domain.ports.ClienteRepository;
import org.jfm.domain.usecases.ClienteUseCase;

public class ClienteService implements ClienteUseCase {

    ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public int criar(Cliente cliente) {
        // TODO: onde fica a gestão da criação do ID? aqui, automatica pelo banco de
        // dados? definir...

        Random rand = new Random(); // TODO: substituir por algo mais prático

        cliente.setId(rand.nextInt());
        cliente.setDataCriacao(Instant.now());
        cliente.setDataAtualizacao(null);
        clienteRepository.criar(cliente);

        return cliente.getId();
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.listar();
    };

    @Override
    public Cliente buscarPorId(int id) {
        return clienteRepository.buscarPorId(id);
    };

    @Override
    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.buscarPorCpf(cpf);
    };

    @Override
    public void editar(Cliente cliente) {
        cliente.setDataAtualizacao(Instant.now());
        clienteRepository.editar(cliente);
    };

    @Override
    public void remover(int id) {
        Cliente cliente = clienteRepository.buscarPorId(id);
        clienteRepository.remover(cliente);
    };
}
