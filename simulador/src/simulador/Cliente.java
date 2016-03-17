package simulador;

/**
 * Created by Victor on 12.mar.2016.
 */
public class Cliente {
    private Classe classe;
    private double tempoEntrada;
    private double tempoSaida;
    protected double tempoDeServico;
    protected double trabalhoPendente;

    public Cliente(Classe classe, double tempoEntrada) {
        this.classe = classe;
        this.tempoEntrada = tempoEntrada;
        this.tempoSaida = tempoSaida;
        this.tempoDeServico = this.classe.getRandom();
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

    protected double getTrabalhoPendente() {
        return trabalhoPendente;
    }

    public double getTempoDeServico() {
        return tempoDeServico;
    }
}
