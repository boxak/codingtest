package solutions.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class WordMath {

    static class Pair implements Comparable<Pair> {
        char alphabet;
        int num;

        Pair(char a, int b) {
            alphabet = a;
            num = b;
        }

        public int compareTo(Pair pair) {
            return this.num - pair.num;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<String> numberList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            numberList.add(str);
        }

        int alphabet[] = new int[26];

        for (int i = 0; i < N; i++) {
            String numberStr = numberList.get(i);
            int numLen = numberStr.length();
            for (int j = 0; j < numLen; j++) {
                char c = numberStr.charAt(j);
                int inx = c - 'A';
                int num = getPower(10, numLen - j - 1);
                alphabet[inx] += num;
            }
        }

        ArrayList<Pair> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] != 0) {
                list.add(new Pair((char) (i + 'A'), alphabet[i]));
            }
        }

        Collections.sort(list);

        int maxNum = 9;
        int listSize = list.size();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = listSize-1; i >=0; i--) {
            map.put(list.get(i).alphabet, maxNum);
            maxNum--;
        }

        ArrayList<Integer> numList = new ArrayList<>();
        int answer = 0;
        for (int i = 0; i < N; i++) {
            String str = "";
            String num1 = numberList.get(i);
            int numLen = num1.length();
            for (int j = 0; j < numLen; j++) {
                str+=map.get(num1.charAt(j));
            }
            answer+=Integer.parseInt(str);
        }
        System.out.println(answer);
    }

    static int getPower(int a, int b) {
        if (b == 0) return 1;
        else return a * getPower(a, b - 1);
    }
}
