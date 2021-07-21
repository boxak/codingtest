package solutions.sort;

import java.io.*;

public class MergeSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(arr,0,N-1);

        for (int i = 0;i<N;i++) {
            bw.write(String.valueOf(arr[i]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static void mergeSort(int[] arr,int s,int e) {
        if (s==e) return;

        int mid = (s+e)/2;

        mergeSort(arr,s,mid);
        mergeSort(arr,mid+1,e);
        merge(arr,s,mid,mid+1,e);
    }

    static void merge(int[] arr,int p1,int q1,int p2,int q2) {
        int[] temp = new int[q2-p1+1];
        int inx = 0;
        int p11 = p1;
        int p22 = p2;

        while(p1<=q1 && p2<=q2) {
            if (arr[p1]<arr[p2]) {
                temp[inx] = arr[p1];
                inx++;
                p1++;
            } else {
                temp[inx] = arr[p2];
                inx++;
                p2++;
            }
        }

        while(p1<=q1) {
            temp[inx] = arr[p1];
            inx++;
            p1++;
        }

        while(p2<=q2) {
            temp[inx] = arr[p2];
            inx++;
            p2++;
        }

        for (int i = p11;i<=q2;i++) {
            arr[i] = temp[i-p11];
        }
    }

}
