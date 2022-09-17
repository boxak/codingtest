package solutions.implementaition;

import java.util.Arrays;

public class TheBestSet {

    static public int[] solution(int n, int s) {
        int[] answer = {};

        int num = s / n;

        if (num == 0) return new int[]{-1};

        answer = new int[n];

        double exact = ((double)s) / n;

        if (exact - num >= 0.5)

        Arrays.sort(answer);

        return answer;
    }

    public static void main(String[] args) {
        int[] arr = solution(100, 100_000_000);

        System.out.println("answer");
        for (int i = 0;i<arr.length;i++) {
            System.out.printf("%d ",arr[i]);
        }

    }
}
