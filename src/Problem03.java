import java.util.*;

public class Problem03 {
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length <= 1) return 0;
        int n = points.length;
        List<int[]> edges = new ArrayList<>(n * (n - 1) / 2);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int cost = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{cost, i, j});
            }
        }
        edges.sort((a, b) -> Integer.compare(a[0], b[0]));

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int minCost = 0, edgesUsed = 0;
        for (int[] edge : edges) {
            int rootU = find(parent, edge[1]);
            int rootV = find(parent, edge[2]);
            if (rootU != rootV) {
                parent[rootU] = rootV;
                minCost += edge[0];
                edgesUsed++;
                if (edgesUsed == n - 1) break;
            }
        }
        return minCost;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent, parent[i]);
    }
}