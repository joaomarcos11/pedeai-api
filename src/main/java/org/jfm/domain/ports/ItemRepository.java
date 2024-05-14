package org.jfm.domain.ports;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;

import java.util.List;
import java.util.UUID;

public interface ItemRepository {
    public void criar(Item item);

    public List<Item> listar();

    public Item buscarPorId(UUID id);

    public void editar(Item item);

    public void remover(Item item);

    public List<Item> listarPorCategoria(Categoria categoria);
}
