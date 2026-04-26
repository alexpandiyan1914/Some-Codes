//Chandy Misra Haas AND 

import java.util.*;

public class CMH_AND {
    static int n;
    static List<Integer>[] graph;
    static boolean[] visited;
    static boolean deadlock = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.print("Enter number of edges (dependencies): ");
        int e = sc.nextInt();

        System.out.println("Enter edges (i -> j means Pi waits for Pj):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
        }

        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false);
            detect(i, i);
        }

        if (deadlock)
            System.out.println("Deadlock detected (cycle found)");
        else
            System.out.println("No deadlock");
    }

    static void detect(int initiator, int current) {
        visited[current] = true;

        for (int neighbor : graph[current]) {
            if (neighbor == initiator) {
                deadlock = true;
                System.out.println("Cycle involves process " + initiator);
                return;
            }
            if (!visited[neighbor]) {
                detect(initiator, neighbor);
            }
        }
    }
}
