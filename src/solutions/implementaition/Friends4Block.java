package solutions.implementaition;

public class Friends4Block {
    public static int solution(int m, int n, String[] board) {
        int answer = 0;

        int[][] map = new int[40][40];
        for (int i = 0; i < m; i++) {
            String str = board[i];
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - 'A' + 1;
            }
        }

        boolean[][] check = new boolean[40][40];

        while (true) {

            for (int i = 0;i<m;i++) {
                for (int j = 0;j<n;j++) {
                    System.out.printf("%d ",map[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            boolean flag = false;
            //2x2로 같은 블럭이 있는지 체크
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    int num1 = map[i][j];
                    int num2 = map[i][j + 1];
                    int num3 = map[i + 1][j];
                    int num4 = map[i + 1][j + 1];
                    if (num1 == num2 && num2 == num3 && num3 == num4 && num1!=0) {
                        check[i][j] = true;
                        check[i][j + 1] = true;
                        check[i + 1][j] = true;
                        check[i + 1][j + 1] = true;
                        flag = true;
                    }
                }
            }
            if (!flag) break;
            //체크되어 있는 블록 0으로 만들기
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (check[i][j]) {
                        answer++;
                        map[i][j] = 0;
                        check[i][j] = false;
                    }
                }
            }

            for (int i = 0;i<m;i++) {
                for (int j = 0;j<n;j++) {
                    System.out.printf("%d ",map[i][j]);
                }
                System.out.println();
            }
            System.out.println();

            //0으로 된 부분 메꾸기
            for (int i = m - 2; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j]!=0) {
                        int k = i+1;
                        boolean flag2 = false;
                        if (map[k][j] == 0) {
                            //System.out.println(i+" "+j+" "+k+ " "+j);
                            for (;k<m;k++) {
                                if (map[k][j]!=0) {
                                    map[k-1][j] = map[i][j];
                                    map[i][j] = 0;
                                    flag2 = true;
                                    break;
                                }
                            }
                            if (!flag2) {
                                map[m-1][j] = map[i][j];
                                map[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(6,6,new String[]{"TTTANT","RRFACC","RRRFCC","TRRRAA",
                "TTMMMF","TMMTTJ"}));
    }
}
