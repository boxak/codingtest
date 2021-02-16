package solutions.implementaition;

public class PassingCars {
    public int solution(int[] A) {
        int dp[] = new int[A.length];
        int cntZero = 0;

        if (A[0]==0) cntZero = 1;

        for (int i = 1;i<A.length;i++) {
            if (A[i]==0) cntZero++;
            else {
                dp[i] = cntZero;
            }
        }

        int sum = 0;
        for (int i = 0;i<A.length;i++) {
            if (dp[i] != 0) {
                sum+=dp[i];
            }
            if (sum>1000000000) break;
        }

        if (sum>1000000000) {
            sum = -1;
        }
        return sum;
    }
}
