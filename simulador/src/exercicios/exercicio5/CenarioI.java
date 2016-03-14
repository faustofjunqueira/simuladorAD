package exercicios.exercicio5;

import simulador.*;

/**
 * Created by fausto on 3/12/16.
 */
public class CenarioI {

    public static void main(String [] args) {

        Simulacao simulacao = new SimulacaoPreemptiva(
            new Classe(0, 1,0,() -> Random.Exponencial(1)), null
        );

        simulacao.executar(.05, .91, .05);
    }
}
