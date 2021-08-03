package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TestObserver {
  static int N;
  static int[] students;
  static int B,C;
  static long answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    answer = 0;

    students = new int[N];
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");
    for (int i = 0;i<N;i++) {
      students[i] = Integer.parseInt(st.nextToken());
    }

    str = br.readLine();
    st = new StringTokenizer(str," ");
    B = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    getObserverNumber();
    System.out.println(answer);
  }

  static void getObserverNumber() {
    for (int i = 0;i<N;i++) {
      int studentNum = students[i];

      if (studentNum<=B) {
        answer+=(long)1;
      } else {
        int cnt = (studentNum - B) / C;
        if (cnt * C + B < studentNum) cnt++;
        answer += (long) cnt + (long) 1;
      }
    }
  }
}
