package org.jfm.domain.ports;

import org.jfm.domain.entities.Item;
import java.util.List;

public interface ItemRepository {
    public void criar(Item item);

    public List<Item> listar();

    public Item buscarPorId(int id);

    public void editar(Item item);

    public void remover(Item item);
}
