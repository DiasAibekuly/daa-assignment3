import java.util.*;

public class Problem09 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) return new int[0];

        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
        }

        int[] state = new int[numCourses];
        int[] order = new int[numCourses];
        int[] index = new int[]{numCourses - 1};

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycle(graph, state, order, index, i)) {
                    return new int[0];
                }
            }
        }
        return order;
    }

    private boolean hasCycle(List<List<Integer>> graph, int[] state, int[] order, int[] index, int u) {
        state[u] = 1;
        for (int v : graph.get(u)) {
            if (state[v] == 1) return true;
            if (state[v] == 0 && hasCycle(graph, state, order, index, v)) return true;
        }
        state[u] = 2;
        order[index[0]--] = u;
        return false;
    }
}