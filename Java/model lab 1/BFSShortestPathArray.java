import java.util.*;

public class BFSShortestPathArray {
    public static void bfs(int[][] graph, int V, int src) {
        boolean[] visited = new boolean[V];
        int[] dist = new int[V];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < V; v++) {
                if (graph[u][v] == 1 && !visited[v]) {
                    visited[v] = true;
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                }
            }
        }

        // Output shortest distances
        System.out.println("\nShortest distances from source " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To " + i + " = " + dist[i]);
        }
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
            graph[v][u] = 1; // Remove for directed graph
        }

        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();

        bfs(graph, V, src);

        sc.close();
    }
}
