import java.util.*;

public class Problem05 {
    public int countDependencyGroups(int n, int[][] dependencies) {
        if (n <= 0) return 0;

        List<List<Integer>> graph = new ArrayList<>(n);
        List<List<Integer>> reverseGraph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }
        for (int[] dep : dependencies) {
            graph.get(dep[0]).add(dep[1]);
            reverseGraph.get(dep[1]).add(dep[0]);
        }

        boolean[] visited = new boolean[n];
        int[] finishOrder = new int[n];
        int finishPtr = 0;

        int[] stack = new int[n];
        int[] edgeIdx = new int[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int top = 0;
                stack[0] = i;
                visited[i] = true;

                while (top >= 0) {
                    int u = stack[top];
                    List<Integer> neighbors = graph.get(u);
                    if (edgeIdx[u] < neighbors.size()) {
                        int v = neighbors.get(edgeIdx[u]++);
                        if (!visited[v]) {
                            visited[v] = true;
                            top++;
                            stack[top] = v;
                        }
                    } else {
                        finishOrder[finishPtr++] = u;
                        top--;
                    }
                }
            }
        }

        boolean[] visited2 = new boolean[n];
        int sccCount = 0;

        for (int i = finishPtr - 1; i >= 0; i--) {
            int root = finishOrder[i];
            if (!visited2[root]) {
                sccCount++;
                int head = 0, tail = 0;
                stack[tail++] = root;
                visited2[root] = true;

                while (head < tail) {
                    int u = stack[head++];
                    for (int v : reverseGraph.get(u)) {
                        if (!visited2[v]) {
                            visited2[v] = true;
                            stack[tail++] = v;
                        }
                    }
                }
            }
        }
        return sccCount;
    }
}