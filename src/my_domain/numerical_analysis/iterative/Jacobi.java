package my_domain.numerical_analysis.iterative;

import my_domain.numerical_analysis.matrix.MatrixUtils;

import java.io.FileNotFoundException;

public class Jacobi {
    public static void main(String[] args) throws FileNotFoundException {
        double a[][] = MatrixUtils.readMatrixAsArray();
        int n = a.length;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++)
                System.out.printf("%." + MatrixUtils.PRECISION + "f  ", a[i][j]);
            System.out.println();
        }

        double inv[][] = MatrixUtils.inverseMatrix(a);

        System.out.println(); System.out.println();

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                System.out.printf("%." + MatrixUtils.PRECISION + "f  ", inv[i][j]);
            }
            System.out.println();
        }
    }
}

