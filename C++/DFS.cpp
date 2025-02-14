#include <iostream>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>
#include <map>
using namespace std;

class Graph {
    int V; // Number of vertices
    vector<vector<int>> adj; // Adjacency list
    vector<vector<int>> transposeAdj; // Transposed graph for SCC
    vector<int> discoveryTime, finishingTime, visited, topologicalOrder;
    map<pair<int, int>, string> edgeClassification;
    int time;
    bool hasCycle;

public:
    Graph(int vertices) : V(vertices), adj(vertices), transposeAdj(vertices) {
        discoveryTime.resize(V, -1);
        finishingTime.resize(V, -1);
        visited.resize(V, 0);
        time = 0;
        hasCycle = false;
    }

    void addEdge(int u, int v) {
        adj[u].push_back(v);
    }

    void dfs(int u) {
        visited[u] = 1;
        discoveryTime[u] = ++time;

        for (int v : adj[u]) {
            if (visited[v] == 0) { // Tree Edge
                edgeClassification[{u, v}] = "Tree Edge";
                dfs(v);
            } else if (visited[v] == 1) { // Back Edge
                edgeClassification[{u, v}] = "Back Edge";
                hasCycle = true;
            } else if (discoveryTime[u] < discoveryTime[v]) { // Forward Edge
                edgeClassification[{u, v}] = "Forward Edge";
            } else { // Cross Edge
                edgeClassification[{u, v}] = "Cross Edge";
            }
        }

        visited[u] = 2;
        finishingTime[u] = ++time;
        topologicalOrder.push_back(u);
    }

    void dfsTraversal() {
        fill(visited.begin(), visited.end(), 0);
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        reverse(topologicalOrder.begin(), topologicalOrder.end());
    }

    void transposeGraph() {
        for (int u = 0; u < V; u++) {
            for (int v : adj[u]) {
                transposeAdj[v].push_back(u);
            }
        }
    }

    void sccUtil(int u, vector<int>& component) {
        visited[u] = 1;
        component.push_back(u);
        for (int v : transposeAdj[u]) {
            if (!visited[v]) {
                sccUtil(v, component);
            }
        }
    }

    void findStronglyConnectedComponents() {
        fill(visited.begin(), visited.end(), 0);
        transposeGraph();

        vector<vector<int>> sccs;
        for (int i = V - 1; i >= 0; i--) {
            int u = topologicalOrder[i];
            if (!visited[u]) {
                vector<int> component;
                sccUtil(u, component);
                sccs.push_back(component);
            }
        }

        cout << "Number of Strongly Connected Components: " << sccs.size() << endl;
        for (int i = 0; i < sccs.size(); i++) {
            cout << "SCC " << i + 1 << ": ";
            for (int node : sccs[i]) {
                cout << node << " ";
            }
            cout << endl;
        }
    }

    void topologicalSortIndegree() {
        vector<int> indegree(V, 0);
        for (int u = 0; u < V; u++) {
            for (int v : adj[u]) {
                indegree[v]++;
            }
        }

        queue<int> q;
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.push(i);
            }
        }

        vector<int> topoOrder;
        while (!q.empty()) {
            int u = q.front();
            q.pop();
            topoOrder.push_back(u);

            for (int v : adj[u]) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    q.push(v);
                }
            }
        }

        cout << "Topological Sorting using In-degree: ";
        for (int node : topoOrder) {
            cout << node << " ";
        }
        cout << endl;
    }

    void printEdgeClassification() {
        cout << "Edge Classification:\n";
        for (auto& edge : edgeClassification) {
            cout << edge.first.first << " -> " << edge.first.second << ": " << edge.second << endl;
        }
    }

    void printDiscoveryAndFinishingTimes() {
        cout << "Discovery and Finishing Times:\n";
        for (int i = 0; i < V; i++) {
            cout << "Node " << i << ": Discovery Time = " << discoveryTime[i]
                 << ", Finishing Time = " << finishingTime[i] << endl;
        }
    }

    void checkCycle() {
        if (hasCycle) {
            cout << "The graph contains a cycle.\n";
        } else {
            cout << "The graph does not contain a cycle.\n";
        }
    }

    vector<int> getTopologicalOrder() {
        return topologicalOrder;
    }
};

int main() {
    int V, E;
    cout << "Enter the number of vertices: ";
    cin >> V;
    cout << "Enter the number of edges: ";
    cin >> E;
    Graph graph(V);
    cout << "Enter the edges (u v) where u -> v:\n";
    for (int i = 0; i < E; i++) {
        int u, v;
        cin >> u >> v;
        graph.addEdge(u, v);
    }

    cout << "\nDFS Traversal:\n";
    graph.dfsTraversal();
    graph.printDiscoveryAndFinishingTimes();
    graph.printEdgeClassification();
    graph.checkCycle();
    cout << "\nTopological Sorting using DFS: ";
    vector<int> topoOrder = graph.getTopologicalOrder();
    for (int node : topoOrder) {
        cout << node << " ";
    }
    cout << endl;
    cout << "\nFinding Strongly Connected Components:\n";
    graph.findStronglyConnectedComponents();
    cout << "\nTopological Sorting using In-degree:\n";
    graph.topologicalSortIndegree();
    return 0;
}
