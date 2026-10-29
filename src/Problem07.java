import java.util.*;

public class Problem07 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) return true;

        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
        }

        int[] state = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycle(graph, state, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasCycle(List<List<Integer>> graph, int[] state, int u) {
        state[u] = 1;
        for (int v : graph.get(u)) {
            if (state[v] == 1) return true;
            if (state[v] == 0 && hasCycle(graph, state, v)) return true;
        }
        state[u] = 2;
        return false;
    }
}