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
    protected Double tempoVazio = 0.;
    protected Double tempoVazioTotal = 0.;
    protected Integer nClientesChegadas = 0;
    protected Integer nClientesComServidorVazio = 0;
    protected Cliente clienteAtual;

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
            temporizador.registrarTarefaPorAtraso(getClasseRandomLambda(c), (tempo) -> InsereClienteNaFila(tempo, c));
        }
    }

    protected void LiberaServidorEBuscaNovoCliente(Double horarioDeEntradaNoServidor, Cliente cliente){
        setServidorOcupado(false);
        setTempoVazio(temporizador.getTempoAtual());
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
        clienteAtual = cliente;
        setTempoVazioTotal(tempoVazioTotal + temporizador.getTempoAtual() - tempoVazio);
        temporizador.registrarTarefaPorAtraso(cliente.getTempoDeServico(), (tempo) -> LiberaServidorEBuscaNovoCliente(tempo, cliente));
    }

    protected void InsereClienteNaFila(Double horarioDeEntrada, Classe classe){
        Cliente cliente = new Cliente(classe, horarioDeEntrada);
        // Com preempção: coloca direto no servidor
        if(!servidorOcupado){
            cliente.setTempoSaida(horarioDeEntrada);
            cliente.setTrabalhoPendente(getTrabalhoPendenteAtual(0));
            metricaDeInteresse.setFracaoDeChegadasServidorVazio(++nClientesComServidorVazio/(double)++nClientesChegadas);
            ProcessarCliente(cliente);
        }else{
            cliente.setTrabalhoPendente(getTrabalhoPendenteAtual(clienteAtual.getTempoDeServico())); //TODO: XResidual do cliente que está ocupando o servidor
            metricaDeInteresse.setFracaoDeChegadasServidorVazio(nClientesComServidorVazio/(double)++nClientesChegadas);
            fila.adicionar(cliente,false);
        }
        // Usa-se Random.Exponecial Sempre pois a entrada eh sempre Memoryless
        temporizador.registrarTarefaPorAtraso(getClasseRandomLambda(classe), (tempo) -> InsereClienteNaFila(tempo, classe));
    }

    protected Double getTrabalhoPendenteAtual(double xResidual) {
        //Nq1*E[X1] + Nq2*E[X2] + Xr
        Double total = 0.;
        for(List<Cliente> f : fila.getFilas()) {
            for(Cliente cliente : f)
                total += 1/cliente.getClasse().getMi();
        }

        return total + xResidual;
    }

    public MetricaDeInteresse iniciarSimulacao(){
        temporizador.play();
        getMetricaDeInteresse().setFracaoDeTempoServidorVazio(this.tempoVazioTotal / temporizador.getTempoFinal());
        return getMetricaDeInteresse();
    }

    public MetricaDeInteresse continuarSimulação(Double tempoFinal){
        getMetricaDeInteresse().setMediaCalculada(null);
        temporizador.setTempoFinal(tempoFinal);
        return iniciarSimulacao();
    }

    public void setTempoVazioTotal(Double tempoVazioTotal) {
        this.tempoVazioTotal = tempoVazioTotal;
    }

    public void setTempoVazio(Double tempoVazio) {
        this.tempoVazio = tempoVazio;
    }

    protected Double getClasseRandomLambda(Classe classe) {
        return Random.Exponencial(classe.getLambda());
    }
}
