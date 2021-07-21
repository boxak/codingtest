package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class SortWord {

    static class Word implements Comparable<Word>{
        String word;
        Word(String word) {
            this.word = word;
        }

        public int compareTo(Word w) {
            if (w.word.length() == this.word.length()) {
                return this.word.compareTo(w.word);
            } else {
                return this.word.length() - w.word.length();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Word> list = new ArrayList<Word>();

        for (int i = 0;i<N;i++) {
            String str = br.readLine();
            list.add(new Word(str));
        }

        Collections.sort(list);
        System.out.println(list.get(0).word);
        for (int i = 1;i<list.size();i++) {
            if (!list.get(i).word.equals(list.get(i-1).word)) {
                System.out.println(list.get(i).word);
            }
        }

    }
}
