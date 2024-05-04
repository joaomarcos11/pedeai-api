package org.jfm.domain.services;

import java.time.Instant;
import java.util.List;
import java.util.Random;

import org.jfm.domain.entities.Cliente;
import org.jfm.domain.ports.ClienteRepository;

public class ClienteService {

    ClienteRepository clienteRepository;

    // TODO: trocar por All-Args constructor?
    public ClienteService (ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public int criar(Cliente cliente) {
        // TODO: onde fica a gestão da criação do ID? aqui, automatica pelo banco de dados? definir...

        Random rand = new Random(); // TODO: substituir por algo mais prático

        cliente.setId(rand.nextInt());
        cliente.setDataCriacao(Instant.now());
        cliente.setDataAtualizacao(null);
        clienteRepository.criar(cliente);

        return cliente.getId();
    }

    public List<Cliente> listar(){
        return clienteRepository.listar();
    };

    public Cliente buscarPorId(int id){
        Cliente cliente = clienteRepository.buscarPorId(id);
        return cliente;
    };

    public Cliente buscarPorCpf(String cpf){
        return clienteRepository.buscarPorCpf(cpf);
    };

    public void editar(Cliente cliente){
        clienteRepository.editar(cliente);
        return;
    };

    public void remover(int id){
        Cliente cliente = clienteRepository.buscarPorId(id);
        clienteRepository.remover(cliente);
        return;
    };
}
