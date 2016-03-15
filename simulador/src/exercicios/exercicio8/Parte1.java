package exercicios.exercicio8;

import simulador.Classe;
import simulador.Random;
import simulador.Simulacao;
import simulador.SimulacaoPreemptiva;

/**
 * Created by Victor on 13.mar.2016.
 */
public class Parte1 {
    public static void main(String[] args) {
        //Cenário 2 com fila única
        /*Simulacao simulacao = new Simulacao(
                new Classe(0, 1,0,() -> Random.Exponencial(1)),
                new Classe(.2, .5,0,() -> Random.Exponencial(.5))
        );

        simulacao.executar(.05, .61, .05);

        //Cenário 2 com preempção
        simulacao = new SimulacaoPreemptiva(
                new Classe( 0, 1,0,() -> Random.Exponencial(1)),
                new Classe(.2,.5,1,() -> Random.Exponencial(.5))
        );

        simulacao.executar(.05, .61, .05);*/

        //Cenário 3 com fila única
        Simulacao simulacao = new Simulacao(
                new Classe( 0, 1,0,() -> Random.Deterministico(1)),
                new Classe(.2,.5,0,() -> Random.Deterministico(.5))
        );

        simulacao.executar(.05, .61, .05);

        //Cenário 3 com preempção
        simulacao = new SimulacaoPreemptiva(
                new Classe( 0, 1,0,() -> Random.Deterministico(1)),
                new Classe(.2,.5,1,() -> Random.Deterministico(.5))
        );

        simulacao.executar(.05, .61, .05);
    }
}
