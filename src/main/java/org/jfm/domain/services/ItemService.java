package org.jfm.domain.services;

import java.util.List;
import java.util.Random;

import org.jfm.domain.entities.Item;
import org.jfm.domain.ports.ItemRepository;

public class ItemService {

    ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public int criar(Item item) {
        // FIXME: ver como é feita a implementação dos ids
        Random rand = new Random();

        item.setId(rand.nextInt());
        itemRepository.criar(item);

        return item.getId();
    }

    public List<Item> listar() {
        return itemRepository.listar();
    }

    public Item buscarPorId(int id) {
        return itemRepository.buscarPorId(id);
    }

    public void editar(Item item) {
        itemRepository.editar(item);
    }

    public void remover(int id) {
        Item item = itemRepository.buscarPorId(id);
        itemRepository.remover(item);
    }

}
