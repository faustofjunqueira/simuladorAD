package simulador;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by fausto on 3/11/16.
 */
public class AgendaTaferas {
    private List<Tarefa> tarefas;

    public AgendaTaferas() {
        this.tarefas = new ArrayList<>(10);
    }

    public void removerTarefa(Tarefa tarefa){
        for(int i = 0; i < tarefas.size(); i++){
            if(tarefas.get(i).hashCode() == tarefa.hashCode()){
                tarefas.remove(i);
            }
        }
    }

    public void adicionarTarefa(Tarefa tarefa){
        tarefas.add(tarefa);
    }

    public Tarefa proximaTarefa(){
        if(tarefas.size() > 0){
            tarefas.sort(new TarefaComparator());
            Tarefa tarefa = tarefas.get(0);
            tarefas.remove(0);
            return tarefa;
        }else{
            return null;
        }
    }
}
