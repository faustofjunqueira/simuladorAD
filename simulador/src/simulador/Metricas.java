package simulador;

import java.util.List;

/**
 * Created by Victor on 11.mar.2016.
 */
public class Metricas {

    public static double Little(double lambda, double mediaTempo) {
        return lambda * mediaTempo;
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

    public static double IntervaloConfiancaInferior(List<Double> lista) {
        return Media(lista) - 1.96 * Math.sqrt(DesvioPadrao(lista)) / Math.sqrt(lista.size());
    }

    public static double IntervaloConfiancaSuperior(List<Double> lista) {
        return Media(lista) + 1.96 * Math.sqrt(DesvioPadrao(lista))/Math.sqrt(lista.size());
    }
}
