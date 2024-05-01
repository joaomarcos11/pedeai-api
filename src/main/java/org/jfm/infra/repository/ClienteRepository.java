package org.jfm.infra.repository;

import java.util.List;

import org.jfm.domain.entities.Cliente;
import org.jfm.usecase.cliente.IClienteRepository;

import jakarta.enterprise.context.ApplicationScoped;

// @AllArgsConstructor TODO: entender essa anotation aqui
@ApplicationScoped
public class ClienteRepository implements IClienteRepository {

    ClienteDTO clienteDTO;

    @Override
    public List<Cliente> BuscarPorCpf(String cpf) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cliente BuscarPorId(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cliente Criar(Cliente cliente) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cliente Editar(Cliente cliente) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Cliente> Listar() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void Remover(int id) {
        // TODO Auto-generated method stub
        
    }
    
}
