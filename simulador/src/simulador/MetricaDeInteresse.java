package simulador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fausto on 3/12/16.
 */
public class MetricaDeInteresse {
    private List<Cliente> clientesProcessados;
    private Double mediaCalculada = null;
    private Double fracaoDeTempoServidorVazio = 0.;
    private Double fracaoDeChegadasServidorVazio = 0.;

    public MetricaDeInteresse() {
        clientesProcessados = new ArrayList<>();
    }

    public List<Cliente> getClientesProcessados() {
        return clientesProcessados;
    }

    public void setMediaCalculada(Double mediaCalculada) {
        this.mediaCalculada = mediaCalculada;
    }

    public Double getMediaTempoDeEspera() {
        if(mediaCalculada == null){
            List<Double> listaDeltaTempo =
                    getClientesProcessados().
                            stream().
                            map(cliente -> cliente.getDeltaTempo()).
                            collect(Collectors.toList());
            setMediaCalculada(Metricas.Media(listaDeltaTempo));
        }
        return mediaCalculada;
    }

    public void adicionaClienteProcessado(Cliente cliente){
        if(cliente != null){
            getClientesProcessados().add(cliente);
        }else{
            Integer i = 0;
        }
    }

    public Double getFracaoDeTempoServidorVazio() {
        return this.fracaoDeTempoServidorVazio;
    }

    public void setFracaoDeTempoServidorVazio(Double fracaoDeTempoServidorVazio) {
        this.fracaoDeTempoServidorVazio = fracaoDeTempoServidorVazio;
    }

    public Double getFracaoDeChegadasServidorVazio() {
        return fracaoDeChegadasServidorVazio;
    }

    public void setFracaoDeChegadasServidorVazio(Double fracaoDeChegadasServidorVazio) {
        this.fracaoDeChegadasServidorVazio = fracaoDeChegadasServidorVazio;
    }

    public List<Double> getTempoEntreSaidas() {
        List<Double> tempoDeSaidas = new ArrayList<>();
        List<Double> tempoEntreSaidas = new ArrayList<>();
        for (Cliente cliente : clientesProcessados)
            tempoDeSaidas.add(cliente.getTempoSaida());
        tempoDeSaidas.sort(Comparator.<Double>naturalOrder());
        for (int i = 0; i < tempoDeSaidas.size() - 1; i++) {
            tempoEntreSaidas.add(tempoDeSaidas.get(i + 1) - tempoDeSaidas.get(i));
        }
        return tempoEntreSaidas;
    }
}
