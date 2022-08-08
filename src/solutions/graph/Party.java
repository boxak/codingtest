package solutions.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Party {

  static class Pair {
    int node;
    int time;
    Pair(int node, int time) {
      this.node=node;
      this.time=time;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int X = Integer.parseInt(st.nextToken());

    ArrayList<ArrayList<Pair>> vector = new ArrayList<>();

    for (int i = 0;i<=N;i++) vector.add(new ArrayList<>());

    for (int i = 0;i<M;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
//
      vector.get(a).add(new Pair(b,c));
    }

    int answer = getTime(N,X,vector);
    System.out.println(answer);
  }

  static int getTime(int N, int X, ArrayList<ArrayList<Pair>> vector) {
    int max = Integer.MIN_VALUE;
    for (int i = 1;i<=N;i++) {
      int time = dijkstra(N,i,X,vector) + dijkstra(N,X,i,vector);
      if (time > max) max = time;
    }
    return max;
  }

  static int dijkstra(int N,int start, int end, ArrayList<ArrayList<Pair>> vector) {
    int[] dp = new int[N+1];
    boolean[] check = new boolean[N+1];
    int minDist = -1;

    for (int i = 1;i<=N;i++) dp[i] = Integer.MAX_VALUE;

    dp[start] = 0;

    for (int i = 1;i<=N;i++) {
      int node = -1;
      int minValue = Integer.MAX_VALUE;

      for (int j = 1;j<=N;j++) {
        if (dp[j] < minValue && !check[j]) {
          node = j;
          minValue = dp[j];
        }
      }

      if (node == end) {
        minDist = minValue;
        break;
      }
      check[node] = true;

      for (int j = 0;j<vector.get(node).size();j++) {
        int next = vector.get(node).get(j).node;
        int time = vector.get(node).get(j).time;
        if (dp[next] > dp[node] + time) dp[next] = dp[node] + time;
      }

    }
    return minDist;
  }
}
