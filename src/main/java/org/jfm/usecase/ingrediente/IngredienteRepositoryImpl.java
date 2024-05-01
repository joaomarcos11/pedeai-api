import java.util.List;

import org.jfm.domain.entities.Ingrediente;

@NamedQuery("nome")
public class IngredienteRepositoryImpl implements IngredienteRepository {

    @Id
    private int id;
    private int


    @Override
    public List<Ingrediente> Buscar(String query) {
        query = NameQuery("nome", "joão da silva")
        resultado = query.executar();

        Cliente cliente = new Cliente();
        cliente.nome = resultado.nome(); 

        return cliente

        return null;
    }

    @Override
    public Ingrediente BuscarPorId(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ingrediente Criar(Ingrediente ingrediente) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Ingrediente Editar(Ingrediente ingrediente) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Ingrediente> Listar() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void Remover(int id) {
        // TODO Auto-generated method stub
        
    }
    
}
