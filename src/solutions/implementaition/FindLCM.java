package solutions.implementaition;

public class FindLCM {
    public int solution(int[] arr) {
        int answer = 0;
        int i = 1;
        while(true) {
            boolean flag = true;
            for (int num : arr) {
                if (i%num != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer = i;
                break;
            }
            i++;
        }
        return answer;
    }
}
