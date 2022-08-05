package solutions.implementaition;

public class TaxiFee {
    int[][] dist;
    int[][] costs;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        dist = new int[201][201];
        costs = new int[201][201];

        for (int i = 0;i<fares.length;i++) {
            int p1 = fares[i][0];
            int p2 = fares[i][1];
            int c = fares[i][2];

            costs[p1][p2] = c;
            costs[p2][p1] = c;
        }


        for (int i = 1;i<=n;i++) {
            dijkstra(n, fares, i);
        }

        for (int i = 1;i<=n;i++) {
            int fee = dist[s][i] + dist[i][a] + dist[i][b];
            answer = Math.min(answer, fee);
        }

        return answer;
    }

    void dijkstra(int n, int[][] fares, int start) {
        boolean[] check = new boolean[n+1];
        int[] dp = new int[n+1];

        for (int i = 1;i<=n;i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[start] = 0;

        for (int cnt = 0; cnt < n; cnt++) {
            int minValue = Integer.MAX_VALUE;
            int minIdx = -1;

            for (int i = 1;i<=n;i++) {
                if (!check[i] && minValue > dp[i]) {
                    minValue = dp[i];
                    minIdx = i;
                }
            }

            if (minIdx == -1) {
                break;
            }

            check[minIdx] = true;

            for (int i = 1;i<=n;i++) {
                if (i == minIdx || costs[i][minIdx] == 0) continue;
                if (minValue + costs[i][minIdx] < dp[i]) {
                    dp[i] = minValue + costs[i][minIdx];
                }
            }

        }

        for (int i = 1;i<=n;i++) {
            dist[start][i] = dp[i];
            dist[i][start] = dp[i];
        }
    }
}
