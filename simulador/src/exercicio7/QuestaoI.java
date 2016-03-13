package exercicio7;

import simulador.Classe;
import simulador.Random;
import simulador.Simulacao;

/**
 * Created by Victor on 13.mar.2016.
 */
public class QuestaoI {
    public static void main(String [] args) {
        Simulacao simulacao = new Simulacao(
                new Classe(0, 1,0,() -> Random.Exponencial(1)), null
        );

        simulacao.executar(.05, .91, .05);
    }
}
