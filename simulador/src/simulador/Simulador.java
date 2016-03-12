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

    public Simulador(Double tempoFinal, Classe classeObrigatoria, Classe ...classes) {
        temporizador = new Temporizador(tempoFinal);

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

    private void prepararSimulacao(List<Classe> classes){
        for(Classe c : classes){
            temporizador.registrarTarefaPorAtraso(c.getRandom(), (tempo) -> InsereClienteNaFila(tempo, c));
        }
    }

    private void LiberaServidorEBuscaNovoCliente(Double horarioDeTermino, Cliente cliente){
        setServidorOcupado(false);
        cliente.setTempoSaida(horarioDeTermino);
        if(fila.tamanho() > 0){
            Cliente novoCliente = fila.remover();
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
            ProcessarCliente(cliente);
        }else{
            fila.adicionar(cliente);
        }
        // Usa-se Random.Exponecial Sempre pois a entrada eh sempre Memoryless
        temporizador.registrarTarefaPorAtraso(Random.Exponencial(classe.getLambda()), (tempo) -> InsereClienteNaFila(tempo, classe));
    }

    public void iniciarSimulacao(){
        temporizador.play();
    }

    public  void continuarSimulação(Double tempoFinal){
        temporizador.setTempoFinal(tempoFinal);
        temporizador.play();
    }

}
