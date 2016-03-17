package simulador;

/**
 * Created by fausto on 3/13/16.
 */
public class SimuladorPreemptivo extends Simulador {

    private ClientePreemptivo clienteNoServidor;
    private Tarefa tarefaDeProcessamento;

    public SimuladorPreemptivo(Double tempoFinal, Classe classeObrigatoria, Classe ...classes) {
        super(tempoFinal,classeObrigatoria,classes);
    }

    @Override
    protected void LiberaServidorEBuscaNovoCliente(Double horarioDeEntradaNoServidor, Cliente cliente){
        metricaDeInteresse.adicionaClienteProcessado(cliente);
        //tira o cliente do servidor
        clienteNoServidor = null;
        //se tiver gnt na fila
        if(fila.tamanho() > 0) {
            //tira da fila
            ClientePreemptivo novoCliente = (ClientePreemptivo) fila.remover();
            //Marca a saida do cliente
            novoCliente.marcaSaida(horarioDeEntradaNoServidor);
            //Processa o cliente
            ProcessarCliente(novoCliente, horarioDeEntradaNoServidor);
        }
    }

    protected void ProcessarCliente(Cliente cliente, Double horarioAtual){
        //guarda o cliente no servidor
        clienteNoServidor = (ClientePreemptivo) cliente;
        //marca o tempo de saida da fila
        clienteNoServidor.marcaSaida(horarioAtual);
        //cria o evento para terminar o processamento do cliente, baseado do tempo pendente para terminado
        //guarda o evento
        //dispara o evento
        tarefaDeProcessamento =
                temporizador.registrarTarefaPorAtraso(
                        clienteNoServidor.getTempoPendente() , (tempo) -> LiberaServidorEBuscaNovoCliente(tempo, cliente));
    }

    @Override
    protected void InsereClienteNaFila(Double horarioDeEntrada, Classe classe){
        //marca o horario de entrada
        ClientePreemptivo novoCliente = new ClientePreemptivo(classe, horarioDeEntrada);
        //Se tem cliente no Servidor
        if(clienteNoServidor != null) {
            //Se a prioridade for menor do cliente que esta no servidor
            if(novoCliente.getClasse().getPrioridade() < clienteNoServidor.getClasse().getPrioridade()){
                novoCliente.setTrabalhoPendente(getTrabalhoPendenteAtual(0));
                //Cancela o evento de processamento desse cliente
                temporizador.cancelarTarefa(tarefaDeProcessamento);
                //Marca o tempo restante que falta para terminar o processamento
                clienteNoServidor.atualizaTempoPendente(horarioDeEntrada); // i.e horarioDeEntrada corresponde ao horario atual
                //Marca a nova entrada do cliente
                clienteNoServidor.marcaEntrada(horarioDeEntrada);
                //Colocar como proximo na fila
                fila.adicionar(clienteNoServidor, true);
                //Processa o Cliente que chegou
                ProcessarCliente(novoCliente,horarioDeEntrada);
            }else{
                novoCliente.setTrabalhoPendente(getTrabalhoPendenteAtual(novoCliente.getTempoPendente()));
                fila.adicionar(novoCliente,false);
            }
        }else{
            //Processa o cliente que chegou
            novoCliente.setTrabalhoPendente(getTrabalhoPendenteAtual(0));
            ProcessarCliente(novoCliente,horarioDeEntrada);
        }

        // Usa-se Random.Exponecial Sempre pois a entrada eh sempre Memoryless
        temporizador.registrarTarefaPorAtraso(Random.Exponencial(classe.getLambda()), (tempo) -> InsereClienteNaFila(tempo, classe));
    }

}
