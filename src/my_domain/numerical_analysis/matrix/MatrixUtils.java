package my_domain.numerical_analysis.matrix;

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class MatrixUtils {
    public static final int PRECISION = 8;

    public static double[][] readMatrix() throws FileNotFoundException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(new FileReader("input.txt"));

        int n = sc.nextInt();
        double a[][] = new double[n][n + 1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n + 1; j++)
                a[i][j] = sc.nextDouble();
        sc.close();

        return a;
    }

    public static double[][] inverseMatrix(double[][] a){
        a = a.clone();
        int n = a.length;
        double res[][] = new double[n][n];
        for(int i = 0; i<n; i++)
            res[i][i] = 1.0;

        Logger.log(n, a, res, "");

        for(int i = 0; i<n; i++){
            int maxIndInCol = i;
            for(int j = i; j<n; j++)
                if(abs(a[j][i]) > abs(a[maxIndInCol][i]))
                    maxIndInCol = j;

            int maxIndInRow = i;
            for(int j = i; j<n; j++)
                if(abs(a[i][j]) > abs(a[i][maxIndInRow]))
                    maxIndInRow = j;

//            if(abs(a[maxIndInCol][i]) > abs(a[i][maxIndInRow])){
//                for(int j = 0; j<n; j++){
//                    double t;
//
//                    t = a[i][j];
//                    a[i][j] = a[maxIndInCol][j];
//                    a[maxIndInCol][j] = t;
//
//                    t = res[i][j];
//                    res[i][j] = res[maxIndInCol][j];
//                    res[maxIndInCol][j] = t;
//                }
//                Logger.log(n, a, res, String.format("cols #%d, #%d swapped", i, maxIndInCol));
//            }
//            else{
//                for(int j = 0; j<n; j++){
//                    double t;
//
//                    t = a[j][i];
//                    a[j][i] = a[j][maxIndInRow];
//                    a[j][maxIndInRow] = t;
//
//                    t = res[j][i];
//                    res[j][i] = res[j][maxIndInRow];
//                    res[j][maxIndInRow] = t;
//                }
//                Logger.log(n, a, res, String.format("rows #%d, #%d swapped", i, maxIndInRow));
//            }

            if(abs(a[i][i]) < 1e-6)
                throw new IllegalArgumentException("singular matrix");

            double d = a[i][i];
            for(int j = 0; j<n; j++){
                a[i][j] /= d;
                res[i][j] /= d;
            }
            Logger.log(n, a, res, String.format("row #%d normalized", i));

            for(int j = i + 1; j<n; j++){
                double t = a[j][i];
                for(int k = 0; k<n; k++){
                    a[j][k] -= a[i][k] * t;
                    res[j][k] -= res[i][k] * t;
                }
                Logger.log(n, a, res, String.format("row #%d processed", j));
            }
        }

        for(int i = n-1; i>=0; i--){
            for(int j = 0; j<i; j++){
                double t = a[j][i];
                for(int k = 0; k<n; k++){
                    a[j][k] -= t * a[i][k];
                    res[j][k] -= t * res[i][k];
                }
            }
            Logger.log(n, a, res, String.format("row #%d processed", i));
        }

        return res;
    }
}

class Logger {
    private static final String F_NAME = "output.txt";
    private static int num = 0;

    static{
        try{
            new PrintWriter(new FileWriter(F_NAME)).close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    static void log(int n, double a[][], double res[][], String comment){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(F_NAME, true))){
            pw.printf("\n\nStep #%2d (%s)\n", num++, comment);
            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++)
                    pw.printf("%." + MatrixUtils.PRECISION + "f  ", a[i][j]);
                pw.print("|  ");
                for(int j = 0; j<n; j++)
                    pw.printf("%." + MatrixUtils.PRECISION + "f  ", res[i][j]);
                pw.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}