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
  static int[] needCnts;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TestCnt = Integer.parseInt(br.readLine());
    int[] answerList = new int[TestCnt];

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
      needCnts = new int[N+1];

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
        needCnts[b]++;
      }

      W = Integer.parseInt(br.readLine());
      startNums = new ArrayList<>();
      visited = new boolean[N+1];

      visit1(W);

      answer = bfs();
      answerList[test] = answer;
    }

    for (int answer : answerList) System.out.println(answer);

  }

  static void visit1(int node) {
    visited[node] = true;
    if (vector2.get(node).size()==0 && !startNums.contains(node)) {
      startNums.add(node);
    }
    for (int i = 0;i<vector2.get(node).size();i++) {
      int next = vector2.get(node).get(i);
      if (!visited[next]){
        visit1(next);
      }
    }
  }

  static int bfs() {
    Queue<Integer> que = new LinkedList<>();
    int[] timeToBuild = new int[N+1];
    for (int num : startNums) {
      timeToBuild[num] = delays[num];
      que.add(num);
    }

    while(!que.isEmpty()) {
      int node = que.peek();
      que.poll();
      for (int i = 0;i<vector1.get(node).size();i++) {
        int next = vector1.get(node).get(i);
        if (timeToBuild[next] < timeToBuild[node] + delays[next]) {
          timeToBuild[next] = timeToBuild[node] + delays[next];
        }
        needCnts[next]--;
        if (needCnts[next] == 0) {
          que.add(next);
        }
      }
    }

    return timeToBuild[W];
  }

}
