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

    private Double getTempoAtual() {
        return tempoAtual;
    }

    private Double getTempoFinal() {
        return tempoFinal;
    }

    private AgendaTaferas getAgenda() {
        return agenda;
    }

    public void registrarTarefaPorAtraso(Double atraso, Consumer<Double> funcao) {
        Double horario = tempoAtual + atraso;
        agenda.adicionarTarefa(new Tarefa(horario,funcao));
    }

    public void play(){
        while(tempoAtual < tempoFinal){
            Tarefa proximaTarefa = agenda.proximaTarefa();
            if(proximaTarefa == null){
                break;
            }
            tempoAtual = proximaTarefa.getHorario();
            proximaTarefa.executar();
        }
    }

}