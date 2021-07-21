package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Treasure {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int A[] = new int[N];
        int B[] = new int[N];

        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        for (int i = 0;i<N;i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        str = br.readLine();
        st = new StringTokenizer(str, " ");
        for (int i = 0;i<N;i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);
        int answer = 0;

        for (int i = 0;i<N;i++) {
            answer+=A[i]*B[N-1-i];
        }

        System.out.println(answer);

    }
}
