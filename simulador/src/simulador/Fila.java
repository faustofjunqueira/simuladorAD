package simulador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fausto on 3/11/16.
 */
public class Fila{
    private List<List<Cliente>> filas;

    public Fila(List<Classe> classes) {
        filas = new ArrayList<>(classes.size());
        for (Classe classe : classes) {
            filas.add(new ArrayList<>(10));
        }
    }

    public List<List<Cliente>> getFilas() {
        return filas;
    }

    public void adicionar(Cliente cliente, Boolean preemptivo){
        if(preemptivo){
            getFilas().get(cliente.getClasse().getPrioridade()).add(0,cliente);
        }else{
            getFilas().get(cliente.getClasse().getPrioridade()).add(cliente);
        }
    }

    public Cliente remover(){
        for(List<Cliente> lista : getFilas()){
          if(lista.size() > 0){
            Cliente cliente = lista.get(0);
            lista.remove(cliente);
            return cliente;
          }
        }
        return null;
    }

    public Integer tamanho(){
        return getFilas().stream().mapToInt(List::size).sum();
    }
}
