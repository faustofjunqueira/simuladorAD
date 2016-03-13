package exercicio4;

import simulador.Classe;
import simulador.Random;
import simulador.Simulacao;

/**
 * Created by fausto on 3/12/16.
 */
public class CenarioI {

    public static void main(String [] args) {
        Simulacao simulacao = new Simulacao(
            new Classe(0, 1,0,() -> Random.Exponencial(1)), null
        );

        simulacao.executar(.05, .91, .05);
    }
}
