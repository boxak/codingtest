package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class CultivatingStemCells {

    static int N,M,K;
    static int[][] cells = new int[1000][1000];
    static ArrayList<Cell> cellList;
    static ArrayList<Cell> bornList;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    static class Cell implements Comparable<Cell>{
        int r;
        int c;
        int life;
        int age = 0;
        boolean isAlive = true;
        boolean isActivate = false;

        Cell(int r,int c,int life) {
            this.r = r;
            this.c = c;
            this.life = life;
        }

        @Override
        public int compareTo(Cell cell) {
            return cell.life-this.life;
        }

        void activate() {
            age = 0;
            isActivate = true;
        }

        void die() {
            isAlive = false;
        }

        void plusAge() {
            age++;
        }
    }

    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Administrator\\Downloads\\sample_input (6).txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TestCnt = Integer.parseInt(br.readLine());

        for (int test = 1;test<=TestCnt;test++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cellList = new ArrayList<>();
            bornList = new ArrayList<>();

            for (int i = 0;i<1000;i++) {
                for (int j = 0;j<1000;j++) {
                    cells[i][j] = 0;
                }
            }

            for (int i = 0;i<N;i++) {
                str = br.readLine();
                st = new StringTokenizer(str," ");
                for (int j = 0;j<M;j++) {
                    cells[500+i][500+j] = Integer.parseInt(st.nextToken());
                    if (cells[500+i][500+j]>0) {
                        cellList.add(new Cell(500 + i, 500 + j, cells[500 + i][500 + j]));
                    }
                }
            }

            for (int time = 0;time<K;time++) {
                simulation();
            }

            int answer = 0;

            for (Cell cell : cellList) {
                if (cell.isAlive) answer++;
            }

            System.out.println("#"+test+" "+answer);
        }

    }

    static void simulation() {
        for (Cell cell : cellList) {
            if (!cell.isAlive) continue;

            if (!cell.isActivate) {
                //1. 비활성 상태이면서 age < life
                if (cell.age < cell.life) {
                    cell.plusAge();
                }

                //2. 비활성 상태이면서 age = life
                if (cell.age == cell.life) {
                    cell.activate();
                }
            } else {
                //3. 활성 상태이면서 age < life
                if (cell.age < cell.life) {
                    cell.plusAge();
                }
                //4. 활성 상태이면서 age = 1
                if (cell.age == 1) {
                    makeCells(cell.r, cell.c);
                }

                //5. 활성 상태이면서 age = life
                if (cell.age == cell.life) {
                    cell.die();
                    cells[cell.r][cell.c] = -1;
                }
            }
        }

        //죽은 세포는 cellList에서 제외.
        ArrayList<Cell> tempList = new ArrayList<>();

        for (Cell cell : cellList) {
            if (cell.isAlive) tempList.add(cell);
        }

        cellList.clear();

        for (Cell cell : tempList) cellList.add(cell);

        // 새로 태어나는 세포들을  cellList에 삽입.
        for (Cell cell : bornList) cellList.add(cell);

        bornList.clear();

        Collections.sort(cellList);
    }

    static void makeCells(int r,int c) {
        for (int d = 0;d<4;d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];
            if (cells[nr][nc]==0) {
                bornList.add(new Cell(nr,nc,cells[r][c]));
                cells[nr][nc] = cells[r][c];
            }
        }
    }
}
