package solutions.implementaition;

public class JumpAndWarp {
    public static int solution(int n) {
        int ans = 0;

        int number = n;

        while(number>0) {
            if (number%2==0) {
                number/= 2;
            } else {
                number--;
                ans++;
            }
        }

        return ans;
    }

    public static void main(String args[]) {
        System.out.println(solution(1000000000));
    }
}
