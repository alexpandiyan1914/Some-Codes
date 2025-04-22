import java.util.Scanner;

public class PrimsAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];
        System.out.println("Enter adjacency matrix (0 if no edge):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();

        boolean[] visited = new boolean[n];
        visited[0] = true;
        int edges = 0, minCost = 0;

        while (edges < n - 1) {
            int min = Integer.MAX_VALUE;
            int a = -1, b = -1;

            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    for (int j = 0; j < n; j++) {
                        if (!visited[j] && cost[i][j] != 0 && cost[i][j] < min) {
                            min = cost[i][j];
                            a = i;
                            b = j;
                        }
                    }
                }
            }

            if (a != -1 && b != -1) {
                visited[b] = true;
                minCost += cost[a][b];
                edges++;
                System.out.println("Edge: " + (a + 1) + " - " + (b + 1) + " Cost: " + cost[a][b]);
            }
        }

        System.out.println("Total Minimum Cost: " + minCost);
        sc.close();
    }
}
