package solutions.sort;

import java.util.Arrays;

public class Distinct {
    public static int solution(int[] A) {
        Arrays.sort(A);
        int cnt = 0;
        if (A.length>0) cnt = 1;

        for (int i=1;i<A.length;i++) {
            if (A[i-1] != A[i]) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int a = solution(new int[]{1,2,3,3,3,2,4,5,6});
        System.out.println(a);
        System.out.println();
    }

}
