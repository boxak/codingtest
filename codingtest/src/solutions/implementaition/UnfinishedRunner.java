package solutions.implementaition;

import java.util.Arrays;

public class UnfinishedRunner {
    static String solution(String[] participant, String[] completion) {
        String answer = "";

        Arrays.sort(participant);
        Arrays.sort(completion);

        int completionLen = completion.length;

        for (int i = 0; i < completionLen; i++) {
            if (!participant[i].equals(completion[i])) {
                answer = participant[i];
            }
        }

        if (answer.length()==0) {
            answer = participant[participant.length - 1];
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] participant = {"mislav","stanko","mislav","ana"};
        String[] completion = {"ana","mislav","mislav"};

        System.out.println(solution(participant, completion));
    }
}
