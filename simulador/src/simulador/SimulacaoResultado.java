package simulador;

/**
 * Created by fausto on 3/14/16.
 */
public class SimulacaoResultado {
    public Double media;
    public Double intConfInferior;
    public Double intConfSuperior;

    public SimulacaoResultado(Double media, Double intConfInferior, Double intConfSuperior) {
        this.media = media;
        this.intConfInferior = intConfInferior;
        this.intConfSuperior = intConfSuperior;
    }
}
