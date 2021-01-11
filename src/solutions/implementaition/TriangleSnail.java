package solutions.implementaition;

public class TriangleSnail {
    public static int[] solution(int n) {
        int[] answer = {};
        answer = new int[n * (n + 1) / 2];

        int[][] arr = new int[n][];

        for (int i = 0; i < n; i++) {
            arr[i] = new int[i + 1];
        }

        int dr[] = {1, 0, -1};
        int dc[] = {0, 1, -1};

        int numberCnt = n * (n + 1) / 2;
        int nowNum = 1;
        int r = 0;
        int c = 0;
        int dir = 0;

        for (int i = 0; i < numberCnt; i++) {
            arr[r][c] = nowNum++;
            int nr = r+dr[dir];
            int nc = c+dc[dir];
            if (nr<0 || nr>=n || nc<0 || nc>=n || nc>nr) {
                dir=(dir+1)%3;
                nr = r+dr[dir];
                nc = c+dc[dir];
            } else if (arr[nr][nc]!=0) {
                dir=(dir+1)%3;
                nr = r+dr[dir];
                nc = c+dc[dir];
            }
            r = nr;
            c = nc;
        }

        int inx = 0;
        for (int i = 0;i<n;i++){
            int colLen = arr[i].length;
            for (int j =0;j<colLen;j++) {
                answer[inx++] = arr[i][j];
            }
        }

        return answer;
    }

    public static void main(String args[]) {
        int[] arr = solution(6);
        for (int num : arr){
            System.out.printf("%d ",num);
        }
    }
}
