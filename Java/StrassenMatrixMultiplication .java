import java.util.Scanner;

public class StrassenMatrixMultiplication {
    
    // Function to multiply two matrices using Strassen's Algorithm
    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];

        // Base case: if matrix is 1x1, just multiply elements
        if (n == 1) {
            result[0][0] = A[0][0] * B[0][0];
            return result;
        }

        // Splitting matrices into quadrants
        int newSize = n / 2;
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];

        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        // Dividing the matrices into sub-matrices
        splitMatrix(A, A11, 0, 0);
        splitMatrix(A, A12, 0, newSize);
        splitMatrix(A, A21, newSize, 0);
        splitMatrix(A, A22, newSize, newSize);

        splitMatrix(B, B11, 0, 0);
        splitMatrix(B, B12, 0, newSize);
        splitMatrix(B, B21, newSize, 0);
        splitMatrix(B, B22, newSize, newSize);

        // Strassen's 7 Multiplication steps
        int[][] M1 = strassenMultiply(addMatrices(A11, A22), addMatrices(B11, B22));
        int[][] M2 = strassenMultiply(addMatrices(A21, A22), B11);
        int[][] M3 = strassenMultiply(A11, subtractMatrices(B12, B22));
        int[][] M4 = strassenMultiply(A22, subtractMatrices(B21, B11));
        int[][] M5 = strassenMultiply(addMatrices(A11, A12), B22);
        int[][] M6 = strassenMultiply(subtractMatrices(A21, A11), addMatrices(B11, B12));
        int[][] M7 = strassenMultiply(subtractMatrices(A12, A22), addMatrices(B21, B22));

        // Computing result quadrants
        int[][] C11 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
        int[][] C12 = addMatrices(M3, M5);
        int[][] C21 = addMatrices(M2, M4);
        int[][] C22 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);

        // Combining quadrants into final result matrix
        joinMatrix(C11, result, 0, 0);
        joinMatrix(C12, result, 0, newSize);
        joinMatrix(C21, result, newSize, 0);
        joinMatrix(C22, result, newSize, newSize);

        return result;
    }

    // Function to add two matrices
    private static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] + B[i][j];
        return result;
    }

    // Function to subtract two matrices
    private static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] - B[i][j];
        return result;
    }

    // Function to split a matrix into sub-matrices
    private static void splitMatrix(int[][] parent, int[][] child, int rowStart, int colStart) {
        int n = child.length;
        for (int i = 0, r = rowStart; i < n; i++, r++)
            for (int j = 0, c = colStart; j < n; j++, c++)
                child[i][j] = parent[r][c];
    }

    // Function to join sub-matrices into a parent matrix
    private static void joinMatrix(int[][] child, int[][] parent, int rowStart, int colStart) {
        int n = child.length;
        for (int i = 0, r = rowStart; i < n; i++, r++)
            for (int j = 0, c = colStart; j < n; j++, c++)
                parent[r][c] = child[i][j];
    }

    // Function to print a matrix
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row)
                System.out.print(num + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter matrix size (must be power of 2): ");
        int n = scanner.nextInt();

        int[][] A = new int[n][n];
        int[][] B = new int[n][n];

        System.out.println("Enter elements of matrix A:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = scanner.nextInt();

        System.out.println("Enter elements of matrix B:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                B[i][j] = scanner.nextInt();

        int[][] result = strassenMultiply(A, B);

        System.out.println("Resultant Matrix:");
        printMatrix(result);
        
        scanner.close();
    }
}
