package simulador;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
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

    protected Simulador getSimulador(Double tempoFinal, Classe classe1) {
        return new Simulador(tempoFinal, classe1);
    }

    protected Simulador getSimulador(Double tempoFinal, Classe classe1, Classe classe2) {
        return new Simulador(tempoFinal, classe1, classe2);
    }

    public Double executarPessoasNaFila(Double lambda){
        classe1.setLambda(lambda);
        List<Double> MediasPessoasNaFilaColhetadas = new ArrayList<>(nLoops);
        Double intervaloInferior;
        Double intervaloSuperior;
        Double media;
        do{
            for(int i = 0; i < nLoops; i++){
                Simulador simulador = getSimulador(tempoFinal,classe1,classe2);
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
                Simulador simulador = getSimulador(tempoFinal, classe1, classe2);
                MetricaDeInteresse metricaDeInteresse = simulador.iniciarSimulacao();
                mediasTempoDePessoasNaFila.add(metricaDeInteresse.getMediaTempoDeEspera());
            }

            media = Metricas.Media(mediasTempoDePessoasNaFila);
            intervaloInferior = Metricas.IntervaloConfiancaInferior(mediasTempoDePessoasNaFila);
            intervaloSuperior = Metricas.IntervaloConfiancaSuperior(mediasTempoDePessoasNaFila);

        }while( media < intervaloInferior || media > intervaloSuperior );

        return media;
    }

    public Double executarFracaoTempoServidorVazio(Double lambda){
        classe1.setLambda(lambda);
        List<Double> mediasFracaoServidorVazio = new ArrayList<>(nLoops);
        Double intervaloInferior;
        Double intervaloSuperior;
        Double media;
        do{
            for(int i = 0; i < nLoops; i++){
                Simulador simulador = getSimulador(tempoFinal, classe1, classe2);
                MetricaDeInteresse metricaDeInteresse = simulador.iniciarSimulacao();
                mediasFracaoServidorVazio.add(metricaDeInteresse.getFracaoDeTempoServidorVazio());
            }

            media = Metricas.Media(mediasFracaoServidorVazio);
            intervaloInferior = Metricas.IntervaloConfiancaInferior(mediasFracaoServidorVazio);
            intervaloSuperior = Metricas.IntervaloConfiancaSuperior(mediasFracaoServidorVazio);

        }while( media < intervaloInferior || media > intervaloSuperior );

        return media;
    }


    public Double executarFracaoChegadasServidorVazio(Double lambda){
        classe1.setLambda(lambda);
        List<Double> mediasFracaoChegadasServidorVazio = new ArrayList<>(nLoops);
        Double intervaloInferior;
        Double intervaloSuperior;
        Double media;
        do{
            for(int i = 0; i < nLoops; i++){
                Simulador simulador = getSimulador(tempoFinal, classe1, classe2);
                MetricaDeInteresse metricaDeInteresse = simulador.iniciarSimulacao();
                mediasFracaoChegadasServidorVazio.add(metricaDeInteresse.getFracaoDeChegadasServidorVazio());
            }

            media = Metricas.Media(mediasFracaoChegadasServidorVazio);
            intervaloInferior = Metricas.IntervaloConfiancaInferior(mediasFracaoChegadasServidorVazio);
            intervaloSuperior = Metricas.IntervaloConfiancaSuperior(mediasFracaoChegadasServidorVazio);

        }while( media < intervaloInferior || media > intervaloSuperior );

        return media;
    }

    public void executar(double inicio, double _final, double incremento){
        /*System.out.println("Media de Pessoas da Fila");
        for(Double lambda = inicio; lambda <= _final; lambda += incremento){
            System.out.println(executarPessoasNaFila(lambda));
        }

        System.out.println("Media de tempo das pessoas na fila");
        for(Double lambda = inicio; lambda <= _final; lambda += incremento){
            System.out.println(executarTempoPessoasNaFila(lambda));
        }

        // Questão 7
        System.out.println("Fracao em que o servidor fica vazio");
        for(Double lambda = inicio; lambda <= _final; lambda += incremento){
            System.out.println(executarFracaoTempoServidorVazio(lambda));
        }

        System.out.println("Fracao de chegadas em que servidor se encontra vazio");
        for(Double lambda = inicio; lambda <= _final; lambda += incremento){
            System.out.println(executarFracaoChegadasServidorVazio(lambda));
        }*/

        //Questão 8
        System.out.println("Trabalho Pendente");
        for(Double lambda = inicio; lambda <= _final; lambda += incremento){
            System.out.println(executarTrabalhoPendente(lambda));
        }
    }

    //Questão 6
    public void executarTempoEntreSaidasDeCliente(String filename) {
        System.out.println();
        Simulador simulador = getSimulador(tempoFinal, classe1, classe2);
        MetricaDeInteresse metricaDeInteresse = simulador.iniciarSimulacao();
        List<Double> tempoEntreSaidas = metricaDeInteresse.getTempoEntreSaidas();
        tempoEntreSaidas.sort(Comparator.<Double>naturalOrder());
        PrintWriter out = null;
        try {
            out = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < tempoEntreSaidas.size(); i++) {
            out.println(tempoEntreSaidas.get(i) + " " + (i + 1) / (double) tempoEntreSaidas.size());
        }
        out.close();
    }

    //Questão 8
    public Double executarTrabalhoPendente(double lambda) {
        classe1.setLambda(lambda);
        List<Double> mediasTrabalhoPendente = new ArrayList<>(nLoops);
        Double intervaloInferior;
        Double intervaloSuperior;
        Double media;
        do{
            for(int i = 0; i < nLoops; i++){
                Simulador simulador = getSimulador(tempoFinal,classe1,classe2);
                MetricaDeInteresse metricaDeInteresse = simulador.iniciarSimulacao();
                mediasTrabalhoPendente.add(metricaDeInteresse.getTrabalhoPendente());
            }

            media = Metricas.Media(mediasTrabalhoPendente);
            intervaloInferior = Metricas.IntervaloConfiancaInferior(mediasTrabalhoPendente);
            intervaloSuperior = Metricas.IntervaloConfiancaSuperior(mediasTrabalhoPendente);

        }while( media < intervaloInferior || media > intervaloSuperior );

        return media;
    }
}
