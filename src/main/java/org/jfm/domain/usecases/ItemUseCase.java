package org.jfm.domain.usecases;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;

public interface ItemUseCase {
    public UUID criar(Item item);

    public List<Item> listar();

    public Item buscarPorId(UUID id);

    public void editar(Item item);

    public void remover(UUID id);

    public List<Item> listarCategoria(Categoria categoria);
}
