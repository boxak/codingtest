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

      for (int i = 1;i<=N;i++) {
        times[i] = Integer.parseInt(st.nextToken());
      }

      for (int i = 0;i<K;i++) {
        str = br.readLine();
        st = new StringTokenizer(str," ");

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        vector.get(b).add(a);
      }
      int W = Integer.parseInt(br.readLine());
      answers[test++] = findMinSecond(N,K,W,times,vector);
    }

  }

  static int findMinSecond(int N,int K,int W, int[] times,ArrayList<ArrayList<Integer>> vector) {

    int sum = 0;
    sum+=times[W];
    while(true) {

    }

    return 0;
  }

}
