import java.util.*;

public class DijkstraEdgeInput {
    static final int INF = 99999;

    public static void dijkstra(int[][] graph, int V, int src) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        // Initialize distances
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            visited[i] = false;
        }
        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited, V);
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                        dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print shortest distances
        System.out.println("\nShortest distances from source " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("To " + i + " = " + dist[i]);
        }
    }

    public static int minDistance(int[] dist, boolean[] visited, int V) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u][v] = w; // For directed graph
            // graph[v][u] = w; // Uncomment this line if undirected
        }

        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();

        dijkstra(graph, V, src);

        sc.close();
    }
}
