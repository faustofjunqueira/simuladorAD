package simulador;

import java.util.List;

/**
 * Created by Victor on 11.mar.2016.
 */
public class Metricas {

    public static double Little(double lambda, double mi) {
        double rho = lambda/mi;
        return rho/(1-rho);
    }

    public static double Media(List<Double> lista) {
        return lista.stream().mapToDouble(a->a).average().getAsDouble();
    }

    public static double DesvioPadrao(List<Double> lista) {
        double desvioPadrao = 0;
        double media = Media(lista);
        for (Double item : lista) {
            desvioPadrao += (item - media)*(item - media) / (lista.size() - 1);
        }
        return desvioPadrao;
    }
}
