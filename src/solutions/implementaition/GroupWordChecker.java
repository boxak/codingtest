package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GroupWordChecker {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int wordInx = 0;wordInx<N;wordInx++) {
            String word = br.readLine();
            boolean isGroup = true;
            for (int i = 0;i<26;i++) {
                char letter = (char)('a'+i);
                int lastInx = -1;
                for (int j = 0;j<word.length();j++) {
                    if (word.charAt(j)==letter) {
                        if (lastInx>=0 && j>lastInx+1) {
                            isGroup = false;
                            break;
                        }
                        lastInx = j;
                    }
                }
                if (!isGroup) break;
            }
            if (isGroup) answer++;
        }
        System.out.println(answer);
    }
}
