package com.company;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class Integral {
    public static double integral_sredn_pr9m(double a, double b, String integral){
        double s, d, xb, xe, x;
        s = 0;
        d =(b-a)/100;
        xb = a;
        for (int i = 0; i < 100; i++) {
            xe = xb + d;
            x = (xb + xe)/2;
            Argument arg = new Argument("x = " + x);
            Expression e = new Expression(integral, arg);
            s = s + d * e.calculate();
            xb = xe;
        }
        return s;
    }
}