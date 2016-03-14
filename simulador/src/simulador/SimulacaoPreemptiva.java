package simulador;

/**
 * Created by fausto on 3/13/16.
 */
public class SimulacaoPreemptiva extends Simulacao{

    public SimulacaoPreemptiva(Classe classe1, Classe classe2) {
        super(classe1, classe2);
    }

    @Override
    protected Simulador getSimulador(Double tempoFinal, Classe classe1) {
        return new SimuladorPreemptivo(tempoFinal, classe1);
    }

    @Override
    protected Simulador getSimulador(Double tempoFinal, Classe classe1, Classe classe2) {
        return new SimuladorPreemptivo(tempoFinal, classe1, classe2);
    }
}
