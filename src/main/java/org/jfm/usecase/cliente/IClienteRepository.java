package org.jfm.usecase.cliente;

import org.jfm.domain.entities.Cliente;
import java.util.List;

public interface IClienteRepository {
    public Cliente Criar(Cliente cliente);

    public Cliente BuscarPorId(int id);

    public List<Cliente> Listar();

    public List<Cliente> BuscarPorCpf(String cpf);

    public Cliente Editar(Cliente cliente);

    public void Remover(int id);
};
