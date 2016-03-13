package exercicio6;

import simulador.Classe;
import simulador.Random;
import simulador.Simulacao;

/**
 * Created by Victor on 13.mar.2016.
 */
public class Main {
    public static void main(String[] args) {
        //Cenario 1
        Simulacao simulacao = new Simulacao(
                new Classe(.9, 1,0,() -> Random.Exponencial(1)), null
        );

        simulacao.executarTempoEntreSaidasDeCliente("q6-cenario1.txt");

        //Cenário 2

        simulacao = new Simulacao(
                new Classe(.6, 1,0,() -> Random.Exponencial(1)),
                new Classe(.2, .5,0,() -> Random.Exponencial(.5))
        );

        simulacao.executarTempoEntreSaidasDeCliente("q6-cenario2.txt");

        //Cenário 3

        simulacao = new Simulacao(
                new Classe(.6, 1,0,() -> Random.Deterministico(1)),
                new Classe(.2,.5,0,() -> Random.Deterministico(.5))
        );

        simulacao.executarTempoEntreSaidasDeCliente("q6-cenario3.txt");


        //Cenário 4
        simulacao = new Simulacao(
                new Classe(.08, 1,0,() -> Random.Uniforme(5,15)),
                new Classe(.05,.5,0,() -> Random.Uniforme(1, 3))
        );

        simulacao.executarTempoEntreSaidasDeCliente("q6-cenario4.txt");
    }
}
