package simulador;

import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;

/**
 * Created by fausto on 3/11/16.
 */
public class Tarefa {
    private Double horario;
    private Consumer<Double> funcao;

    public Tarefa(Double horario, Consumer<Double> funcao) {
        this.horario = horario;
        this.funcao = funcao;
    }

    public Double getHorario() {
        return horario;
    }

    public void setHorario(Double horario) {
        this.horario = horario;
    }

    public void executar(Double hora){
        this.funcao.accept(hora);
    }

}
