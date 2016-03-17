package simulador;

/**
 * Created by fausto on 3/16/16.
 */
public class SimuladorRejuvenecimento {
    protected Double rho;
    protected Double lambda;
    protected Temporizador temporizador;
    protected Integer nRejuvenecimentosAteFalhar = 0;
    protected Double horarioUltimaFalha = 0.0;

    protected Tarefa rejuvenecimentoTarefa, falhaTarefa;

    protected MetricaRejuvenecimento metricas = new MetricaRejuvenecimento();

    public SimuladorRejuvenecimento(Double tempoFinal, Double rho, Double lambda){
        this.rho = rho;
        this.lambda = lambda;
        temporizador = new Temporizador(tempoFinal);
    }

    private void preparaSimulador(){
        criaTarefas();
    }

    private void criaTarefas(){
        rejuvenecimentoTarefa = temporizador.registrarTarefaPorAtraso(Random.Exponencial(rho), (tempo) -> Rejuveneceu(tempo));
        falhaTarefa = temporizador.registrarTarefaPorAtraso(Random.Exponencial(lambda), (tempo) -> Falhou(tempo));
    }

    private void Falhou(Double horario){
        temporizador.cancelarTarefa(rejuvenecimentoTarefa);
        metricas.incrementaNFalhas();
        metricas.incrementaIteracoes();
        metricas.adicionaNRejuvenecimentosAteFalhar(nRejuvenecimentosAteFalhar+1); // +1 é a vez que falha!
        metricas.adicionaTempoEntreFalhas(horario - horarioUltimaFalha);
        horarioUltimaFalha = horario;
        nRejuvenecimentosAteFalhar = 0;
        criaTarefas();
    }

    private void Rejuveneceu(Double horario){
        temporizador.cancelarTarefa(falhaTarefa);
        metricas.incrementaIteracoes();
        nRejuvenecimentosAteFalhar++;
        criaTarefas();
    }

    public MetricaRejuvenecimento executar(){
        preparaSimulador();
        temporizador.play();
        return metricas;
    }

}
