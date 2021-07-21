package solutions.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class QuickSort {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    int[] arr = new int[N];

    for (int i = 0;i<N;i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    quickSort(arr,0,N-1);

    for (int i = 0;i<N;i++) {
      bw.write(String.valueOf(arr[i]));
      bw.newLine();
    }
    bw.flush();
    bw.close();
  }

  static void quickSort(int[] arr,int s,int e) {
    if (s>=e) return;
    int pivot = arr[s];
    int cntSmaller = 0;
    int cntBigger = 0;
    int[] less = new int[arr.length];
    int[] bigger = new int[arr.length];
    for (int i = s+1;i<=e;i++) {
      if (arr[i]<pivot) {
        less[cntSmaller] = arr[i];
        cntSmaller++;
      } else {
        bigger[cntBigger] = arr[i];
        cntBigger++;
      }
    }

    for (int i = s;i<s+cntSmaller;i++) {
      arr[i] = less[i-s];
    }

    arr[s+cntSmaller] = pivot;

    for (int i = s+cntSmaller+1;i<=e;i++) {
      arr[i] = bigger[i-s-cntSmaller-1];
    }

    quickSort(arr,s,s+cntSmaller-1);
    quickSort(arr,s+cntSmaller+1,e);
  }
}
