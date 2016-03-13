package exercicio4;

import simulador.Classe;
import simulador.Random;
import simulador.Simulacao;

/**
 * Created by fausto on 3/12/16.
 */
public class CenarioIII {

    public static void main(String [] args) {
        Simulacao simulacao = new Simulacao(
            new Classe( 0, 1,0,() -> Random.Deterministico(1)),
            new Classe(.2,.5,1,() -> Random.Deterministico(.5))
        );

        simulacao.executar(.05, .61, .05);
    }
}
