package solutions.greedy;

public class TrainingUniform {
    public int solution(int n,int[] lost,int[] reserve) {
        int answer = 0;

        int[] students = new int[n+1];

        for (int i =1;i<=n;i++) {
            students[i] = 1;
        }

        int lostLen = lost.length;
        int reserveLen = reserve.length;

        for (int i = 0;i<lostLen;i++) {
            int lostNum = lost[i];
            students[lostNum]--;
        }

        for (int i =0;i<reserveLen;i++) {
            int reserveNum = reserve[i];
            students[reserveNum]++;
        }

        if (students[1]==0 && students[2]==2) {
            students[1]++;
            students[2]--;
        }

        for (int i =2;i<=n;i++) {
            if (students[i]==0) {
                if (students[i-1]==2) {
                    students[i-1]--;
                    students[i]++;
                }
            }
        }

        for (int i = 1;i<=n;i++) {
            if (students[i]>=1) {
                answer++;
            }
        }

        return answer;
    }
}
