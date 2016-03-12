package simulador;

import java.util.function.DoubleSupplier;

/**
 * Created by Victor on 11.mar.2016.
 */
public class Classe {
    private double mi;
    private double lambda;
    private Integer prioridade;
    private DoubleSupplier funcao;

    public Classe(double lambda, double mi, Integer prioridade, DoubleSupplier funcao) {
        this.lambda = lambda;
        this.mi = mi;
        this.prioridade = prioridade;
        this.funcao = funcao;
    }

    public Integer getPrioridade() {
        return this.prioridade;
    }

    public double getLambda() {
        return lambda;
    }

    public double getRandom() {
        return funcao.getAsDouble();
    }
}
//temposaida, tempoentrada, delta
