package my_domain.numerical_analysis.matrix;

public class RealMatrix {
    private final int ROWS, COLS;
    private double a[][];

    public RealMatrix(int n, int m){
        a = new double[n][m];
        ROWS = n;
        COLS = m;
    }

    public RealMatrix(double a[][]){
        int n = a.length;
        if(a.length == 0)
            throw new IllegalArgumentException("An array of dimension of 0");
        int m = a[0].length;
        for(double t[] : a)
            if(t.length != m)
                throw new IllegalArgumentException("Not a rectangle array");
        this.a = a.clone();
        ROWS = n;
        COLS = m;
    }

    public RealMatrix multiply(RealMatrix mat){
        if(this.ROWS != mat.ROWS)
            throw new IllegalArgumentException(
                    String.format(
                            "Dimension mismatch: (%d x %d) x (%d x %d)",
                            this.ROWS, this.COLS, mat.ROWS, mat.COLS
                    )
            );

        int n = this.COLS;
        int m = mat.ROWS;
        RealMatrix res = new RealMatrix(n, m);

        for(int i = 0; i<n; i++)
            for(int j = 0; j<m; j++){

            }

        return res;
    }
}
