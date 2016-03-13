package exercicio5;

import simulador.Classe;
import simulador.Random;
import simulador.Simulacao;
import simulador.SimulacaoPreemptiva;

/**
 * Created by fausto on 3/12/16.
 */
public class CenarioII {

    public static void main(String [] args) {
        Simulacao simulacao = new SimulacaoPreemptiva(
            new Classe( 0, 1,0,() -> Random.Exponencial(1)),
            new Classe(.2,.5,1,() -> Random.Exponencial(.5))
        );

        simulacao.executar(.05, .61, .05);
    }
}
