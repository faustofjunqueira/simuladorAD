package simulador;

/**
 * Created by Victor on 13.mar.2016.
 */
public class SimulacaoDeterministica extends Simulacao{

    public SimulacaoDeterministica(Classe classe1, Classe classe2) {
        super(classe1, classe2);
    }

    @Override
    protected Simulador getSimulador(Double tempoFinal, Classe classe1) {
        return new SimuladorDeterministico(tempoFinal, classe1);
    }

    @Override
    protected Simulador getSimulador(Double tempoFinal, Classe classe1, Classe classe2) {
        return new SimuladorDeterministico(tempoFinal, classe1, classe2);
    }
}
