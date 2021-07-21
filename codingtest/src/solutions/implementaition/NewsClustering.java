package solutions.implementaition;

import java.util.HashMap;

public class NewsClustering {
    public static int solution(String str1, String str2) {
        int answer = 0;

        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            String tmp1 = str1.substring(i, i + 2);
            char c1 = tmp1.charAt(0);
            char c2 = tmp1.charAt(1);
            if (Character.isAlphabetic(c1) && Character.isAlphabetic(c2)) {
                tmp1 = tmp1.toLowerCase();
                if (map1.containsKey(tmp1)) {
                    int cnt1 = map1.get(tmp1);
                    map1.put(tmp1, cnt1 + 1);
                } else map1.put(tmp1, 1);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            String tmp2 = str2.substring(i, i + 2);
            char c1 = tmp2.charAt(0);
            char c2 = tmp2.charAt(1);
            if (Character.isAlphabetic(c1) && Character.isAlphabetic(c2)) {
                tmp2 = tmp2.toLowerCase();
                if (map2.containsKey(tmp2)) {
                    int cnt2 = map2.get(tmp2);
                    map2.put(tmp2, cnt2 + 1);
                } else map2.put(tmp2, 1);
            }
        }

        HashMap<String, Integer> union = new HashMap<>();
        HashMap<String, Integer> intersection = new HashMap<>();

        for (String str : map1.keySet()) {
            int cnt1 = map1.get(str);
            union.put(str, cnt1);
        }

        for (String str : map2.keySet()) {
            int cnt2 = map2.get(str);
            int cnt1 = map1.containsKey(str) ? map1.get(str) : 0;
            union.put(str, Math.max(cnt1, cnt2));
            if (cnt1 != 0) {
                intersection.put(str, Math.min(cnt1, cnt2));
            }
        }

        double similarity = 0;
        if (!map1.isEmpty() || !map2.isEmpty()) {
            int unionSize = 0;
            int interSize = 0;
            for (String str : union.keySet()) {
                unionSize+=union.get(str);
                System.out.println("union : "+str+" "+union.get(str));
            }
            for (String str : intersection.keySet()) {
                System.out.println("intersection : "+str+" "+intersection.get(str));
                interSize+=intersection.get(str);
            }
            similarity = ((double)interSize/unionSize);
        } else {
            similarity = 1.0;
        }

        answer = (int) (65536 * similarity);

        return answer;
    }

    public static void main(String args[]) {
        int answer = solution("FRANCE", "french");
        System.out.println("answer : " + answer);
    }
}
