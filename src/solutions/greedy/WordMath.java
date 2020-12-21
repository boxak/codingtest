package solutions.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WordMath {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<String> numberList = new ArrayList<>();
        int maxLen = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (str.length() > maxLen) {
                maxLen = str.length();
            }
            numberList.add(str);
        }

        char[][] numArray = new char[N][maxLen];

        for (int i = 0; i < N; i++) {
            String numberStr = numberList.get(i);
            int numLen = numberStr.length();
            for (int j = 0; j < maxLen; j++) {
                if (j < maxLen - numLen) {
                    numArray[i][j] = '.';
                } else {
                    numArray[i][j] = numberStr.charAt(j - (maxLen - numLen));
                }
            }
        }

        int alphabet[] = new int[26];

        for (int i = 0; i < 26; i++) {
            alphabet[i] = -1;
        }
        int maxNum = 9;

        for (int j = 0; j < maxLen; j++) {
            for (int i = 0; i < N; i++) {
                if (numArray[i][j] == '.') continue;
                else {
                    int inx = numArray[i][j] - 'A';
                    if (alphabet[inx] == -1) {
                        alphabet[inx] = maxNum;
                        numArray[i][j] = (char) ('0' + maxNum);
                        maxNum--;
                    } else {
                        numArray[i][j] = (char) ('0' + alphabet[inx]);
                    }
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            String str = "";
            for (int j = 0; j < maxLen; j++) {
                if (numArray[i][j]!='.') {
                    str+=numArray[i][j];
                }
            }
            answer+=Integer.parseInt(str);
        }

        System.out.println(answer);
    }
}
