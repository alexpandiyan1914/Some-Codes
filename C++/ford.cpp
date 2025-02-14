#include <iostream>
#include <vector>
#include <climits>
#include <cstring>

using namespace std;

bool dfs(vector<vector<int>>& residualGraph, int source, int sink, vector<int>& parent, vector<bool>& visited) {
    visited[source] = true;
    if (source == sink)
        return true;
    for (int v = 0; v < residualGraph.size(); v++) {
        if (!visited[v] && residualGraph[source][v] > 0) {
            parent[v] = source;
            if (dfs(residualGraph, v, sink, parent, visited))
                return true;
        }
    }
    return false;
}

int fordFulkerson(vector<vector<int>>& graph, int source, int sink) {
    int n = graph.size();
    vector<vector<int>> residualGraph = graph;
    vector<int> parent(n);
    int maxFlow = 0;

    while (true) {
        vector<bool> visited(n, false);
        if (!dfs(residualGraph, source, sink, parent, visited))
            break;
        int pathFlow = INT_MAX;
        for (int v = sink; v != source; v = parent[v]) {
            int u = parent[v];
            pathFlow = min(pathFlow, residualGraph[u][v]);
        }
        for (int v = sink; v != source; v = parent[v]) {
            int u = parent[v];
            residualGraph[u][v] -= pathFlow;
            residualGraph[v][u] += pathFlow;
        }
        maxFlow += pathFlow;
        cout << "Augmenting Path: ";
        for (int v = sink; v != source; v = parent[v]) {
            cout << v << " <- ";
        }
        cout << source << " (Flow added: " << pathFlow << ")\n";
    }

    cout << "\nMinimum s-t Cut Edges:\n";
    vector<bool> visited(n, false);
    dfs(residualGraph, source, sink, parent, visited);
    for (int u = 0; u < n; u++) {
        if (visited[u]) {
            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] > 0) {
                    cout << u << " -> " << v << endl;
                }
            }
        }
    }
    return maxFlow;
}

int main() {
    int n, e, source, sink;
    cout << "Enter the number of vertices: ";
    cin >> n;
    vector<vector<int>> graph(n, vector<int>(n, 0));
    cout << "Enter the number of edges: ";
    cin >> e;
    cout << "Enter the edges with capacities (u v capacity):\n";
    for (int i = 0; i < e; i++) {
        int u, v, capacity;
        cin >> u >> v >> capacity;
        graph[u][v] = capacity;
    }
    cout << "Enter the source node: ";
    cin >> source;
    cout << "Enter the sink node: ";
    cin >> sink;
    int maxFlow = fordFulkerson(graph, source, sink);
    cout << "\nThe Maximum Flow is: " << maxFlow << endl;
    return 0;
}
