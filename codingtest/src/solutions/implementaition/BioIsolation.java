package solutions.implementaition;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//맵에서 뭔가를 옮길때는 cmap을 만들어서 원래 map에 영향을 안주도록 하자!
public class BioIsolation {

    static int N,M,K;

    static class Cell implements Comparable<Cell>{
        int r;
        int c;
        int num;
        int dir;

        Cell(int x,int y,int z,int w) {
            r = x;
            c = y;
            num = z;
            dir = w;
        }

        public int compareTo(Cell cell) {
            return cell.num - this.num;
        }
    }

    static ArrayList<Cell> cells;
    static int[][] map;
    static int[][] cmap;
    static int answer;
    static int[] dr = {0,-1,1,0,0};
    static int[] dc = {0,0,0,-1,1};

    public static void main(String args[]) throws IOException {
        //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Downloads\\sample_input (1).txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TestCnt = Integer.parseInt(br.readLine());
        for (int test = 1;test<=TestCnt;test++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cells = new ArrayList<>();
            map = new int[N][N];
            cmap = new int[N][N];
            answer = 0;
            for (int i = 0;i<K;i++) {
                str = br.readLine();
                st = new StringTokenizer(str," ");
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                cells.add(new Cell(r,c,num,dir));
            }

            for (int time = 0;time<M;time++) {
                copy();
                move();
                arrange();
            }

            for (Cell cell : cells) {
                answer+=cell.num;
            }
            System.out.println("#"+test+" "+answer);
        }
    }

    static void copy() {
        for (int i = 0;i<N;i++) {
            for (int j = 0;j<N;j++) {
                cmap[i][j] = map[i][j];
            }
        }
    }

    static void move() {
        for (int i = 0;i<cells.size();i++) {
            Cell cell = cells.get(i);
            int r = cell.r;
            int c = cell.c;
            int num = cell.num;
            int dir = cell.dir;

            int nr = r+dr[dir];
            int nc = c+dc[dir];
            if (nr==0 || nr==N-1 || nc==0 || nc==N-1) {
                num = num/2;
            }
            cmap[nr][nc] = cmap[nr][nc]+num;
            cells.set(i,new Cell(nr,nc,num,dir));
        }
    }

    static void arrange() {
        Collections.sort(cells);
        ArrayList<Cell> tempList = new ArrayList<>();

        for (Cell cell : cells) {
            int r = cell.r;
            int c = cell.c;
            int num = cell.num;
            int dir = cell.dir;

            if (cmap[r][c]==0) continue;

            if (r==0 || r==N-1 || c==0 || c==N-1) {
                if (dir==1) dir=2;
                else if (dir==2) dir=1;
                else if (dir==3) dir=4;
                else if (dir==4) dir=3;
            }
            tempList.add(new Cell(r,c,cmap[r][c],dir));
            cmap[r][c] = 0;
        }

        cells.clear();

        for (Cell cell : tempList) {
            Cell cell2 = new Cell(cell.r,cell.c,cell.num,cell.dir);
            cells.add(cell2);
        }

    }
}
