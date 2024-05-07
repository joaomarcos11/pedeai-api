package org.jfm.domain.ports;

import org.jfm.domain.entities.Ingrediente;
import java.util.List;

public interface IngredienteRepository {
    public void criar(Ingrediente ingrediente);
    
    public List<Ingrediente> listar();
    
    public Ingrediente buscarPorId(int id);

    public void editar(Ingrediente ingrediente);

    public void remover(Ingrediente ingrediente);
};
