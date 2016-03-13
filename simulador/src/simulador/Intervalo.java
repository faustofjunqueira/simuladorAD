package simulador;

/**
 * Created by fausto on 3/13/16.
 */
public class Intervalo {
    public Double entrada;
    public Double saida;
    public Double delta(){
        return saida - entrada;
    }
}
