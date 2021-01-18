package solutions.implementaition;

import java.util.ArrayList;
import java.util.HashMap;

public class LZWAlgorithm {
    public static int[] solution(String msg) {
        int[] answer = {};
        ArrayList<Integer> indexList = new ArrayList<>();

        HashMap<String, Integer> dict = new HashMap<>();

        //initialize
        for (int i = 0; i < 26; i++) {
            dict.put(String.valueOf((char)('A' + i)), i+1);
        }

        String str = msg;

        while (!str.isEmpty()) {
            String tmp = "";
            for (int i = str.length(); i >= 1; i--) {
                String subStr = str.substring(0, i);
                if (dict.containsKey(subStr)) {
                    indexList.add(dict.get(subStr));
                    str = str.replaceFirst(subStr, "");
                    if (!str.isEmpty()) {
                        int size = dict.size();
                        dict.put(subStr + str.charAt(0), size + 1);
                        System.out.println("print : "+
                                subStr+", add : "+subStr+str.charAt(0));
                    }
                    break;
                }
            }
        }

        answer = new int[indexList.size()];
        for (int i = 0;i<indexList.size();i++) {
            answer[i] = indexList.get(i);
        }

        return answer;
    }

    public static void main(String args[]) {
        solution("ABABABABABABABAB");
    }
}
