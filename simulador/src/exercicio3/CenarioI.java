package exercicio3;

import simulador.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fausto on 3/12/16.
 */
public class CenarioI {

    private final Integer nLoops = 30;
    private final Double tempoFinal = 100000.;
    private Double mi = 1.;
    private Classe classe;

    public CenarioI() {
        classe = new Classe(0, mi, 0, () -> Random.Exponencial(mi));
    }

    public Double executarPessoasNaFila(Double lambda){
        classe.setLambda(lambda);
        List<Double> MediasPessoasNaFilaColhetadas = new ArrayList<>(nLoops);
        Double intervaloInferior;
        Double intervaloSuperior;
        Double media;
        do{
            for(int i = 0; i < nLoops; i++){
                Simulador simulador = new Simulador(tempoFinal, classe);
                MetricaDeInteresse metricaDeInteresse = simulador.iniciarSimulacao();
                MediasPessoasNaFilaColhetadas.add( Metricas.Little(lambda, metricaDeInteresse.getMediaTempoDeEspera()));
            }

            media = Metricas.Media(MediasPessoasNaFilaColhetadas);
            intervaloInferior = Metricas.IntervaloConfiancaInferior(MediasPessoasNaFilaColhetadas);
            intervaloSuperior = Metricas.IntervaloConfiancaSuperior(MediasPessoasNaFilaColhetadas);

        }while( media < intervaloInferior || media > intervaloSuperior );

        return media;
    }

    public Double executarTempoPessoasNaFila(Double lambda){
        classe.setLambda(lambda);
        List<Double> mediasTempoDePessoasNaFila = new ArrayList<>(nLoops);
        Double intervaloInferior;
        Double intervaloSuperior;
        Double media;
        do{
            for(int i = 0; i < nLoops; i++){
                Simulador simulador = new Simulador(tempoFinal, classe);
                MetricaDeInteresse metricaDeInteresse = simulador.iniciarSimulacao();
                mediasTempoDePessoasNaFila.add(metricaDeInteresse.getMediaTempoDeEspera());
            }

            media = Metricas.Media(mediasTempoDePessoasNaFila);
            intervaloInferior = Metricas.IntervaloConfiancaInferior(mediasTempoDePessoasNaFila);
            intervaloSuperior = Metricas.IntervaloConfiancaSuperior(mediasTempoDePessoasNaFila);

        }while( media < intervaloInferior || media > intervaloSuperior );

        return media;
    }

    public static void main(String [] args) {
        CenarioI cenario = new CenarioI();
        System.out.println("Cenario I");
        System.out.println("Media de Pessoas da Fila");
        for(Double lambda = .05; lambda <= .91; lambda += .05){
            System.out.println(cenario.executarPessoasNaFila(lambda));
        }

        System.out.println("Media de tempo das pessoas na fila");
        for(Double lambda = .05; lambda <= .91; lambda += .05){
            System.out.println(cenario.executarTempoPessoasNaFila(lambda));
        }
    }
}
