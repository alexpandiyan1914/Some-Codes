import java.util.*;

public class BFSCycleDetection {
    public static boolean isCyclic(int[][] graph, int V) {
        boolean[] visited = new boolean[V];
        int[] parent = new int[V]; // To keep track of parent nodes

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (bfsCheckCycle(graph, i, visited, parent)) {
                    return true; // Cycle found
                }
            }
        }

        return false; // No cycle found
    }

    public static boolean bfsCheckCycle(int[][] graph, int start, boolean[] visited, int[] parent) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        parent[start] = -1;
        queue.add(start);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < graph.length; v++) {
                if (graph[u][v] == 1) {
                    if (!visited[v]) {
                        visited[v] = true;
                        parent[v] = u;
                        queue.add(v);
                    } else if (parent[u] != v) {
                        // If already visited and not parent → cycle found
                        return true;
                    }
                }
            }
        }

        return false;
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
            graph[v][u] = 1; // Undirected graph
        }

        if (isCyclic(graph, V)) {
            System.out.println("\nCycle detected in the graph.");
        } else {
            System.out.println("\nNo cycle in the graph.");
        }

        sc.close();
    }
}
