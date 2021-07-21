package solutions.graph;

import java.util.ArrayList;

public class Ranking {

  ArrayList<ArrayList<Integer>> winners;
  ArrayList<ArrayList<Integer>> losers;
  int winnerCnt;
  int loserCnt;
  boolean[] visited1;
  boolean[] visited2;

  public int solution(int n, int[][] results) {
    int answer = 0;
    visited1 = new boolean[n+1];
    visited2 = new boolean[n+1];
    winnerCnt = 0;
    loserCnt = 0;

    winners = new ArrayList<>();
    losers = new ArrayList<>();

    for (int i = 0;i<=n;i++) {
      winners.add(new ArrayList<>());
      losers.add(new ArrayList<>());
    }

    for (int i = 0;i< results.length;i++) {
      int a = results[i][0];
      int b = results[i][1];
      winners.get(b).add(a);
      losers.get(a).add(b);
    }

    for (int i = 1;i<=n;i++) {
      init(n);
      dfs1(winners,i);
      dfs2(losers,i);
      if (winnerCnt + loserCnt-2 == n-1) answer++;
    }

    return answer;
  }

  void init(int n) {
    winnerCnt = 0;
    loserCnt = 0;
    for (int i = 0;i<=n;i++) {
      visited1[i] = false;
      visited2[i] = false;
    }
  }

  void dfs1(ArrayList<ArrayList<Integer>> winners, int inx) {
    visited1[inx] = true;
    winnerCnt++;
    for (int i = 0;i<winners.get(inx).size();i++) {
      int node = winners.get(inx).get(i);
      if (!visited1[node]) {
        dfs1(winners,node);
      }
    }
  }

  void dfs2(ArrayList<ArrayList<Integer>> losers, int inx) {
    visited2[inx] = true;
    loserCnt++;
    for (int i = 0;i<losers.get(inx).size();i++) {
      int node = losers.get(inx).get(i);
      if (!visited2[node]) {
        dfs2(losers,node);
      }
    }
  }
}
