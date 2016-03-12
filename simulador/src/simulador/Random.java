package simulador;

/**
 * Created by Victor on 11.mar.2016.
 */
public class Random {
    public static double Exponencial(double lambda) {
        return Math.log(1-Math.random())/(-lambda);
    }

    public static double Deterministico(double lambda) {
        return 1/lambda;
    }

    public static double Uniforme(double a, double b) {
        return Math.random() * (b - a) + a;
    }
}
