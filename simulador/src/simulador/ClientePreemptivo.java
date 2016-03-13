package simulador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fausto on 3/13/16.
 */
public class ClientePreemptivo extends Cliente{
    private static int AUTOINC = 0;
    private int id;
    private List<Intervalo> tempoNaFila;
    private Double tempoPendente;
    public ClientePreemptivo(Classe classe, double tempoEntrada) {
        super(classe, tempoEntrada);
        id = AUTOINC++;
        tempoNaFila = new ArrayList<>();
        marcaEntrada(tempoEntrada);
        tempoPendente = classe.getRandom();
    }

    public Double getTempoPendente() {
        return tempoPendente;
    }

    public void marcaEntrada(double tempoEntrada) {
        Intervalo intervalo = new Intervalo();
        intervalo.entrada = tempoEntrada;
        tempoNaFila.add(intervalo);
    }

    public void marcaSaida(double tempoSaida) {
        tempoNaFila.get(tempoNaFila.size() - 1).saida = tempoSaida;
    }

    public void atualizaTempoPendente(Double horario){
        //Ultima saida, foi quando começou o processamento
        tempoPendente -= horario - tempoNaFila.get(tempoNaFila.size()-1).saida;
    }

    @Override
    public double getDeltaTempo(){
        double delta = .0;
        for (Intervalo intervalo : tempoNaFila) {
            if(intervalo != null) {
                delta += intervalo.delta();
            }
        }
        return delta;
    }

    @Override
    public int hashCode(){
        return id;
    }

}
