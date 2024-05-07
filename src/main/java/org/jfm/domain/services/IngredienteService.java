package org.jfm.domain.services;

import java.util.List;
import java.util.Random;

import org.jfm.domain.entities.Ingrediente;
import org.jfm.domain.ports.IngredienteRepository;

public class IngredienteService {

    IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public int criar(Ingrediente ingrediente) {
        // TODO: onde fica a gestão da criação do ID? aqui, automatica pelo banco de
        // dados? definir...

        Random rand = new Random();

        ingrediente.setId(rand.nextInt());
        ingredienteRepository.criar(ingrediente);

        return ingrediente.getId();
    }

    public List<Ingrediente> listar() {
        return ingredienteRepository.listar();
    }

    public Ingrediente buscarPorId(int id) {
        return ingredienteRepository.buscarPorId(id);
    }

    public void editar(Ingrediente ingrediente) {
        ingredienteRepository.editar(ingrediente);
    }

    public void remover(int id) {
        Ingrediente ingrediente = ingredienteRepository.buscarPorId(id);
        ingredienteRepository.remover(ingrediente);
    }

}
