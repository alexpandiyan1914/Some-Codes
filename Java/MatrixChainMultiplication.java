import java.util.Scanner;

class MatrixChainMultiplication {
static void printTable(int[][] dp) {
System.out.println("\nDP Table:");
for (int[] row : dp) {
for (int val : row) {
System.out.printf("%10d ", val);
}
System.out.println();
}
}

static int matrixChainOrder(int[] p, int n) {
int[][] dp = new int[n][n];

for (int len = 2; len < n; len++) {
for (int i = 1; i < n - len + 1; i++) {
int j = i + len - 1;
dp[i][j] = Integer.MAX_VALUE;
for (int k = i; k < j; k++) {
int cost = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
if (cost < dp[i][j]) {
dp[i][j] = cost;
}
}
}

}

printTable(dp);
return dp[1][n - 1];
}

public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter the number of matrices: ");
int n = sc.nextInt();

int[] arr = new int[n + 1];
System.out.println("Enter the dimensions (row, col) of each matrix:");

System.out.print("Matrix 1 row size: ");
arr[0] = sc.nextInt(); // First matrix row size

for (int i = 1; i < n; i++) {
System.out.print("Matrix " + i + " column size / Matrix " + (i + 1) + " row size: ");
arr[i] = sc.nextInt();
}

System.out.print("Matrix " + n + " column size: ");
arr[n] = sc.nextInt(); // Last matrix column size

int minMultiplications = matrixChainOrder(arr, n + 1);
System.out.println("\nMinimum number of multiplications: " + minMultiplications);

sc.close();
}
}
