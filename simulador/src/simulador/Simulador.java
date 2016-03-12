package simulador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fausto on 3/12/16.
 */
public class Simulador {
    private Boolean servidorOcupado = false;
    private Temporizador temporizador;
    private Fila fila;
    private MetricaDeInteresse metricaDeInteresse;

    public Simulador(Double tempoFinal, Classe classeObrigatoria, Classe ...classes) {
        temporizador = new Temporizador(tempoFinal);
        metricaDeInteresse = new MetricaDeInteresse();

        List<Classe> listaClasse = new ArrayList<>(classes.length + 1);
        listaClasse.add(classeObrigatoria);
        listaClasse.addAll(Arrays.asList(classes));

        fila = new Fila(listaClasse);
        prepararSimulacao(listaClasse);
    }

    private void setServidorOcupado(Boolean servidorOcupado) {
        this.servidorOcupado = servidorOcupado;
    }

    private Boolean getServidorOcupado() {
        return servidorOcupado;
    }

    private Temporizador getTemporizador() {
        return temporizador;
    }

    private Fila getFila() {
        return fila;
    }

    public MetricaDeInteresse getMetricaDeInteresse() {
        return metricaDeInteresse;
    }

    private void prepararSimulacao(List<Classe> classes){
        for(Classe c : classes){
            temporizador.registrarTarefaPorAtraso(c.getRandom(), (tempo) -> InsereClienteNaFila(tempo, c));
        }
    }

    private void LiberaServidorEBuscaNovoCliente(Double horarioDeEntradaNoServidor, Cliente cliente){
        setServidorOcupado(false);
        metricaDeInteresse.adicionaClienteProcessado(cliente);
        if(fila.tamanho() > 0){
            Cliente novoCliente = fila.remover();
            novoCliente.setTempoSaida(horarioDeEntradaNoServidor);
            ProcessarCliente(novoCliente);
        }
    }

    private void ProcessarCliente(Cliente cliente){
        setServidorOcupado(true);
        temporizador.registrarTarefaPorAtraso(cliente.getClasse().getRandom(), (tempo) -> LiberaServidorEBuscaNovoCliente(tempo, cliente));
    }

    private void InsereClienteNaFila(Double horarioDeEntrada, Classe classe){
        Cliente cliente = new Cliente(classe, horarioDeEntrada);
        if(!servidorOcupado){
            cliente.setTempoSaida(horarioDeEntrada);
            ProcessarCliente(cliente);
        }else{
            fila.adicionar(cliente);
        }
        // Usa-se Random.Exponecial Sempre pois a entrada eh sempre Memoryless
        temporizador.registrarTarefaPorAtraso(Random.Exponencial(classe.getLambda()), (tempo) -> InsereClienteNaFila(tempo, classe));
    }

    public MetricaDeInteresse iniciarSimulacao(){
        temporizador.play();
        return getMetricaDeInteresse();
    }

    public MetricaDeInteresse continuarSimulação(Double tempoFinal){
        getMetricaDeInteresse().setMediaCalculada(null);
        temporizador.setTempoFinal(tempoFinal);
        return iniciarSimulacao();
    }

}
