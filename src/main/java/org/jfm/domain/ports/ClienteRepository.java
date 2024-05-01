package org.jfm.domain.ports;

import org.jfm.domain.entities.Cliente;
import java.util.List;

public interface ClienteRepository {
    public Cliente criar(Cliente cliente);
    public List<Cliente> listar();
    public Cliente buscarPorId(int id);
    public Cliente buscarPorCpf(String cpf);
    public void editar(Cliente cliente);
    public void remover(int id);
};
