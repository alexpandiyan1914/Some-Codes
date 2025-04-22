import java.util.*;

public class BFSConnectedComponents {

    // BFS to visit all nodes in a component
    public static void bfs(int[][] graph, int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        System.out.print("Component: ");
        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int i = 0; i < graph.length; i++) {
                if (graph[node][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u][v] = 1;
            graph[v][u] = 1; // undirected
        }

        boolean[] visited = new boolean[V];
        int componentCount = 0;

        System.out.println("\nConnected Components:");
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                bfs(graph, i, visited);
                componentCount++;
            }
        }

        System.out.println("\nTotal connected components = " + componentCount);
        sc.close();
    }
}
