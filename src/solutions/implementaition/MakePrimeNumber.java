package solutions.implementaition;

public class MakePrimeNumber {
    public int solution(int[] nums) {
        int answer = 0;
        int len = nums.length;

        boolean check[] = checkPrime();

        for (int i = 0;i<len;i++) {
            int num1 = nums[i];
            for (int j = i+1;j<len;j++) {
                int num2 = nums[j];
                for (int k = j+1;k<len;k++) {
                    int num3 = nums[k];
                    if (check[num1+num2+num3]==false) answer++;
                }
            }
        }
        return answer;
    }

    public boolean[] checkPrime() {
        boolean check[] = new boolean[3010];
        check[0] = true;
        check[1] = true;
        for (int i=2;i<=3000;i++) {
            if (!check[i]) {
                for (int j=2;j*i<=3000;j++) {
                    check[i*j] = true;
                }
            }
        }
        return check;
    }
}
