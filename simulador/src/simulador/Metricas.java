package simulador;

/**
 * Created by Victor on 11.mar.2016.
 */
public class Metricas {
    public static double Little(double lambda, double mi) {
        double rho = lambda/mi;
        return rho/(1-rho);
    }
}
