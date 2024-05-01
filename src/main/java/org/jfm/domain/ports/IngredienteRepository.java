package org.jfm.domain.ports;

import org.jfm.domain.entities.Ingrediente;
import java.util.List;

public interface IngredienteRepository {
    public Ingrediente Criar(Ingrediente ingrediente);
    public Ingrediente BuscarPorId(int id);
    public List<Ingrediente> Listar();
    public List<Ingrediente> Buscar(String query);
    public Ingrediente Editar(Ingrediente ingrediente);
    public void Remover(int id);
};
