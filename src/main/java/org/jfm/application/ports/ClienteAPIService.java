package org.jfm.application.ports;

import java.util.List;

import org.jfm.domain.entities.Cliente;

public interface ClienteAPIService {
    public Cliente criar(Cliente cliente);

    public List<Cliente> listar();

    public Cliente buscarPorId(int id);

    public Cliente buscarPorCpf(String cpf);

    public void editar(Cliente cliente);

    public void remover(int id);    
}
