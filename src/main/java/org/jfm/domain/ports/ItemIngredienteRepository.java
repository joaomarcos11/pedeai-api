package org.jfm.domain.ports;

import java.util.List;

import org.jfm.domain.entities.ItemIngrediente;

public interface ItemIngredienteRepository {
    public void criar(ItemIngrediente itemIngrediente);

    public List<ItemIngrediente> listar();

    public ItemIngrediente buscarPorItemIngrediente(ItemIngrediente itemIngrediente);

    public List<ItemIngrediente> buscarPorItemId(int itemId);

    public List<ItemIngrediente> buscarPorIngredienteId(int ingredienteId);

    public void remover(ItemIngrediente itemIngrediente);
}
