package solutions.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ACMCraft {

  static int N;
  static int K;
  static ArrayList<ArrayList<Integer>> vector1;
  static ArrayList<ArrayList<Integer>> vector2;
  static int answer;
  static int[] delays;
  static int W;
  static ArrayList<Integer> startNums;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TestCnt = Integer.parseInt(br.readLine());

    for (int test = 0;test<TestCnt;test++) {
      vector1 = new ArrayList<>();
      vector2 = new ArrayList<>();

      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      answer = 0;

      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      for (int i = 0;i<=N;i++) {
        vector1.add(new ArrayList<>());
        vector2.add(new ArrayList<>());
      }
      delays = new int[N+1];

      str = br.readLine();
      st = new StringTokenizer(str," ");

      for (int i = 1;i<=N;i++) {
        delays[i] = Integer.parseInt(st.nextToken());
      }

      //건물 a를 짓고 b를 지을 수 있다는 얘기.
      for (int i = 0;i<K;i++) {
        str = br.readLine();
        st = new StringTokenizer(str," ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        vector1.get(a).add(b);
        vector2.get(b).add(a);
      }

      W = Integer.parseInt(br.readLine());
      startNums = new ArrayList<>();

      visit1(W);

      answer = bfs();

    }

  }

  static void visit1(int W) {
    if (vector2.get(W).size()==0) {
      startNums.add(W);
    }
    for (int i = 0;i<vector2.get(W).size();i++) {
      int next = vector2.get(W).get(i);
      visit1(next);
    }
  }

  static int bfs() {
    Queue<Integer> que = new LinkedList<>();
    int[] timeToBuild = new int[N+1];
  }

}
