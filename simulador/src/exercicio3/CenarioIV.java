package exercicio3;

import simulador.*;

/**
 * Created by fausto on 3/12/16.
 */
public class CenarioIV {

    public static void main(String [] args) {
        Simulacao simulacao = new Simulacao(
            new Classe( 0, 1,0,() -> Random.Uniforme(5,15)),
            new Classe(.2,.5,0,() -> Random.Uniforme(1, 3))
        );

        simulacao.executar(.05, .61, .05);
    }
}
