package my_domain.numerical_analysis.direct;

import my_domain.numerical_analysis.matrix.MatrixUtils;

import java.io.FileNotFoundException;

public class Tridiagonal {
    public static void main(String[] args) throws FileNotFoundException {
        double a[][] = MatrixUtils.readMatrixAsArray();
        int n = a.length;

        for(int i = 0; i<n; i++){
            for(int j = i+2; j<n; j++)
                if(a[i][j] != 0 || a[j][i] != 0)
                    throw new IllegalStateException("The matrix is not tridiagonal");
        }

        double alpha[] = new double[n];
        double beta[] = new double[n];

        alpha[1] = -a[0][1] / a[0][0];
        beta[1] = a[0][n] / a[0][0];
        for(int i = 1; i<n-1; i++){
            alpha[i+1] = - a[i][i+1] / (a[i][i-1] * alpha[i] + a[i][i]);
            beta[i+1] = (a[i][n] - a[i][i-1]*beta[i]) / (a[i][i-1]*alpha[i] + a[i][i]);
        }

        double x[] = new double[n];
        x[n-1] = (a[n-1][n] - a[n-1][n-2]*beta[n-1]) / (a[n-1][n-1] + a[n-1][n-2]*alpha[n-1]);
        for(int i = n-2; i>=0; i--)
            x[i] = alpha[i+1]*x[i+1] + beta[i+1];

        for(int i = 0; i<n; i++)
            System.out.printf("%." + MatrixUtils.PRECISION + "f  ", x[i]);
    }
}
