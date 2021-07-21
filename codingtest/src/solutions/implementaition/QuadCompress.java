package solutions.implementaition;

public class QuadCompress {

  static int cnt0 = 0;
  static int cnt1 = 0;
  static int[][] arr = new int[1030][1030];

  public static int[] solution(int[][] arr2) {
    int[] answer = {};
    int r1 = 0;
    int r2 = arr2.length - 1;
    int c1 = 0;
    int c2 = arr2[0].length - 1;
    arr = arr2;
    process(r1, r2, c1, r2);
    answer = new int[2];
    answer[0] = cnt0;
    answer[1] = cnt1;
    return answer;
  }

  public static void process(int r1, int r2, int c1, int c2) {
    //System.out.println(r1+" "+r2+" "+c1+" "+c2);
    boolean check = true;
    int num = arr[r1][c1];
    for (int i = r1; i <= r2; i++) {
      for (int j = c1; j <= c2; j++) {
        if (arr[i][j] != num) {
          check = false;
          break;
        }
      }
      if (!check) {
        break;
      }
    }
    if (!check) {
      int size = r2 - r1 + 1;
      size /= 2;
      process(r1, r1 + size - 1, c1, c1 + size - 1);
      process(r1 + size, r2, c1, c1 + size - 1);
      process(r1, r1 + size - 1, c1 + size, c2);
      process(r1 + size, r2, c1 + size, c2);
    } else {
      if (num == 0) cnt0++;
      if (num == 1) cnt1++;
    }
  }

  public static void main(String args[]) {
    int[] arr = solution(new int[][]{{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,1,0,0,1,1,1,1},
        {0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1},{0,0,0,0,1,0,0,1},{0,0,0,0,1,1,1,1}});
    System.out.println(arr[0]+" "+arr[1]);
  }
}
