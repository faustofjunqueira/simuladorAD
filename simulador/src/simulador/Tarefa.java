package simulador;

import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;

/**
 * Created by fausto on 3/11/16.
 */
public class Tarefa {
    private static Integer SEQUENCE_ID = 0;

    private Integer id;
    private Double horario;
    private Consumer<Double> funcao;

    public Tarefa(Double horario, Consumer<Double> funcao) {
        this.id = Tarefa.SEQUENCE_ID++;
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

    @Override
    public int hashCode()
    {
        return id;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (obj == null)
            return false;
        if (this == obj)
            return false;
        if (!(obj instanceof Tarefa))
            return false;
        final Tarefa other = (Tarefa) obj;
        return id == null ? other.id == null
                : id.equals(other.id);
    }

}
