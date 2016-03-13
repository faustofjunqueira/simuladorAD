package simulador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fausto on 3/13/16.
 */
public class ClientePreemptivo extends Cliente{

    private List<Intervalo> tempoNaFila;
    private Double tempoPendente;
    public ClientePreemptivo(Classe classe, double tempoEntrada) {
        super(classe, tempoEntrada);
        tempoNaFila = new ArrayList<>();
        marcaEntrada(tempoEntrada);
        tempoPendente = classe.getRandom();
    }

    public Double getTempoPendente() {
        return tempoPendente;
    }

    public void setTempoPendente(Double tempoPendente) {
        this.tempoPendente = tempoPendente;
    }

    public void marcaEntrada(double tempoEntrada) {
        Intervalo intervalo = new Intervalo();
        intervalo.entrada = tempoEntrada;
        tempoNaFila.add(intervalo);
    }

    public void marcaSaida(double tempoSaida) {
        tempoNaFila.get(tempoNaFila.size() - 1).saida = tempoSaida;
    }

    @Override
    public double getDeltaTempo(){
        return tempoNaFila.stream().mapToDouble(a -> a.delta()).sum();
    }

}
