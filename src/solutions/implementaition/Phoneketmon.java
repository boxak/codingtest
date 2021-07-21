package solutions.implementaition;

public class Phoneketmon {
  public static int solution(int[] nums) {
    int answer = 0;
    int N = nums.length;
    boolean checked[] = new boolean[200010];

    for (int i = 0;i<N;i++) {
      int kind = nums[i];
      if (!checked[kind]) {
        answer++;
        checked[kind] = true;
      }
    }

    if (answer>N/2) answer = N/2;

    return answer;
  }

  public static void main(String[] args) {
    System.out.println(solution(new int[]{1,2,3,4,5,6}));
  }
}
