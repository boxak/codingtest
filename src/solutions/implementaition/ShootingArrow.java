package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;

public class ShootingArrow {

    static int[] arr1;
    static int[] arr2;
    static ArrayList<int[]> list;

    public static int[] solution(int n, int[] info) {
        int[] answer = {};
        arr1 = new int[12];
        arr2 = info.clone();

        list = new ArrayList<>();

        int apeach_score = 0;

        for (int i = 0;i<=10;i++) {
            if (info[i]>0) {
                apeach_score+=10-i;
            }
        }

        dfs(n,0,0,0, apeach_score);

        if (list.isEmpty()) return new int[]{-1};

        Collections.sort(list, (o1, o2) -> {
           if (o1[11] == o2[11]) {
               for (int i = 10;i>=0;i--) {
                   if (o1[i]!=o2[i]) {
                       return o2[i] - o1[i];
                   }
               }
           } else return o2[11] - o1[11];

           return 0;
        });

//        for (int[] array : list) {
//            if (array[0]==array[1] && array[1]==array[2]
//            && array[2]==array[3] && array[3]==array[4] &&
//            array[4]==array[5] && array[5]==array[6] && array[6]==1) {
//                for (int num : array) {
//                    System.out.printf("%d ", num);
//                }
//                System.out.println();
//            }
//        }
//        System.out.println();

        answer = new int[11];

        for (int i = 0;i<=10;i++) answer[i] = list.get(0)[i];

        return answer;
    }

    public static void dfs(int n, int m, int x, int score1, int score2) {
        int sum = score1;
        for (int i = x+1;i<=10;i++) {
            sum+=10-i;
        }
        if (sum<=score2) return;

        if (x==11) {
            if (score1 > score2 && n==m) {
                arr1[11] = score1 - score2;
                list.add(arr1.clone());
            }
            return;
        }

        for (int i = 0;i<=n-m;i++) {
            arr1[x] = i;
            int score11 = score1;
            int score22 = score2;
            if (arr1[x] > arr2[x]) {
                score11+=10-x;
                if (arr2[x] > 0) score22-=10-x;
            }
            dfs(n,m+i,x+1,score11,score22);

        }


    }

    public static void main(String[] args) {
        int[] array = solution(9, new int[]{0,0,1,2,0,1,1,1,1,1,1});

        for (int num : array) {
            System.out.printf("%d ", num);
        }

    }
}
