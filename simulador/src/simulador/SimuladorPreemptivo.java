package simulador;

/**
 * Created by fausto on 3/13/16.
 */
public class SimuladorPreemptivo extends Simulador {

    private ClientePreemptivo clienteNoServidor;

    public SimuladorPreemptivo(Double tempoFinal, Classe classeObrigatoria, Classe ...classes) {
        super(tempoFinal,classeObrigatoria,classes);
    }

    @Override
    protected void LiberaServidorEBuscaNovoCliente(Double horarioDeEntradaNoServidor, Cliente cliente){
        clienteNoServidor = null;
        setServidorOcupado(false);
        metricaDeInteresse.adicionaClienteProcessado(cliente);
        if(fila.tamanho() > 0){
            Cliente novoCliente = fila.remover();
            novoCliente.setTempoSaida(horarioDeEntradaNoServidor);
            ProcessarCliente(novoCliente);
        }
    }

    @Override
    protected void ProcessarCliente(Cliente cliente){
        ClientePreemptivo clientePreemptivo = (ClientePreemptivo) cliente;

        // com preempçao: tira o cliente, salva o tempo que ainda resta e coloca o novo no servidor
        if(clienteNoServidor != null){
            //Remove cliente do servidor
            clienteNoServidor.marcaSaida(temporizador.getTempoAtual());
            //marca o horario como saida do cara
            //joga ele pra fila
        }
        //Colocar tempo pendente
        temporizador.registrarTarefaPorAtraso(clientePreemptivo.getTempoPendente(), (tempo) -> LiberaServidorEBuscaNovoCliente(tempo, cliente));
    }

    @Override
    protected void InsereClienteNaFila(Double horarioDeEntrada, Classe classe){
        Cliente cliente = new ClientePreemptivo(classe, horarioDeEntrada);
        // Com preempção: coloca direto no servidor
        ProcessarCliente(cliente);
        // Usa-se Random.Exponecial Sempre pois a entrada eh sempre Memoryless
        temporizador.registrarTarefaPorAtraso(Random.Exponencial(classe.getLambda()), (tempo) -> InsereClienteNaFila(tempo, classe));
    }

}
