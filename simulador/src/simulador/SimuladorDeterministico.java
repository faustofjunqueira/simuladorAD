package simulador;

/**
 * Created by Victor on 13.mar.2016.
 */
public class SimuladorDeterministico extends Simulador{

    public SimuladorDeterministico(Double tempoFinal, Classe classeObrigatoria, Classe... classes) {
        super(tempoFinal, classeObrigatoria, classes);
    }

    @Override
    protected Double getClasseRandomLambda(Classe classe) {
        return Random.Deterministico(classe.getLambda());
    }
}
