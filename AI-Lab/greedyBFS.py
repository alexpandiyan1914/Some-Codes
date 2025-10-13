# greedy_bfs_input.py

def greedy_bfs(graph, heuristic, start, goal):
    path = [start]
    while start != goal:
        if not graph[start]:
            print("No path found.")
            return
        next_node = min(graph[start], key=lambda x: heuristic.get(x, float('inf')))
        path.append(next_node)
        start = next_node
    print("Greedy BFS Path:", path)

# Input graph
print("Enter graph edges (e.g., A:B,C B:D C:D,E D:G E:G G:):")
raw_graph = input().strip().split()
graph = {}
for entry in raw_graph:
    node, neighbors = entry.split(":")
    graph[node] = neighbors.split(",") if neighbors else []

# Input heuristic values
print("Enter heuristic values (e.g., A:5 B:3 C:4 D:1 E:2 G:0):")
raw_heuristic = input().strip().split()
heuristic = {}
for entry in raw_heuristic:
    node, value = entry.split(":")
    heuristic[node] = int(value)

# Input start and goal
start = input("Enter start node: ").strip().upper()
goal = input("Enter goal node: ").strip().upper()

# Run Greedy BFS
greedy_bfs(graph, heuristic, start, goal)