import java.util.Random;

public class MatrixMultiplication {

    static final int THRESHOLD = 128; // You can tweak this

    // Naive O(n^3) multiplication
    public static int[][] naiveMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }

    // Add two matrices
    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    // Subtract two matrices
    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    // Split matrix into quarters
    public static void split(int[][] P, int[][] C, int row, int col) {
        int n = C.length;
        for (int i = 0; i < n; i++)
            System.arraycopy(P[i + row], col, C[i], 0, n);
    }

    // Join quarters into matrix
    public static void join(int[][] C, int[][] P, int row, int col) {
        int n = C.length;
        for (int i = 0; i < n; i++)
            System.arraycopy(C[i], 0, P[i + row], col, n);
    }

    // DAC Multiplication (recursive divide)
    public static int[][] dacMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            int newSize = n / 2;
            int[][] a11 = new int[newSize][newSize];
            int[][] a12 = new int[newSize][newSize];
            int[][] a21 = new int[newSize][newSize];
            int[][] a22 = new int[newSize][newSize];
            int[][] b11 = new int[newSize][newSize];
            int[][] b12 = new int[newSize][newSize];
            int[][] b21 = new int[newSize][newSize];
            int[][] b22 = new int[newSize][newSize];

            split(A, a11, 0, 0);
            split(A, a12, 0, newSize);
            split(A, a21, newSize, 0);
            split(A, a22, newSize, newSize);
            split(B, b11, 0, 0);
            split(B, b12, 0, newSize);
            split(B, b21, newSize, 0);
            split(B, b22, newSize, newSize);

            int[][] c11 = add(dacMultiply(a11, b11), dacMultiply(a12, b21));
            int[][] c12 = add(dacMultiply(a11, b12), dacMultiply(a12, b22));
            int[][] c21 = add(dacMultiply(a21, b11), dacMultiply(a22, b21));
            int[][] c22 = add(dacMultiply(a21, b12), dacMultiply(a22, b22));

            join(c11, C, 0, 0);
            join(c12, C, 0, newSize);
            join(c21, C, newSize, 0);
            join(c22, C, newSize, newSize);
        }
        return C;
    }

    // Strassen's Algorithm with Hybrid Threshold
    public static int[][] strassen(int[][] A, int[][] B) {
        int n = A.length;
        if (n <= THRESHOLD) {
            return naiveMultiply(A, B);
        }

        int newSize = n / 2;
        int[][] a11 = new int[newSize][newSize];
        int[][] a12 = new int[newSize][newSize];
        int[][] a21 = new int[newSize][newSize];
        int[][] a22 = new int[newSize][newSize];
        int[][] b11 = new int[newSize][newSize];
        int[][] b12 = new int[newSize][newSize];
        int[][] b21 = new int[newSize][newSize];
        int[][] b22 = new int[newSize][newSize];

        split(A, a11, 0, 0);
        split(A, a12, 0, newSize);
        split(A, a21, newSize, 0);
        split(A, a22, newSize, newSize);
        split(B, b11, 0, 0);
        split(B, b12, 0, newSize);
        split(B, b21, newSize, 0);
        split(B, b22, newSize, newSize);

        int[][] M1 = strassen(add(a11, a22), add(b11, b22));
        int[][] M2 = strassen(add(a21, a22), b11);
        int[][] M3 = strassen(a11, subtract(b12, b22));
        int[][] M4 = strassen(a22, subtract(b21, b11));
        int[][] M5 = strassen(add(a11, a12), b22);
        int[][] M6 = strassen(subtract(a21, a11), add(b11, b12));
        int[][] M7 = strassen(subtract(a12, a22), add(b21, b22));

        int[][] c11 = add(subtract(add(M1, M4), M5), M7);
        int[][] c12 = add(M3, M5);
        int[][] c21 = add(M2, M4);
        int[][] c22 = add(subtract(add(M1, M3), M2), M6);

        int[][] C = new int[n][n];
        join(c11, C, 0, 0);
        join(c12, C, 0, newSize);
        join(c21, C, newSize, 0);
        join(c22, C, newSize, newSize);

        return C;
    }

    // Generate random matrix of size n x n
    public static int[][] generateMatrix(int n) {
        Random rand = new Random();
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                mat[i][j] = rand.nextInt(10); // range 0-9
        return mat;
    }

    public static void main(String[] args) {
        int n = 256; // Matrix size, must be power of 2

        int[][] A = generateMatrix(n);
        int[][] B = generateMatrix(n);

        long start, end;

        start = System.currentTimeMillis();
        int[][] naiveResult = naiveMultiply(A, B);
        end = System.currentTimeMillis();
        System.out.println("Naive time: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        int[][] dacResult = dacMultiply(A, B);
        end = System.currentTimeMillis();
        System.out.println("DAC time: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        int[][] strassenResult = strassen(A, B);
        end = System.currentTimeMillis();
        System.out.println("Strassen time: " + (end - start) + " ms");

        // Optional: Verify correctness
        // System.out.println(Arrays.deepEquals(naiveResult, strassenResult));
    }
}
