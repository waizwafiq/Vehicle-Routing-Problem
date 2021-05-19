package Tools;

public class Normalizer {
    public static double[] decScale(double[] S, double maxRange) {
        double[] out = new double[S.length];
        int j = String.valueOf(Mat.max(S)).length();

        for (int i = 0; i < S.length; i++)
            out[i] = (S[i] / Math.pow(10, j)) * maxRange;

        return out;
    }

    public static double[] minMax(double[] S, double maxRange) {
        double[] out = new double[S.length];
        double max_o = Mat.max(S), min_o = Mat.min(S);

        for (int i = 0; i < S.length; i++)
            out[i] = ((S[i] - min_o) / (max_o - min_o)) * maxRange;
        return out;
    }

    public static double[] Z_scale(double[] S, double maxRange) {
        double[] out = new double[S.length];
        double mean = Mat.mean(S), std = Mat.std(S);
        for (int i = 0; i < S.length; i++)
            out[i] = ((S[i] - mean) / std) * maxRange;

        return out;
    }
/*
//TESTER
    public static void main(String[] args) {
        double[] x = {3, 42, 102, 51, 12, 21, 58};
        System.out.print("Original : || ");
        for (double v : x)
            System.out.print(v + " || ");

        System.out.print("\ndecScale() -> || ");
        for (double v : decScale(x, 50000))
            System.out.print(v + " || ");

        System.out.print("\nminMax() -> || ");
        for (double v : minMax(x, 15))
            System.out.print(v + " || ");

        System.out.print("\nZ_scale() -> || ");
        for (double v : Z_scale(x, 500))
            System.out.print(v + " || ");
    }*/
}
