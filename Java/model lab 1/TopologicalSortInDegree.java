import java.util.*;

public class TopologicalSortInDegree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Take input
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        // Adjacency List
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());

        int[] inDegree = new int[V];

        System.out.println("Enter edges (u v) where u -> v:");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            inDegree[v]++;
        }

        // Step 2: Add all vertices with in-degree 0 to queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0)
                q.add(i);
        }

        // Step 3: Perform BFS (Kahn's Algorithm)
        List<Integer> topoOrder = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            topoOrder.add(node);

            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0)
                    q.add(neighbor);
            }
        }

        // Step 4: Print result
        if (topoOrder.size() == V) {
            System.out.println("Topological Order:");
            for (int node : topoOrder)
                System.out.print(node + " ");
        } else {
            System.out.println("Graph has a cycle! Topological sorting not possible.");
        }

        sc.close();
    }
}
