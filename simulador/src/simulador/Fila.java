package simulador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fausto on 3/11/16.
 */
public class Fila<Cliente,Classe> {
    private List<List<Cliente>> filas;

    public Fila(Classe classeObrigatoria,Classe ...classes) {
        for (Classe classe : classes) {
            filas.add(new ArrayList<>(10));
        }
    }

    public List<List<Cliente>> getFilas() {
        return filas;
    }

    public void adicionar(Cliente cliente){
        //TODO: fazer o preemptivo
        //TODO: quando tiver a prioridade, usar aqui;
        //getFilas().get(cliente.prioridade).add(cliente);
    }

    public void remover(Cliente cliente){
        //TODO: quando tiver a prioridade, usar aqui
        //getFilas().get(cliente.prioridade).remove(cliente);
    }

    public Integer tamanho(){
        return getFilas().stream().mapToInt(List::size).sum();
    }
}
