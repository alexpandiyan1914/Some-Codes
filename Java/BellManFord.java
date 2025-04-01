import java.util.Arrays;
import java.util.Scanner;
class BellmanFord {
static class Edge {
int src, dest, weight;
Edge(int src, int dest, int weight) {
this.src = src;
this.dest = dest;
this.weight = weight;
}
}

static void printDistanceTable(int[] dist, int step) {
System.out.println("\nStep " + step + ": Distance Table");
for (int i = 0; i < dist.length; i++) {
System.out.printf("%10d ", (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
}
System.out.println();
}
static void bellmanFord(Edge[] edges, int V, int E, int src) {
int[] dist = new int[V];
Arrays.fill(dist, Integer.MAX_VALUE);
dist[src] = 0;
for (int i = 0; i < V - 1; i++) {
for (Edge edge : edges) {
if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight <
dist[edge.dest]) {
dist[edge.dest] = dist[edge.src] + edge.weight;
}
}
printDistanceTable(dist, i + 1);
}
// Check for negative-weight cycles
for (Edge edge : edges) {
if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight <
dist[edge.dest]) {
System.out.println("\nGraph contains negative weight cycle");
return;
}
}

System.out.println("\nFinal Shortest Distances from Source:");
for (int i = 0; i < V; i++) {
System.out.println("Vertex " + i + " -> " + (dist[i] == Integer.MAX_VALUE ? "INF" :
dist[i]));
}
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter the number of vertices: ");
int V = sc.nextInt();
System.out.print("Enter the number of edges: ");

int E = sc.nextInt();

Edge[] edges = new Edge[E];
System.out.println("Enter the edges (source, destination, weight):");
for (int i = 0; i < E; i++) {
System.out.print("Edge " + (i + 1) + ": ");
int src = sc.nextInt();
int dest = sc.nextInt();
int weight = sc.nextInt();
edges[i] = new Edge(src, dest, weight);
}
System.out.print("Enter the source vertex: ");
int src = sc.nextInt();
bellmanFord(edges, V, E, src);
sc.close();

}
}
