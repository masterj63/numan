import my_domain.numerical_analysis.matrix.RealMatrix;

public class Main {
    public static void main(String[] args) {
        RealMatrix m1 = new RealMatrix(new double[][]{{1,2},{3,4},{5,6}});
        RealMatrix m2 = new RealMatrix(new double[][]{{1,4,1},{3,2,6}});
        RealMatrix m3 = m1.multiply(m2);
        for(int i = 0; i<m3.ROWS; i++){
            for(int j = 0; j<m3.COLS; j++)
                System.out.printf("%.2f ", m3.getA(i, j));
            System.out.println();
        }
    }
}
