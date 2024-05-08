package org.jfm.domain.services;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Ingrediente;
import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.ItemIngrediente;
import org.jfm.domain.ports.ItemRepository;

public class ItemService {

    ItemRepository itemRepository;

    // TODO: adicionei isso aqui, ver como funciona os agregados...
    ItemIngredienteService itemIngredienteService;

    // TODO: adicionei isso aqui, ver como funciona os agregados...
    IngredienteService ingredienteService;

    public ItemService(ItemRepository itemRepository, ItemIngredienteService itemIngredienteService,
            IngredienteService ingredienteService) {
        this.itemRepository = itemRepository;
        this.itemIngredienteService = itemIngredienteService;
        this.ingredienteService = ingredienteService;
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

    // ---

    // listar ingredientes válidos para o item
    public List<Ingrediente> listarIngredientesRelacionados(Item item) {
        List<ItemIngrediente> itemIngredientes = itemIngredienteService.buscarPorItemId(item.getId());
        return itemIngredientes.stream().map(i -> ingredienteService.buscarPorId(i.getIdItem()))
                .collect(Collectors.toList());
    }

    // verificar se ingrediente é valido para Item
    // TODO: fazer esse tipo de verificação?

}
