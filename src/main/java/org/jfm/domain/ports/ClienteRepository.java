package org.jfm.domain.ports;

import org.jfm.domain.entities.Cliente;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface ClienteRepository {
    public void criar(Cliente cliente);
    public List<Cliente> listar();
    public Cliente buscarPorId(int id);
    public Cliente buscarPorCpf(String cpf);
    public void editar(Cliente cliente);
    public void remover(Cliente cliente);
};
