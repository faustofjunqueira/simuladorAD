package simulador;

import java.util.Comparator;

/**
 * Created by fausto on 3/11/16.
 */
public class TarefaComparator implements Comparator<Tarefa> {

    @Override
    public int compare(Tarefa t1, Tarefa t2) {
        Double diff = t1.getHorario() - t2.getHorario();
        if(diff>0) return 1;
        else if(diff < 0) return -1;
        else return 0;
    }
}
