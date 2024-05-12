package org.jfm.domain.usecases;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Cliente;

public interface ClienteUseCase {
    public UUID criar(Cliente cliente);

    public List<Cliente> listar();

    public Cliente buscarPorId(UUID id);

    public Cliente buscarPorCpf(String cpf);

    public Cliente buscarPorEmail(String email);

    public void editar(Cliente cliente);

    public void remover(UUID id);
}
