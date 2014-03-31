package my_domain.numerical_analysis.direct;

import my_domain.numerical_analysis.matrix.MatrixUtils;

import java.io.FileNotFoundException;
import static java.lang.Math.*;

public class SquareDecomposition {
    public static void main(String[] args) throws FileNotFoundException {
        double a[][] = MatrixUtils.readMatrixAsArray();
        int n = a.length;

        for(int i = 0; i<n; i++)
            for(int j = 0; j<i; j++)
                if(a[i][j] != a[j][i])
                    throw new IllegalStateException("The matrix is not symmetric");

        double u[][] = new double[n][n];
        for(int i = 0; i<n; i++){
            double t = 0;

            for(int k = 0; k<i; k++)
                t += u[k][i] * u[k][i];
            u[i][i] = sqrt(a[i][i] - t);

            for(int j = i + 1; j<n; j++){
                t = 0;
                for(int k = 0; k<i; k++)
                    t += u[k][i] * u[k][j];
                u[i][j] = (a[i][j] - t) / u[i][i];
            }
        }

        double y[] = new double[n];
        for(int i = 0; i<n; i++){
            y[i] = a[i][n];
            for(int j = 0; j<i; j++)
                y[i] -= y[j]*u[j][i];
            y[i] /= u[i][i];
        }

        double x[] = new double[n];
        for(int i = n-1; i>=0; i--){
            x[i] = y[i];
            for(int j = i+1; j<n; j++)
                x[i] -= u[i][j] * x[j];
            x[i] /= u[i][i];
        }

//        for(int i = 0; i<n; i++){
//            for(int j = 0; j<n; j++)
//                System.out.printf("%." + MatrixUtils.PRECISION + "f  ", u[i][j]);
//            System.out.println();
//        }
//        System.out.println();

        for(int i = 0; i<n; i++)
            System.out.printf("%." + MatrixUtils.PRECISION + "f  ", x[i]);
    }
}
