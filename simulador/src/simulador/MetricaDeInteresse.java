package simulador;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fausto on 3/12/16.
 */
public class MetricaDeInteresse {
    private List<Cliente> clientesProcessados;
    private Double mediaCalculada = null;

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
        getClientesProcessados().add(cliente);
    }

}
