package org.jfm.domain.services;

import java.util.List;

import org.jfm.domain.entities.Cliente;
import org.jfm.domain.ports.ClienteRepository;

public class ClienteService {
    ClienteRepository clienteRepository;

    public Cliente criar(Cliente cliente) {
        // TODO: onde fica a gestão da criação do ID? aqui, automatica pelo banco de dados? definir...
        Cliente clienteCriado = clienteRepository.criar(cliente);
        return clienteCriado;
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
        clienteRepository.remover(id);
        return;
    };
}
