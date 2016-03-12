package simulador;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 * Created by fausto on 3/11/16.
 */
public class Temporizador {

    private Double tempoAtual;
    private Double tempoFinal;
    private AgendaTaferas agenda;
    private SortedSet<Tarefa> tarefasPendentes;

    public Temporizador(Double tempoFinal) {
        this.tempoAtual = .0;
        this.tempoFinal = tempoFinal;
        this.agenda = new AgendaTaferas();
        this.tarefasPendentes = new TreeSet<>(new TarefaComparator());
    }

    private Double getTempoAtual() {
        return tempoAtual;
    }

    private Double getTempoFinal() {
        return tempoFinal;
    }

    private AgendaTaferas getAgenda() {
        return agenda;
    }

    public SortedSet<Tarefa> getTarefasPendentes() {
        return tarefasPendentes;
    }

    public void setTempoFinal(Double tempoFinal){
        this.tempoFinal += tempoFinal;
    }

    public void registrarTarefaPorAtraso(Double atraso, Consumer<Double> funcao){
        Tarefa tarefa = new Tarefa(atraso,funcao);
        tarefasPendentes.add(tarefa);
    }

    private void registrarTarefa(Tarefa tarefa) {
        // Quando registra uma tarefa, ela fica pendente, quando termina a rodada do play, então ele ordena e insere do menor para maior
        tarefa.setHorario(tarefa.getHorario() + tempoAtual);
        agenda.adicionarTarefa(tarefa);
    }

    private void validarTarefasPendentes(){
        while (tarefasPendentes.size() > 0){
            Tarefa tarefa = getTarefasPendentes().first();
            getTarefasPendentes().remove(tarefa);
            registrarTarefa(tarefa);
        }
    }

    public void play(){
        while(tempoAtual < tempoFinal){
            validarTarefasPendentes();
            Tarefa proximaTarefa = agenda.proximaTarefa();
            if(proximaTarefa == null){
                break;
            }
            tempoAtual = proximaTarefa.getHorario();
            proximaTarefa.executar(tempoAtual);
        }
    }

}