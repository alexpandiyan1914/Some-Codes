#include <iostream>
#include <vector>
#include <queue>
#include <iomanip>
#include <climits>
using namespace std;
const int INF = INT_MAX;
typedef pair<int, int> pii;
void printTable(vector<vector<int>> &iterParents, vector<vector<int>> &iterDistances, int V)
{
    const int col1 = 10, col2 = 8, col3 = 14, col4 = 7;
    cout << left << setw(col1) << "\nVertex    ";
    for (int i = 0; i < V; i++)
        cout << setw(col3) << ("   It-" + to_string(i + 1));
    cout << endl << setw(col1) << "    ";
    for (int i = 0; i < V; i++)
        cout << setw(col3) << "Dist Parent";
    cout << setw(col2) << "Shortest path from source" << endl;
    cout << string(col1 + col2 + col2 + (col3 * V), '-') << endl;
    for (int i = 0; i < V; i++)
    {
        cout << setw(col1) << i;
        for (int j = 0; j < V; j++)
        {
            cout << setw(col4) << (iterDistances[i][j] == INF ? "inf" : to_string(iterDistances[i][j]))
                 << setw(col4) << (iterParents[i][j] == -1 ? "-" : to_string(iterParents[i][j]));
        }
        cout << setw(col2) << (iterDistances[i][V - 1] == INF ? "inf" : to_string(iterDistances[i][V - 1]));
        cout << "\n";
    }
    cout << endl;
}
void dijkstra(vector<vector<pair<int, int>>> &adj, int src, int V)
{
    priority_queue<pii, vector<pii>, greater<pii>> pq;
    vector<int> dist(V, INF);
    vector<int> parent(V, -1);
    vector<vector<int>> iterDistances(V, vector<int>(V, INF));
    vector<vector<int>> iterParents(V, vector<int>(V, -1));
    
    dist[src] = 0;
    pq.push({0, src});
    
    for (int count = 0; count < V; count++)
    {
        if (pq.empty())
            break;
        int u = pq.top().second;
        pq.pop();
        for (auto &edge : adj[u])
        {
            int v = edge.first;
            int weight = edge.second;
            if (dist[u] + weight < dist[v])
            {
                dist[v] = dist[u] + weight;
                parent[v] = u;
                pq.push({dist[v], v});
            }
        }
        for (int i = 0; i < V; i++) {
            iterDistances[i][count] = dist[i];
            iterParents[i][count] = parent[i];
        }
    }
    printTable(iterParents, iterDistances, V);
}
int main()
{
    int V, E;
    cout << "Enter number of vertices: ";
    cin >> V;
    cout << "Enter number of edges: ";
    cin >> E;
    vector<vector<pair<int, int>>> adj(V);
    cout << "Enter the edges (start vertex, end vertex, weight):\n";
    for (int i = 0; i < E; ++i)
    {
        int u, v, weight;
        cin >> u >> v >> weight;
        adj[u].push_back({v, weight});
        adj[v].push_back({u, weight});
    }
    int src;
    cout << "Enter the source vertex: ";
    cin >> src;
    dijkstra(adj, src, V);
    return 0;
}
