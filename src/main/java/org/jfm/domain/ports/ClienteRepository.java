package org.jfm.domain.ports;

import org.jfm.domain.entities.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository {
    public void criar(Cliente cliente);

    public List<Cliente> listar();

    public Cliente buscarPorId(UUID id);

    public Cliente buscarPorCpf(String cpf);

    public void editar(Cliente cliente);

    public void remover(Cliente cliente);
};
