package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JosephProblem {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N,K;
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1;i<=N;i++) {
            list.add(i);
        }

        int inx = 0;

        ArrayList<Integer> removed = new ArrayList<>();

        while(!list.isEmpty()) {
            inx+=K-1;
            if (inx>=list.size()) {
                inx = inx%list.size();
            }
            removed.add(list.get(inx));
            list.remove(inx);
            if (inx >= list.size()) {
                inx = 0;
            }
        }
        System.out.print("<");
        for (int i = 0;i<removed.size()-1;i++) {
            System.out.printf("%d, ",removed.get(i));
        }
        System.out.printf("%d>",removed.get(removed.size()-1));
    }
}
