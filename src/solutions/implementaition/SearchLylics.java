package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;

public class SearchLylics {
    public static int[] solution(String[] words, String[] queries) {
        int[] answer = {};



        return answer;
    }

    public static void main(String[] args) {
        String s1 = "fro??";
        String s2 = "frod?";

        ArrayList<String> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);

        System.out.println("starts : " + s2.startsWith(""));

        Collections.sort(list);

        System.out.println(list);

        for(String str : list) {
            System.out.println(str);
        }

//        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
//        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
//
//        int[] results = solution(words, queries);
//
//        for(int num : results) {
//            System.out.printf("%d ",num);
//        }
    }
}
