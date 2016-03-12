package simulador;

/**
 * Created by fausto on 3/11/16.
 */
public class Main {

    public static void main(String [ ] args)
    {
        Classe classe = new Classe(0.15,1,0,() -> Random.Exponencial(1));

        Simulador simulador = new Simulador(100000., classe);
        MetricaDeInteresse m = simulador.iniciarSimulacao();
        System.out.print("media de tempo: ");
        System.out.print(m.getMediaTempoDeEspera());
        System.out.print(" little: ");
        System.out.print(Metricas.Little(classe.getLambda(), m.getMediaTempoDeEspera()));
    }
}
