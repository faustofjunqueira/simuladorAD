package simulador;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by fausto on 3/11/16.
 */
public class AgendaTaferas {
    private SortedSet<Tarefa> tarefas;

    public AgendaTaferas() {
        this.tarefas = new TreeSet<>(new TarefaComparator());
    }

    public SortedSet<Tarefa> getTarefas() {
        return tarefas;
    }

    public void adicionarTarefa(Tarefa tarefa){
        getTarefas().add(tarefa);
    }

    public Tarefa proximaTarefa(){
        if(getTarefas().size() > 0){
            Tarefa tarefa = getTarefas().first();
            getTarefas().remove(tarefa);
            return tarefa;
        }else{
            return null;
        }
    }
}
