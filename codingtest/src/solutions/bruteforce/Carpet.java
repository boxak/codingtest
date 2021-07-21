package solutions.bruteforce;

public class Carpet {

  public static int[] solution(int brown, int yellow) {
    int[] answer = {};
    answer = new int[2];

    for (int row = 3; row <= 5000; row++) {
      for (int col = row; col <= 5000; col++) {
        int cnt1 = 2 * row + 2 * (col - 2);
        int cnt2 = (row - 2) * (col - 2);
        if (cnt1 == brown && cnt2 == yellow) {
          answer[0] = col;
          answer[1] = row;
          break;
        }
      }
    }

    return answer;
  }

  public static void main(String[] args) {
    int arr[] = solution(24, 24);
    System.out.println("Answer : " + arr[0] + " " + arr[1]);
  }
}
