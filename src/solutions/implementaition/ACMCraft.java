package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ACMCraft {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TestCnt = Integer.parseInt(br.readLine());
    int[] answers = new int[TestCnt];

    for (int test = 0;test<TestCnt;test++) {

      int N,K;
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      int[] times = new int[N+1];
      ArrayList<ArrayList<Integer>> vector = new ArrayList<>();

      for (int i = 0;i<=N;i++) {
        vector.add(new ArrayList<>());
      }

      str = br.readLine();
      st = new StringTokenizer(str," ");

      for (int i = 0;i<N;i++) {
        times[i] = Integer.parseInt(st.nextToken());
      }

      for (int i = 0;i<K;i++) {
        str = br.readLine();
        st = new StringTokenizer(str," ");

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        vector.get(a).add(b);
      }
      answers[test++] = findMinSecond(N,K,times,vector);
    }

  }

  static int findMinSecond(int N,int K, int[] times,ArrayList<ArrayList<Integer>> vector) {

    int root = findRoot(N,vector);

    return 0;
  }

  static int findRoot(int N,ArrayList<ArrayList<Integer>> vector) {
    int[] parents = new int[N+1];
    for (int i = 0;i<N;i++) parents[i] = i;

    for (int i = 0;i<vector.size();i++) {
      int parent = i+1;
      ArrayList<Integer> list = vector.get(i);

    }

  }
}
