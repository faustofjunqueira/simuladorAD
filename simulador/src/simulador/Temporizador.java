package simulador;

import java.util.function.Consumer;

/**
 * Created by fausto on 3/11/16.
 */
public class Temporizador {

    private Double tempoAtual;
    private Double tempoFinal;
    private AgendaTaferas agenda;

    public Temporizador(Double tempoFinal) {
        this.tempoAtual = .0;
        this.tempoFinal = tempoFinal;
        this.agenda = new AgendaTaferas();
    }

    public Double getTempoAtual() {
        return tempoAtual;
    }

    public Double getTempoFinal() {
        return tempoFinal;
    }

    public AgendaTaferas getAgenda() {
        return agenda;
    }

    public void registrarTarefaPorAtraso(Double atraso, Consumer<Double> funcao) {
        Double horario = tempoAtual + atraso;
        agenda.adicionarTarefa(new Tarefa(horario,funcao));
    }

    public void play(){
        while(tempoAtual < tempoFinal){
            Tarefa proximaTarefa = agenda.proximaTarefa();
            tempoAtual = proximaTarefa.getHorario();
            proximaTarefa.executar();
        }
    }

}