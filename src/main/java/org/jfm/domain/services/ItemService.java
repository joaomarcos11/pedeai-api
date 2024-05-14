package org.jfm.domain.services;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.ports.ItemRepository;
import org.jfm.domain.usecases.ItemUseCase;

public class ItemService implements ItemUseCase {

    ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public UUID criar(Item item) {
        item.setId(UUID.randomUUID());
        itemRepository.criar(item);

        return item.getId();
    }

    @Override
    public List<Item> listar() {
        return itemRepository.listar();
    }

    @Override
    public Item buscarPorId(UUID id) {
        return itemRepository.buscarPorId(id);
    }

    @Override
    public void editar(Item item) {
        itemRepository.editar(item);
    }

    @Override
    public void remover(UUID id) {
        Item item = itemRepository.buscarPorId(id);
        itemRepository.remover(item);
    }

    @Override
    public List<Item> listarCategoria(Categoria categoria) {
        return itemRepository.listarPorCategoria(categoria);
    }

}
