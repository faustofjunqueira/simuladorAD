package simulador;

/**
 * Created by Victor on 12.mar.2016.
 */
public class Cliente {
    private Classe classe;
    private double tempoEntrada;
    private double tempoSaida;
    private double trabalhoPendente;

    public Cliente(Classe classe, double tempoEntrada) {
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

    public void setTempoSaida(double tempoSaida) {
        this.tempoSaida = tempoSaida;
    }

    public double getDeltaTempo(){
        return tempoSaida - tempoEntrada;
    }

    public void setTrabalhoPendente(double trabalhoPendente) {
        this.trabalhoPendente = trabalhoPendente;
    }

    public double getTrabalhoPendente() {
        return trabalhoPendente;
    }
}
