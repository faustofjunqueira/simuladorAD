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
    
}
