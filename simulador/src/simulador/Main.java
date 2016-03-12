package simulador;

/**
 * Created by fausto on 3/11/16.
 */
public class Main {

    public static void main(String [ ] args)
    {
        Classe classe = new Classe(0.05,1,0,() -> Random.Exponencial(1));

        Simulador simulador = new Simulador(1000., classe);
        simulador.iniciarSimulacao();
    }
}
