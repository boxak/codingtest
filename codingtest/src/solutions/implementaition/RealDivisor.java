package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RealDivisor {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String str = br.readLine();
        String arr[] = str.split(" ");
        int length = arr.length;

        int arr2[] = new int[length];

        for (int i = 0;i<length;i++) {
            arr2[i] = Integer.parseInt(arr[i]);
        }

        Arrays.sort(arr2);

        System.out.println(arr2[0]*arr2[length-1]);
    }
}
