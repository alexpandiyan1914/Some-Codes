#include <iostream>
#include <vector>
#include <limits>
#include <iomanip>

using namespace std;

#define INF numeric_limits<int>::max()

// Function to find the vertex with the minimum key value
int minKey(vector<int>& key, vector<bool>& inMST, int n) {
    int min = INF, min_index = -1;
    for (int v = 0; v < n; v++)
        if (!inMST[v] && key[v] < min)
            min = key[v], min_index = v;
    return min_index;
}

// Function to print the table and minimum cost
void printTable(vector<int>& parent, vector<vector<int>>& iterDistances, vector<int>& chosenVertex, int n) {
    cout << "\nPrim's Algorithm Table:\n";

    // Column Widths
    const int col1 = 15, col2 = 20, col3 = 18, col4 = 10;

    // Print header with consistent column width
    cout << left << setw(col1) << "Active Vertex"
         << setw(col2) << "Initial Distance";
    for (int i = 0; i < n; i++)
        cout << setw(col3) << ("Iteration " + to_string(i + 1) + " (" + to_string(chosenVertex[i]) + ")");
    cout << setw(col4) << "Parent" << endl;

    // Print separator line for clarity
    cout << string(col1 + col2 + (col3 * n) + col4, '-') << endl;

    // Print table content
    for (int i = 0; i < n; i++) {
        cout << setw(col1) << i 
             << setw(col2) << (i == 0 ? "0" : "∞");
        for (int j = 0; j < n; j++)
            cout << setw(col3) << (iterDistances[i][j] == INF ? "∞" : to_string(iterDistances[i][j]));
        cout << setw(col4) << parent[i] << endl;
    }

    int minCost = 0;
    for (int i = 1; i < n; i++)
        minCost += (parent[i] != -1 ? iterDistances[i][n - 1] : 0);
    
    cout << "\nMinimum Cost: " << minCost << endl;
}

// Prim’s Algorithm function
void primMST(vector<vector<int>>& graph, int n) {
    vector<int> parent(n, -1);  
    vector<int> key(n, INF);    
    vector<bool> inMST(n, false); 
    vector<vector<int>> iterDistances(n, vector<int>(n, INF)); 
    vector<int> chosenVertex(n, -1); 

    key[0] = 0; 

    for (int count = 0; count < n; count++) {
        int u = minKey(key, inMST, n);
        if (u == -1) break; 
        inMST[u] = true;
        chosenVertex[count] = u;

        for (int v = 0; v < n; v++)
            if (graph[u][v] && !inMST[v] && graph[u][v] < key[v])
                parent[v] = u, key[v] = graph[u][v];

        for (int i = 0; i < n; i++)
            iterDistances[i][count] = key[i]; 
    }

    printTable(parent, iterDistances, chosenVertex, n);
}

int main() {
    int n, e;
    cout << "Enter the number of vertices: ";
    cin >> n;
    cout << "Enter the number of edges: ";
    cin >> e;

    // Initialize adjacency matrix with INF
    vector<vector<int>> graph(n, vector<int>(n, 0));

    cout << "Enter edges (format: u v weight):\n";
    for (int i = 0; i < e; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        graph[u][v] = w;
        graph[v][u] = w;  
    }

    primMST(graph, n);
    return 0;
}
