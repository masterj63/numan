package my_domain.numerical_analysis.direct;

import my_domain.numerical_analysis.matrix.MatrixUtils;

import java.io.FileNotFoundException;
import static java.lang.Math.*;

public class Gauss {
    public static void main(String[] args) throws FileNotFoundException {
        double a[][] = MatrixUtils.readMatrixAsArray();
        int n = a.length;
        for(int i = 0; i<n-1; i++){
            int absMaxInd = i;
            for(int j = i; j<n; j++)
                if(abs(a[j][i]) > abs(a[absMaxInd][i]))
                    absMaxInd = j;
            for(int j = 0; j<n+1; j++){
                double t = a[i][j];
                a[i][j] = a[absMaxInd][j];
                a[absMaxInd][j] = t;
            }
            for(int j = i+1; j<n; j++){
                double d = a[j][i] / a[i][i];
                for(int k = i; k<n+1; k++)
                    a[j][k] -= a[i][k] * d;
            }
        }

        double x[] = new double[n];
        for(int i = n - 1; i >= 0; i--){
            x[i] = a[i][n];
            for(int j = i + 1; j < n; j++)
                x[i] -= x[j] * a[i][j];
            x[i] /= a[i][i];
        }

//        for(int i = 0; i<n; i++){
//            for(int j = 0; j<n+1; j++)
//                System.out.printf("%." + MatrixUtils.PRECISION + "f  ", a[i][j]);
//            System.out.println();
//        }
//        System.out.println();

        for(int i = 0; i<n; i++)
            System.out.printf("%." + MatrixUtils.PRECISION + "f  ", x[i]);
    }
}
