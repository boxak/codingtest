package solutions.greedy;

public class Joystick {
    public static int solution(String name) {
        int answer = 0;
        int nameLen = name.length();
        int distance = name.length() - 1;
        for (int i = 0; i < nameLen; i++) {
            int num1 = name.charAt(i) - 'A';
            int num2 = 'Z' - name.charAt(i) + 1;
            answer += num1 < num2 ? num1 : num2;

            if (name.charAt(i) == 'A') {
                int nextInx = i + 1;
                int countA = 0;
                while(nextInx<nameLen && name.charAt(nextInx) == 'A') {
                    nextInx++;
                    countA++;
                }
                int tmp = 2*(i-1)+(nameLen - (i+1) - countA);
                if (distance>tmp) distance = tmp;
            }
        }

        answer+=distance;

        return answer;
    }

    public static void main(String args[]) {
        System.out.println("answer : " + solution("AXAAX"));
    }
}
