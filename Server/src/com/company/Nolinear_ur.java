package com.company;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class Nolinear_ur {

    private static final int NESHOD = 2000;
    private static int iteration;

    static double solve(String function, double x0, double e) {
        iteration = 0;
        double x1  = x0 - f(function, x0)/f1(function, x0); // первое приближение
        while (java.lang.Math.abs(x1-x0)>e && iteration < NESHOD) { // пока не достигнута точность 0.000001
            x0 = x1;
            x1 = x0 - f(function, x0)/f1(function, x0); // последующие приближения
            iteration++;
        }
        return x1;
    }

    public static double f (String function, double x){
        Argument arg = new Argument("x = " + x);
        Expression e = new Expression(function, arg);
        return e.calculate();
    }

    public static double f1 (String function, double x){
        String line = function + ", " + "x, " + x;
        Expression e = new Expression("der("+line+")");
        return e.calculate();
    }

    public static int getConst(){
        return NESHOD;
    }

    public static int getIteration(){
        return iteration;
    }
}
