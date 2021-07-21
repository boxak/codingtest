package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class SplitWords {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0;i<=word.length()-2;i++) {
            for (int j = i+1;j<word.length()-1;j++) {
                String first = word.substring(0,i+1);
                String second = word.substring(i+1,j+1);
                String third = word.substring(j+1);

                StringBuilder sb = new StringBuilder();

                for (int k = first.length()-1;k>=0;k--) {
                    sb.append(first.charAt(k));
                }

                for (int k = second.length()-1;k>=0;k--) {
                    sb.append(second.charAt(k));
                }

                for (int k = third.length()-1;k>=0;k--) {
                    sb.append(third.charAt(k));
                }
                list.add(sb.toString());
            }
        }

        Collections.sort(list);

        System.out.println(list.get(0));

    }
}
