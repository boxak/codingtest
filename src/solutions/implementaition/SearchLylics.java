package solutions.implementaition;

import java.util.HashMap;
import java.util.Map;

public class SearchLylics {

    class Node {
        Map<Character, Node> childNode = new HashMap<>();
        boolean isEnd;
        int cnt;
    }

    class Trie {
        Node root = new Node();

        void insert(String s) {

            Node node = this.root;

            for (char c : s.toCharArray()) {
                node.cnt++;
                node = node.childNode.computeIfAbsent(c, key -> new Node());
            }
            node.cnt++;
            node.isEnd = true;
        }

        int search(String s) {
            Node node = this.root;

            for (char c : s.toCharArray()) {
                if (c=='?') return node.cnt;
                node = node.childNode.getOrDefault(c, null);
                if (node == null) {
                    return 0;
                }
            }

            return node.cnt;
        }

    }

    Trie[] tries = new Trie[10001];
    Trie[] reverse = new Trie[10001];

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        for (int i = 0;i< tries.length;i++) {
            tries[i] = new Trie();
            reverse[i] = new Trie();
        }

        for (int i = 0;i<words.length;i++) {
            String word = words[i];
            String reverse_word = new StringBuffer(word).reverse().toString();
            int wordLen = word.length();
            tries[wordLen].insert(word);
            reverse[wordLen].insert(reverse_word);
        }


        for (int i = 0;i<queries.length;i++) {
            int len = queries[i].length();
            if (queries[i].charAt(len-1) == '?') {
                answer[i] = tries[len].search(queries[i]);
            } else {
                String newQuery = new StringBuffer(queries[i]).reverse().toString();
                answer[i] = reverse[len].search(newQuery);
            }
        }

        return answer;
    }

}
