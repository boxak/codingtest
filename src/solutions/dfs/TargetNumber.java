package solutions.dfs;

public class TargetNumber {

  static boolean[] isPlus = new boolean[30];
  static int cnt = 0;

  public static int solution(int[] numbers, int target) {
    int answer = 0;

    dfs(0, numbers, target);

    answer = cnt;
    return answer;
  }

  public static void dfs(int inx, int[] numbers, int target) {
    if (inx == numbers.length) {
      int sum = 0;
      for (int i = 0; i < numbers.length; i++) {
        if (isPlus[i]) {
          sum+=numbers[i];
        } else {
          sum-=numbers[i];
        }
      }
      System.out.println();
      if (sum == target) {
        cnt++;
      }
      return;
    }
    isPlus[inx] = true;
    dfs(inx+1,numbers,target);
    isPlus[inx] = false;
    dfs(inx+1,numbers,target);
  }

  public static void main(String args[]) {
    System.out.println("Answer : "+solution(new int[]{1,1,1,1,1},3));
  }
}
