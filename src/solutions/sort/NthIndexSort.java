package solutions.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NthIndexSort {
    public String[] solution(String[] strings, int n) {
        String[] answer = {};
        answer = new String[strings.length];
        int arrLen = strings.length;
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            char pivot = (char) ('a' + i);
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < arrLen; j++) {
                if (strings[j].charAt(n) == pivot) {
                    temp.add(strings[j]);
                }
            }
            if (!temp.isEmpty()) {
                Collections.sort(temp);
                for (String str : temp) {
                    list.add(str);
                }
            }
        }

        for (int i = 0; i < arrLen; i++) {
            answer[i] = list.get(i);
        }

        answer = strings;

        return answer;
    }

    public String[] solution2(String[] strings, int n) {
        String[] answer = {};
        int arrLen = strings.length;

        for (int i = 0; i < arrLen; i++) {
            for (int j = 0; j < arrLen - 1; j++) {
                String current = strings[j];
                String after = strings[j+1];
                if (current.charAt(n)>after.charAt(n)) {
                    strings[j] = after;
                    strings[j+1] = current;
                } else if(current.charAt(n)==after.charAt(n)) {
                    String[] temp = new String[2];
                    temp[0] = current;
                    temp[1] = after;
                    Arrays.sort(temp);
                    if (!temp[0].equals(current)) {
                        strings[j] = after;
                        strings[j+1] = current;
                    }
                }
            }
        }

        return answer;
    }
}
