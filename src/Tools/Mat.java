package Tools;

/**
 * Mathematical tools
 */
public class Mat {
    public static double max(double[] array) {
        double out = Double.NEGATIVE_INFINITY;

        for (double v : array) out = Math.max(v, out);
        return out;
    }

    public static double min(double[] array) {
        double out = Double.POSITIVE_INFINITY;
        for (double v : array) out = Math.min(v, out);
        return out;
    }

    public static double mean(double[] array) {
        int N = array.length;
        double avg = 0;

        for (double v : array) avg += v / N;
        return avg;
    }

    public static double std(double[] array) {
        int N = array.length;
        double mean = mean(array);

        return sum_x2(array) / N - (mean * mean);
    }

    public static double sum_x2(double[] array) {
        double sum = 0;

        for (double v : array) sum += (v * v);
        return sum;
    }
}
