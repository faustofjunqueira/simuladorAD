package exercicios.exercicio9;

import simulador.MetricaRejuvenecimento;
import simulador.SimuladorRejuvenecimento;

/**
 * Created by fausto on 3/16/16.
 */
public class SimulacaoRejuvenecimento {

    static Double XEstrela(Double l, Double s){
        return l/(l+s);
    }

    public static void main(String[] args) {
        Double rho = 0.8;
        Double lambda = 0.5;
        Double tempoFinal = 100000.;

        SimuladorRejuvenecimento simulador = new SimuladorRejuvenecimento(tempoFinal, rho, lambda);

        MetricaRejuvenecimento metrica = simulador.executar();

        System.out.println(String.format("3.1) Seja p a probabilidade de falhar, logo p(simulacao) = %f", metrica.getProbabilidadeDeFalhar()));
        System.out.println(String.format("3.1) Seja p a probabilidade de falhar, logo p(analítico) = %f", XEstrela(lambda,rho) ));
        System.out.println("-------------------------------------------");
        System.out.println(String.format("3.2) Numero de vezes que o sistema reinicia, até falhar, por simulacao = %f", metrica.getnRejuvenecimentoAteFalhar()));
        System.out.println(String.format("3.2) Numero de vezes que o sistema reinicia, até falhar, analítico %f", 1/XEstrela(lambda,rho)));
        System.out.println("-------------------------------------------");
        System.out.println(String.format("3.3) Tempo do último rejuvenescimento   até a falha, simulacao = %f", metrica.getTempoUltimoRejuvenescimento()));
        System.out.println(String.format("3.3) Tempo do último rejuvenescimento   até a falha, analítico = %f", (1/(lambda + rho))));

    }
}
