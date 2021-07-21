package solutions.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SearchMiro {

    static class Pair {
        int r;
        int c;
        int cost;
        Pair(int x,int y,int z) {
            r = x;
            c = y;
            cost = z;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int miro[][] = new int[N+1][M+1];

        for (int i = 1;i<=N;i++) {
            str = br.readLine();
            for (int j = 1;j<=M;j++) {
                miro[i][j] = str.charAt(j-1)-'0';
            }
        }

        boolean visited[][] = new boolean[N+1][M+1];

        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(1,1,1));
        visited[1][1] = true;
        boolean flag = false;

        int dr[] = {-1,0,1,0};
        int dc[] = {0,1,0,-1};

        int answer = 0;

        while(true) {
            int r = que.peek().r;
            int c = que.peek().c;
            int cost = que.peek().cost;
            if (r==N && c==M) {
                answer = cost;
                break;
            }
            que.poll();
            for (int i = 0;i<4;i++) {
                int nr = r+dr[i];
                int nc = c+dc[i];
                if (nr<1 || nr>N || nc<1 || nc>M) continue;
                if (miro[nr][nc]==1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    que.add(new Pair(nr,nc,cost+1));
                }
            }
        }
        System.out.println(answer);

    }
}
