package com.company;

public class Cub_ur {
    public static double[] solveCubic(double d, double a, double b, double c) {
        double[] result;
        if (d != 1) {
            a = a / d;
            b = b / d;
            c = c / d;
        }
        double p = b / 3 - a * a / 9;
        double q = a * a * a / 27 - a * b / 6 + c / 2;
        double D = p * p * p + q * q;
        if (Double.compare(D, 0) >= 0) {
            if (Double.compare(D, 0) == 0) {
                double r = java.lang.Math.cbrt(-q);
                result = new double[2];
                result[0] = 2 * r;
                result[1] = -r;
            } else {
                double r = java.lang.Math.cbrt(-q + java.lang.Math.sqrt(D));
                double s = java.lang.Math.cbrt(-q - java.lang.Math.sqrt(D));
                result = new double[1];
                result[0] = r + s;
            }
        } else {
            double ang = java.lang.Math.acos(-q / java.lang.Math.sqrt(-p * p * p));
            double r = 2 * java.lang.Math.sqrt(-p);
            result = new double[3];
            for (int k = -1; k <= 1; k++) {
                double theta = (ang - 2 * java.lang.Math.PI * k) / 3;
                result[k + 1] = r * java.lang.Math.cos(theta);
            }

        }
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i] - a / 3;
        }
        return result;
    }
}
