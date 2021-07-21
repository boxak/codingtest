package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SequenceSort {

    static class Pair implements Comparable<Pair>{
        int num;
        int inx;
        Pair(int x,int y) {
            num = x;
            inx = y;
        }

        public int compareTo(Pair pair) {
            return this.num - pair.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int A[] = new int[N];
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        for (int i = 0;i<N;i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Pair> list = new ArrayList<>();

        for (int i = 0;i<N;i++) {
            list.add(new Pair(A[i],i));
        }

        Collections.sort(list);

        int inx = 0;
        int P[] = new int[N];
        for (int i = 0;i<N;i++) {
            P[list.get(i).inx] = inx;
            inx++;
        }

        for (int num : P) {
            System.out.printf("%d ",num);
        }

    }
}
