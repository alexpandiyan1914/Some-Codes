import java.util.Scanner;

public class MatrixChainMultiplication {

    public static int matrixChainOrder(int[] arr, int n) {
        int[][] dp = new int[n][n];

        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }
        return dp[1][n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of test cases: ");
        int testCases = sc.nextInt();

        for (int t = 1; t <= testCases; t++) {
            System.out.println("\nTest case " + t + ":");
            System.out.print("Enter number of elements in the array (number of matrices + 1): ");
            int n = sc.nextInt();

            int[] arr = new int[n];
            System.out.println("Enter the dimensions array (example: for 3 matrices A:10x30, B:30x5, C:5x60, enter: 10 30 5 60):");
            for (int i = 0; i < n; i++) {
                System.out.print("arr[" + i + "]: ");
                arr[i] = sc.nextInt();
            }

            int result = matrixChainOrder(arr, n);
            System.out.println("Minimum number of multiplications needed: " + result);
        }

        sc.close();
    }
}
