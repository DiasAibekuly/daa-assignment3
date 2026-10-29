import java.util.*;

public class Problem06 {
    public int countDependencyGroups(int n, int[][] dependencies) {
        if (n <= 0) return 0;

        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] dep : dependencies) {
            graph.get(dep[0]).add(dep[1]);
        }

        int[] dfn = new int[n];
        int[] low = new int[n];
        Arrays.fill(dfn, -1);

        boolean[] inStack = new boolean[n];
        int[] tarjanStack = new int[n];
        int tarjanTop = -1;

        int[] callStack = new int[n];
        int[] edgeIdx = new int[n];

        int timer = 0;
        int sccCount = 0;

        for (int i = 0; i < n; i++) {
            if (dfn[i] == -1) {
                int callTop = 0;
                callStack[0] = i;

                dfn[i] = low[i] = timer++;
                tarjanStack[++tarjanTop] = i;
                inStack[i] = true;

                while (callTop >= 0) {
                    int u = callStack[callTop];
                    List<Integer> neighbors = graph.get(u);

                    if (edgeIdx[u] < neighbors.size()) {
                        int v = neighbors.get(edgeIdx[u]++);
                        if (dfn[v] == -1) {
                            dfn[v] = low[v] = timer++;
                            tarjanStack[++tarjanTop] = v;
                            inStack[v] = true;

                            callTop++;
                            callStack[callTop] = v;
                        } else if (inStack[v]) {
                            low[u] = Math.min(low[u], dfn[v]);
                        }
                    } else {
                        if (callTop > 0) {
                            int p = callStack[callTop - 1];
                            low[p] = Math.min(low[p], low[u]);
                        }

                        if (low[u] == dfn[u]) {
                            sccCount++;
                            while (tarjanTop >= 0) {
                                int v = tarjanStack[tarjanTop--];
                                inStack[v] = false;
                                if (v == u) break;
                            }
                        }
                        callTop--;
                    }
                }
            }
        }
        return sccCount;
    }
}