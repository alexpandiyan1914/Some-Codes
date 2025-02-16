#include <iostream>
#include <queue>
#include <utility>
using namespace std;

#define MAX 100

typedef struct Node {
    int v;
    struct Node* next;
} Node;

typedef struct Graph {
    int V;
    int adjmatrix[MAX][MAX];
    Node* adjlist[MAX];
} Graph;

int dist[MAX];
int parent[MAX];
bool visited[MAX] = {false};

// Function Prototypes
void initializeGraph(Graph* g, int vertices);
Node* createNode(int vertex);
void addEdge(Graph* g, int u, int v, bool isDirected);
void printadjMatrix(Graph* g);
void printadjList(Graph* g);
bool bfs(Graph* g, int source, bool visited[MAX]);
bool detectCycle(Graph* g);
bool cycleBFS(Graph* g, int start);
int connectedComponents(Graph* g);
void findShortestPath(Graph* g, int source, int dest);
void displayMenu();

void initializeGraph(Graph* g, int vertices) {
    g->V = vertices;
    for (int i = 0; i < MAX; i++) {
        for (int j = 0; j < MAX; j++) {
            g->adjmatrix[i][j] = 0;
        }
        dist[i] = -1;
        parent[i] = -1;
        g->adjlist[i] = NULL;
    }
}

Node* createNode(int vertex) {
    Node* newNode = new Node;
    newNode->v = vertex;
    newNode->next = NULL;
    return newNode;
}

void addEdge(Graph* g, int u, int v, bool isDirected) {
    g->adjmatrix[u][v] = 1;

    Node* node = createNode(v);
    node->next = g->adjlist[u];
    g->adjlist[u] = node;

    if (!isDirected) {
        g->adjmatrix[v][u] = 1;
        if (u != v) {
            node = createNode(u);
            node->next = g->adjlist[v];
            g->adjlist[v] = node;
        }
    }
}

void printadjMatrix(Graph* g) {
    cout << "Adjacency Matrix:\n";
    for (int i = 1; i <= g->V; i++) {
        for (int j = 1; j <= g->V; j++) {
            cout << g->adjmatrix[i][j] << " ";
        }
        cout << "\n";
    }
}

void printadjList(Graph* g) {
    cout << "Adjacency List:\n";
    for (int i = 1; i <= g->V; i++) {
        cout << i << ": ";
        Node* temp = g->adjlist[i];
        while (temp) {
            cout << temp->v << " -> ";
            temp = temp->next;
        }
        cout << "NULL\n";
    }
}

bool bfs(Graph *g, int source, bool visited[MAX]) {
    int queue[MAX], front = 0, rear = 0;

    // Initialize distances and parents
    for (int i = 1; i <= g->V; i++) {
        parent[i] = -1;
        dist[i] = -1;
    }

    dist[source] = 0;
    parent[source] = -1;
    visited[source] = true;
    queue[rear++] = source;

    while (front < rear) {
        int u = queue[front++];
        for (int v = 1; v <= g->V; v++) {
            if (g->adjmatrix[u][v] && !visited[v]) {
                visited[v] = true;
                dist[v] = dist[u] + 1;
                parent[v] = u;
                queue[rear++] = v;
            }
        }
    }

    // Output BFS traversal result
    cout << "BFS Traversal from vertex " << source << ":\n";
    cout << "Vertex\tDistance\tParent\n";
    for (int i = 1; i <= g->V; i++) {
        cout << i << "\t  " << (dist[i] == -1 ? "-" : to_string(dist[i])) << "\t\t";
        cout << (parent[i] == -1 ? "-" : to_string(parent[i])) << "\n";
    }

    return false;  // Return value remains unchanged as per your original algorithm
}


bool detectCycle(Graph* g) {
    fill(visited, visited + MAX, false);

    for (int i = 1; i <= g->V; i++) {
        if (!visited[i]) {
            if (cycleBFS(g, i)) {
                return true;
            }
        }
    }
    return false;
}

bool cycleBFS(Graph* g, int start) {
    queue<pair<int, int>> q;
    visited[start] = true;
    q.push({start, -1});

    while (!q.empty()) {
        int u = q.front().first;
        int parent = q.front().second;
        q.pop();

        for (int v = 1; v <= g->V; v++) {
            if (g->adjmatrix[u][v]) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.push({v, u});
                } else if (v != parent) {
                    return true;
                }
            }
        }
    }
    return false;
}

int connectedComponents(Graph* g) {
    fill(visited, visited + MAX, false);  // Reset visited array
    int componentCount = 0;

    for (int i = 1; i <= g->V; i++) {
        if (!visited[i]) {
            // Start BFS for a new component
            queue<int> q;
            q.push(i);
            visited[i] = true;

            while (!q.empty()) {
                int u = q.front();
                q.pop();

                for (int v = 1; v <= g->V; v++) {
                    if (g->adjmatrix[u][v] && !visited[v]) {
                        visited[v] = true;
                        q.push(v);
                    }
                }
            }
            componentCount++;
        }
    }
    return componentCount;
}


void findShortestPath(Graph* g, int source, int dest) {
    fill(visited, visited + MAX, false);
    bfs(g, source, visited);

    if (dist[dest] == -1) {
        cout << "No path exists from " << source << " to " << dest << "\n";
        return;
    }

    cout << "Shortest dist from " << source << " to " << dest << " is: " << dist[dest] << "\n";
    cout << "Path: ";
    int path[MAX], pathIndex = 0;
    for (int v = dest; v != -1; v = parent[v]) {
        path[pathIndex++] = v;
    }
    for (int i = pathIndex - 1; i >= 0; i--) {
        cout << path[i] << (i > 0 ? " -> " : "\n");
    }
}

void displayMenu() {
    cout << "\nGraph Operations Menu:\n";
    cout << "1. Insert Edge\n";
    cout << "2. Adjacency Matrix\n";
    cout << "3. Adjacency List\n";
    cout << "4. BFS Traversal\n";
    cout << "5. Find Shortest Path\n";
    cout << "6. Detect Cycle\n";
    cout << "7. Find Connected Components\n";
    cout << "8. Exit Program\n";
    cout << "Enter your choice here: ";
}

int main() {
    Graph g;
    int vertices, choice, u, v, source, dest;
    bool isDirected;
    cout << "\nEnter the number of vertices: ";
    cin >> vertices;
    initializeGraph(&g, vertices);
    cout << "Is the graph directed? (1 for Yes, 0 for No): ";
    cin >> isDirected;

    while (true) {
        displayMenu();
        cin >> choice;
        switch (choice) {
            case 1:
                cout << "Enter edge (u,v): ";
                cin >> u >> v;
                addEdge(&g, u, v, isDirected);
                cout << "Edge added.\n";
                break;
            case 2:
                printadjMatrix(&g);
                break;
            case 3:
                printadjList(&g);
                break;
            case 4:
                cout << "Enter source vertex for BFS: ";
                cin >> source;
                fill(visited, visited + MAX, false);
                bfs(&g, source, visited);
                cout << "BFS Traversal from vertex " << source << " completed.\n";
                break;
            case 5:
                cout << "Enter source and destination: ";
                cin >> source >> dest;
                findShortestPath(&g, source, dest);
                break;
            case 6:
                if (detectCycle(&g)) {
                    cout << "The graph contains a cycle.\n";
                } else {
                    cout << "The graph does not contain a cycle.\n";
                }
                break;
            case 7:
                cout << "The number of connected components is: " << connectedComponents(&g) << "\n";
                break;
            case 8:
                cout << "Exiting program...\n";
                return 0;
            default:
                cout << "Invalid choice. Please try again.\n";
        }
    }
}
