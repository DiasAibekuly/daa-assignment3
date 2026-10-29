### DAA Assignment3 - Report and Defense (50 points)

##### Problem 1 and 2 - Number of Provinces (10 points)
- **Problem 1 (DFS Approach):** Traverses the adjacency matrix using Depth-First Search (DFS). A `visited` boolean array tracks processed nodes. Each outer loop iteration triggering a new DFS traversal increments the province count.
  - **Time Complexity:** $O(N^2)$
  - **Space Complexity:** $O(N)$

- **Problem 2 (Disjoint Set Union - DSU):** Implements Disjoint Set Union with path compression. Starts with $N$ independent sets. Iterates through the matrix and performs `union` operations for connected cities, decrementing the total component count.
  - **Time Complexity:** $O(N^2 \cdot \alpha(N))$
  - **Space Complexity:** $O(N)$

##### Problem 3 and 4 - Min Cost Spanning Tree (10 points)
- **Problem 3 (Kruskal's Algorithm):** Generates all $E = \frac{N(N-1)}{2}$ edges using Manhattan distance. Sorts edges by weight in ascending order and uses DSU to build the Minimum Spanning Tree (MST).
  - **Time Complexity:** $O(N^2 \log N)$
  - **Space Complexity:** $O(N^2)$

- **Problem 4 (Prim's Algorithm):** Builds the MST incrementally using a Min-Heap (PriorityQueue). Starts from node 0 and repeatedly selects the minimum-weight edge connecting an unvisited vertex to the current tree.
  - **Time Complexity:** $O(N^2)$
  - **Space Complexity:** $O(N)$

##### Problem 5 and 6 - Software Dependency Cycles (10 points)
- **Problem 5 (Kosaraju's Algorithm):** Finds Strongly Connected Components (SCCs) in two DFS passes. Implemented iteratively without recursion to avoid JVM `StackOverflowError` for $N = 10^5$. Pass 1 collects finish order, Pass 2 traverses the transposed graph $G^T$.
  - **Time Complexity:** $O(V + E)$
  - **Space Complexity:** $O(V + E)$

- **Problem 6 (Tarjan's Algorithm):** Performs a single-pass iterative DFS maintaining discovery time (`dfn`) and lowest reachable time (`low`) for each node. Uses an explicit stack to handle $N = 10^5$ without recursion overhead.
  - **Time Complexity:** $O(V + E)$
  - **Space Complexity:** $O(V + E)$

##### Problem 7 and 8 - Course Schedule (10 points)
- **Problem 7 (DFS Cycle Detection):** Detects directed cycles using 3-state nodes: `0` (unvisited), `1` (visiting/in current recursion stack), `2` (visited). Finding a neighbor with state `1` indicates a back-edge (cycle).
  - **Time Complexity:** $O(V + E)$
  - **Space Complexity:** $O(V + E)$

- **Problem 8 (Kahn's Algorithm):** BFS-based topological sorting using in-degree counts. Pushes nodes with in-degree 0 into a Queue.
  - **Time Complexity:** $O(V + E)$
  - **Space Complexity:** $O(V + E)$

##### Problem 9 and 10 - Course Schedule II (10 points)
- **Problem 9 (DFS Topological Sort):** Extends DFS cycle detection. Once a node and all its descendants are processed (state `2`), it is placed in the result order array.
  - **Time Complexity:** $O(V + E)$
  - **Space Complexity:** $O(V + E)$

- **Problem 10 (Kahn's Algorithm Topological Order):** Implements Kahn's algorithm and records each dequeued node directly into an output array.
  - **Time Complexity:** $O(V + E)$
  - **Space Complexity:** $O(V + E)$