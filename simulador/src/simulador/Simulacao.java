package simulador;

import simulador.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fausto on 3/12/16.
 */
public class Simulacao {
    private final Integer nLoops = 30;
    private final Double tempoFinal = 100000.;
    private Classe classe1;
    private Classe classe2;

    public Simulacao(Classe classe1, Classe classe2) {
        this.classe1 = classe1;
        if(classe2 == null){
            this.classe2 = new Classe(0,0,0,null);
        }else{
            this.classe2 = classe2;
        }
    }

    protected Simulador gerarSimulador(Double tempoFinal, Classe classe1, Classe classe2){
        if(classe2 != null){
            return new Simulador(tempoFinal, classe1, classe2);
        }else{
            return new Simulador(tempoFinal, classe1);
        }
    }

    public Double executarPessoasNaFila(Double lambda){
        classe1.setLambda(lambda);
        List<Double> MediasPessoasNaFilaColhetadas = new ArrayList<>(nLoops);
        Double intervaloInferior;
        Double intervaloSuperior;
        Double media;
        do{
            for(int i = 0; i < nLoops; i++){
                Simulador simulador = gerarSimulador(tempoFinal,classe1,classe2);
                MetricaDeInteresse metricaDeInteresse = simulador.iniciarSimulacao();
                MediasPessoasNaFilaColhetadas.add( Metricas.Little(classe1.getLambda() + classe2.getLambda(), metricaDeInteresse.getMediaTempoDeEspera()));
            }

            media = Metricas.Media(MediasPessoasNaFilaColhetadas);
            intervaloInferior = Metricas.IntervaloConfiancaInferior(MediasPessoasNaFilaColhetadas);
            intervaloSuperior = Metricas.IntervaloConfiancaSuperior(MediasPessoasNaFilaColhetadas);

        }while( media < intervaloInferior || media > intervaloSuperior );

        return media;
    }

    public Double executarTempoPessoasNaFila(Double lambda){
        classe1.setLambda(lambda);
        List<Double> mediasTempoDePessoasNaFila = new ArrayList<>(nLoops);
        Double intervaloInferior;
        Double intervaloSuperior;
        Double media;
        do{
            for(int i = 0; i < nLoops; i++){
                Simulador simulador = new Simulador(tempoFinal, classe1, classe2);
                MetricaDeInteresse metricaDeInteresse = simulador.iniciarSimulacao();
                mediasTempoDePessoasNaFila.add(metricaDeInteresse.getMediaTempoDeEspera());
            }

            media = Metricas.Media(mediasTempoDePessoasNaFila);
            intervaloInferior = Metricas.IntervaloConfiancaInferior(mediasTempoDePessoasNaFila);
            intervaloSuperior = Metricas.IntervaloConfiancaSuperior(mediasTempoDePessoasNaFila);

        }while( media < intervaloInferior || media > intervaloSuperior );

        return media;
    }

    public void executar(double inicio, double _final, double incremento){
        System.out.println("Media de Pessoas da Fila");
        for(Double lambda = inicio; lambda <= _final; lambda += incremento){
            System.out.println(executarPessoasNaFila(lambda));
        }

        System.out.println("Media de tempo das pessoas na fila");
        for(Double lambda = inicio; lambda <= _final; lambda += incremento){
            System.out.println(executarTempoPessoasNaFila(lambda));
        }
    }
}
