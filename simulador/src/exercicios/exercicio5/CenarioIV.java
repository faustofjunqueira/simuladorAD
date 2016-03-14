package exercicios.exercicio5;

import simulador.Classe;
import simulador.Random;
import simulador.Simulacao;
import simulador.SimulacaoPreemptiva;

/**
 * Created by fausto on 3/12/16.
 */
public class CenarioIV {

    public static void main(String [] args) {
        Simulacao simulacao = new SimulacaoPreemptiva(
                new Classe(.08,1,0,() -> Random.Uniforme(5,15)),
                new Classe(.05,1,1,() -> Random.Uniforme(1, 3))
        );

        simulacao.executar(.08, 0.081, .05);
    }
}
