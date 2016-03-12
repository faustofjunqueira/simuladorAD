package simulador;

/**
 * Created by Victor on 12.mar.2016.
 */
public class Cliente {
    private Classe classe;
    private double tempoEntrada;
    private double tempoSaida;

    public Cliente(Classe classe, double tempoEntrada, double tempoSaida) {
        this.classe = classe;
        this.tempoEntrada = tempoEntrada;
        this.tempoSaida = tempoSaida;
    }

    public Classe getClasse() {
        return this.classe;
    }

    public double getTempoEntrada() {
        return this.tempoEntrada;
    }

    public double getTempoSaida() {
        return this.tempoSaida;
    }

}
