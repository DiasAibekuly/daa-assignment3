import java.util.*;

public class Problem04 {
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length <= 1) return 0;
        int n = points.length;
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        boolean[] inMST = new boolean[n];

        minDist[0] = 0;
        int minCost = 0;

        for (int step = 0; step < n; step++) {
            int u = -1;
            int currentMin = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!inMST[i] && minDist[i] < currentMin) {
                    currentMin = minDist[i];
                    u = i;
                }
            }

            if (u == -1) break;
            inMST[u] = true;
            minCost += currentMin;

            for (int v = 0; v < n; v++) {
                if (!inMST[v]) {
                    int dist = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    if (dist < minDist[v]) {
                        minDist[v] = dist;
                    }
                }
            }
        }
        return minCost;
    }
}