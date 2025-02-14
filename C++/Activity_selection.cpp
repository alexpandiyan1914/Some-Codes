#include <iostream>

#include <vector>

#include <climits>

#include <iomanip>

using namespace std;

const int INF = INT_MAX;

int minKey(const vector<int> &key, const vector<bool> &mstSet, int V) {

int min = INF, min_index = -1;

for (int v = 0; v < V; v++) {

if (!mstSet[v] && key[v] < min) {

min = key[v];

min_index = v;

}

}

return min_index;

}

void printTable(const vector<int>& parent, const vector<vector<int>>& dist, const vector<int>&

vertex, int V) {

const int col1 = 15, col2 = 10,col3 = 7;

cout << left << setw(col1) << "Active Vertex"

<< setw(col2) << "Initial";

for (int i = 0; i < V; i++)

cout << setw(col2) << ("It-" + to_string(i + 1) + "(" + char(vertex[i] + 'a') + ")");

cout << setw(col3) << "Parent" << endl;

cout << string(col1 + col2 + (col2 * V) + col3, '-') << endl;

for (int i = 0; i < V; i++) {

cout << setw(col1) << char(i + 'a') << setw(col2) << (i == 0 ? "0" : "inf");

for (int j = 0; j < V; j++)

cout << setw(col2) << (dist[i][j] == INF ? "inf" : to_string(dist[i][j]));

cout << setw(col3) << (parent[i] == -1 ? '-' : char(parent[i] + 'a')) << endl;

}

int minCost = 0;

for (int i = 1; i < V; i++)

minCost += (parent[i] != -1 ? dist[i][V - 1] : 0);

cout << "\nMinimum Cost: " << minCost << endl;

}

void primMST(const vector<vector<int>> &graph, int V) {

vector<int> parent(V, -1);

vector<int> key(V, INF);

vector<bool> mstSet(V, false);

vector<pair<int, int>> cycleEdges;

vector<vector<int>> dist(V, vector<int>(V, INF));

vector<int> vertex(V, -1);

key[0] = 0;

for (int count = 0; count < V; count++) {
int u = minKey(key, mstSet, V);

if (u == -1) break;

mstSet[u] = true;

vertex[count] = u;

for (int v = 0; v < V; v++) {

if (graph[u][v] && !mstSet[v] && graph[u][v] < key[v]) {

parent[v] = u;

key[v] = graph[u][v];

} else if (graph[u][v] && mstSet[v] && parent[u] != v) {

cycleEdges.push_back({u, v});

}

}

for (int i = 0; i < V; i++)

dist[i][count] = key[i];

}

printTable(parent, dist, vertex, V);

cout << "\nFinal MST:\n";

for (int i = 1; i < V; ++i) {

cout << "Edge: (" << char(parent[i] + 'a') << "," << char(i + 'a')

<< ") Weight: " << graph[i][parent[i]] << endl;

}

if (!cycleEdges.empty()) {

cout << "\nEdges that create cycles : ";

for (auto &edge : cycleEdges) {

cout << "(" << char(edge.first + 'a') <<"," << char(edge.second + 'a')<<") " ;

}

} else {

cout << "\nNo cycle-forming edges found.\n";

}

cout<<"\n\n";

}

int main() {

int V, E, u, v, weight;

cout << "Enter number of vertices: ";

cin >> V;

cout << "Enter number of edges: ";

cin >> E;

vector<vector<int>> graph(V, vector<int>(V, 0));

cout << "Enter the edges (start vertex, end vertex, weight):\n";

for (int i = 0; i < E; ++i) {

cin >> u >> v >> weight;

graph[u][v] = weight;

graph[v][u] = weight;

}

primMST(graph, V);

return 0;

}
