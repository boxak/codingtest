package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SearchLylics {

    static class Node {
        Map<Character, Node> childNode = new HashMap<>();
        boolean isEnd;
        int cnt;
    }

    static class Trie {
        Node root = new Node();

        void insert(String s) {

            Node node = this.root;

            for (char c : s.toCharArray()) {
                if (node.childNode.containsKey(c)) {
                    node = node.childNode.get(c);
                } else {
                    node.childNode.put(c, new Node());
                    node.cnt++;
                    node = node.childNode.get(c);
                }
            }

            node.isEnd = true;
        }

        int search(String s) {
            Node node = this.root;

            for (char c : s.toCharArray()) {
                node = node.childNode.getOrDefault(c, null);
                if (node == null) {
                    return node.cnt;
                }
            }

            return 0;
        }

    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = {};

        Trie[] tries = new Trie[10001];

        for (int i = 0;i< tries.length;i++) {
            tries[i] = new Trie();
        }



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

        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        int[] results = solution(words, queries);

        for(int num : results) {
            System.out.printf("%d ",num);
        }
    }
}
