package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LeaveCompany {

  static boolean[] arr;
  static int Ti[];
  static int Pi[];
  static int answer;
  static int N;

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    Ti = new int[N];
    Pi = new int[N];

    for (int i = 0;i<N;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      Ti[i] = Integer.parseInt(st.nextToken());
      Pi[i] = Integer.parseInt(st.nextToken());
    }

    arr = new boolean[N];
    answer = 0;

    dfs(0);
    System.out.println(answer);
  }

  static void dfs(int x) {
    if (x==N) {
      if (checkValidation()) {
        int sum = 0;
        for (int i = 0;i<N;i++) {
          if (arr[i]) {
            sum+=Pi[i];
          }
        }
        if (sum>answer) answer = sum;
      }
      return;
    }
    arr[x] = true;
    dfs(x+1);
    arr[x] = false;
    dfs(x+1);
  }

  static boolean checkValidation() {
    for (int i = 0;i<N;i++) {
      if (arr[i]) {
        if (i+Ti[i]>N) return false;
        for (int j = i + 1; j < i + Ti[i]; j++) {
          if (arr[j])
            return false;
        }
      }
    }
    return true;
  }
}
