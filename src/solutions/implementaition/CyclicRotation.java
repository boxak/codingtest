package solutions.implementaition;

import java.util.Collections;

public class CyclicRotation {
    public int[] solution(int[] A, int K) {
        if (A.length == 0) return A;
        for (int i = 0; i<K;i++) {
            A = rotate(A);
        }
        return A;
    }

    int[] rotate(int[] A) {
        int[] newArr = new int[A.length];
        for (int i = 1;i<A.length;i++) {
            newArr[i] = A[i-1];
        }
        newArr[0] = A[A.length - 1];

        return newArr;
    }
}
