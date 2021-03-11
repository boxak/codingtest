package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CutLan {

    static int[] lenArr;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        lenArr = new int[K];

        for (int i = 0;i<K;i++) {
            lenArr[i] = Integer.parseInt(br.readLine());
        }

        long answer = binarySearch(N,1,(long)Integer.MAX_VALUE+1);
        System.out.println(answer);
    }

    static long binarySearch(int N,long s,long e) {
        if (e==s+1) {
            return s;
        }
        long mid = (e+s)/2;
        long num = check(mid);
        if (num >= N) return binarySearch(N,mid,e);
        else return binarySearch(N,s,mid);
    }

    static long check(long x) {
        long cnt = 0;
        for (int len : lenArr) {
            cnt+=(long)len/x;
        }
        return cnt;
    }
}
