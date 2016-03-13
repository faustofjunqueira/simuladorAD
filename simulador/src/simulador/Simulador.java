package simulador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fausto on 3/12/16.
 */
public class Simulador {
    protected Boolean servidorOcupado = false;
    protected Temporizador temporizador;
    protected Fila fila;
    protected MetricaDeInteresse metricaDeInteresse;

    public Simulador(Double tempoFinal, Classe classeObrigatoria, Classe ...classes) {
        temporizador = new Temporizador(tempoFinal);
        metricaDeInteresse = new MetricaDeInteresse();

        List<Classe> listaClasse = new ArrayList<>(classes.length + 1);
        listaClasse.add(classeObrigatoria);
        listaClasse.addAll(Arrays.asList(classes));

        fila = new Fila(listaClasse);
        prepararSimulacao(listaClasse);
    }

    protected void setServidorOcupado(Boolean servidorOcupado) {
        this.servidorOcupado = servidorOcupado;
    }

    protected Boolean getServidorOcupado() {
        return servidorOcupado;
    }

    protected Temporizador getTemporizador() {
        return temporizador;
    }

    protected Fila getFila() {
        return fila;
    }

    public MetricaDeInteresse getMetricaDeInteresse() {
        return metricaDeInteresse;
    }

    protected void prepararSimulacao(List<Classe> classes){
        for(Classe c : classes){
            temporizador.registrarTarefaPorAtraso(Random.Exponencial(c.getLambda()), (tempo) -> InsereClienteNaFila(tempo, c));
        }
    }

    protected void LiberaServidorEBuscaNovoCliente(Double horarioDeEntradaNoServidor, Cliente cliente){
        setServidorOcupado(false);
        metricaDeInteresse.adicionaClienteProcessado(cliente);
        if(fila.tamanho() > 0){
            Cliente novoCliente = fila.remover();
            novoCliente.setTempoSaida(horarioDeEntradaNoServidor);
            ProcessarCliente(novoCliente);
        }
    }

    protected void ProcessarCliente(Cliente cliente){
        // com preempçao: tira o cliente, salva o tempo que ainda resta e coloca o novo no servidor
        setServidorOcupado(true);
        temporizador.registrarTarefaPorAtraso(cliente.getClasse().getRandom(), (tempo) -> LiberaServidorEBuscaNovoCliente(tempo, cliente));
    }

    protected void InsereClienteNaFila(Double horarioDeEntrada, Classe classe){
        Cliente cliente = new Cliente(classe, horarioDeEntrada);
        // Com preempção: coloca direto no servidor
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
