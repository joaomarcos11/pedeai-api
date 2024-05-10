package org.jfm.domain.services;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.ports.ItemRepository;

public class ItemService {

    ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public UUID criar(Item item) {
        item.setId(UUID.randomUUID());
        itemRepository.criar(item);

        return item.getId();
    }

    public List<Item> listar() {
        return itemRepository.listar();
    }

    public Item buscarPorId(UUID id) {
        return itemRepository.buscarPorId(id);
    }

    public void editar(Item item) {
        itemRepository.editar(item);
    }

    public void remover(UUID id) {
        Item item = itemRepository.buscarPorId(id);
        itemRepository.remover(item);
    }

    // ---

    public List<Item> listarCategoria(Categoria categoria) {
        return itemRepository.listarPorCategoria(categoria);
    }

}
