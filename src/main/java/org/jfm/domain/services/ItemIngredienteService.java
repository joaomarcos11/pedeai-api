package org.jfm.domain.services;

import java.util.List;
import org.jfm.domain.entities.ItemIngrediente;
import org.jfm.domain.ports.ItemIngredienteRepository;

public class ItemIngredienteService {

    ItemIngredienteRepository itemIngredienteRepository;

    public ItemIngredienteService(ItemIngredienteRepository itemIngredienteRepository) {
        this.itemIngredienteRepository = itemIngredienteRepository;
    }

    public void criar(ItemIngrediente itemIngrediente) {
        itemIngredienteRepository.criar(itemIngrediente);
    }

    public List<ItemIngrediente> listar() {
        return itemIngredienteRepository.listar();
    }

    public ItemIngrediente buscarPorItemIngrediente(ItemIngrediente itemIngrediente) {
        return itemIngredienteRepository.buscarPorItemIngrediente(itemIngrediente);
    }

    public List<ItemIngrediente> buscarPorItemId(int itemId) {
        return itemIngredienteRepository.buscarPorItemId(itemId);
    }

    public List<ItemIngrediente> buscarPorIngredienteId(int igrendienteId) {
        return itemIngredienteRepository.buscarPorIngredienteId(igrendienteId);
    }

    public void remover(ItemIngrediente itemIngrediente) {
        itemIngredienteRepository.remover(itemIngrediente);
    }

}
