#include <bits/stdc++.h>
using namespace std;
struct Edge {
    int src, dest, weight;
};
bool compare(Edge a, Edge b) {
    return a.weight < b.weight;
}
int findParent(vector<int>& parent, int v) {
    if (parent[v] == v) return v;
    return parent[v] = findParent(parent, parent[v]);
}
void kruskal(int V, vector<Edge>& edges) {
    sort(edges.begin(), edges.end(), compare);
    vector<int> parent(V);
    for (int i = 0; i < V; i++) parent[i] = i;
    int minCost = 0;
    cout << "Selected Edges:\n";
    for (Edge e : edges) {
        int srcParent = findParent(parent, e.src);
        int destParent = findParent(parent, e.dest);

        if (srcParent != destParent) {
            cout << e.src << " - " << e.dest << " : " << e.weight << endl;
            minCost += e.weight;
            parent[srcParent] = destParent;
        }
    }
    cout << "Total Cost: " << minCost << endl;
}

int main() {
    int V, E;
    cout << "Enter number of vertices and edges: ";
    cin >> V >> E;

    vector<Edge> edges(E);
    cout << "Enter edges (src dest weight):\n";
    for (int i = 0; i < E; i++) {
        cin >> edges[i].src >> edges[i].dest >> edges[i].weight;
    }
    kruskal(V, edges);
    return 0;
}
