package solutions.implementaition;

import java.util.ArrayList;

public class EnglishEndingGame {
    public static int[] solution(int n, String[] words) {
        int[] answer = {};
        answer = new int[2];
        answer[0] = 0;
        answer[1] = 0;
        ArrayList<String> wordList = new ArrayList<>();
        int check[] = new int[n+1];
        check[1] = 1;
        wordList.add(words[0]);
        boolean isEnable = true;

        for (int i = 1;i<words.length;i++) {
            String word = words[i];
            String before = words[i-1];
            int player = (i%n) + 1;
            check[player]++;
            System.out.println(word+" "+player+" "+check[player]);
            if (word.charAt(0) != before.charAt(before.length()-1)) isEnable = false;
            if (wordList.contains(word)) isEnable = false;
            if (!isEnable) {
                answer[0] = player;
                answer[1] = check[player];
                break;
            }
            wordList.add(word);
        }

        return answer;
    }

    public static void main(String args[]) {
        int arr[] = solution(2,new String[]{
                "hello","one","even","never","now","world","draw"
        });
        System.out.println(arr[0]+" "+arr[1]);
    }
}
