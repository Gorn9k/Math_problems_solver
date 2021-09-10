package com.company;

public class Kramer {

    public static double detRec(double[][] A)
    {
        int n = A.length;
        if(n == 1) return A[0][0];
        double ans = 0;
        double B[][] = new double[n-1][n-1];
        int l = 1;
        for(int i = 0; i < n; ++i){

            int x = 0, y = 0;
            for(int j = 1; j < n; ++j){
                for(int k = 0; k < n; ++k){
                    if(i == k) continue;
                    B[x][y] = A[j][k];
                    ++y;
                    if(y == n - 1){
                        y = 0;
                        ++x;
                    }
                }
            }
            ans += l * A[0][i] * detRec(B);
            l *= (-1);
        }
        return ans;
    }

    public static double[] kramer(double[][] A, double[] B) {
        double[] el = new double[A.length];
        double det;
        double[] dets = new double[A.length];
        det = detRec(A);
        for (int j = 0; j < A.length; j++)
        {
            for (int i = 0; i < A.length; i++)
            {
                el[i] = A[i][j];
                A[i][j] = B[i];
            }
            dets[j] = detRec(A);
            for (int i = 0; i < A.length; i++) {
                A[i][j] = el[i];
            }
        }
        double[] results = new double[A.length];
        for (int i = 0; i < A.length; i++)
        {
            results[i] = dets[i] / det;
        }
        return results;
    }
}
