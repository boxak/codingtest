package solutions.pattern;

public class predictTournament {
    public static int solution(int n, int a, int b) {
        int answer = 0;

        int A = a;
        int B = b;
        int round = 1;

        while (true) {
            if (A%2!=0) A++;
            if (B%2!=0) B++;
            if (A == B) break;
            A/=2;
            B/=2;
            round++;
        }
        answer = round;
        return answer;
    }

    public static void main(String args[]) {
        System.out.println(solution(8,8,9));
    }
}
