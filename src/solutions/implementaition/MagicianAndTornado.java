package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MagicianAndTornado {
	static int N;
	static int[][] map;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {-1,0,1,0};
	
	static int[] dr2 = {0,0,1,1,1,2,-1,-1,-1,-2};
	static int[] dc2 = {-1,-2,-1,0,1,0,1,0,-1,0};
	static double[] rates = {0,0.05,0.1,0.07,0.01,0.02,0.01,0.07,0.1,0.02};
	static int[][] tempMap = new int[6][6];
	static int[][] tempMap2 = new int[6][6];
	
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for (int i = 1;i<=N;i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			for (int j = 1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		simulation();
		
		System.out.println(answer);
	}

	static void simulation() {
		int r = N/2+1;
		int c = N/2+1;
		int d = 0;
		int goCount = 0;
		int goCountLimit = 1;
		int changeDir = 0;
		while(r!=1 || c!=1) {
			r = r + dr[d];
			c = c + dc[d];
			moveSand(r,c,d);
			goCount++;
			if (goCountLimit==goCount) {
				goCount = 0;
				d = (d+1)%4;
				if (changeDir==0) {
					changeDir++;
				} else if (changeDir==1){
					changeDir = 0;
					goCountLimit++;
				}
			}
		}
	}
	
	static void moveSand(int r,int c,int d) {
		int initSand = map[r][c];
		int sandSum = 0;
		
		for (int i=0;i<=5;i++) {
			for (int j = 0;j<=5;j++) {
				tempMap[i][j] = 0;
				tempMap2[i][j] = 0;
			}
		}
		
		int tempR = r - (r-3);
		int tempC = c - (c-3);
		
		for (int i = 1;i<=9;i++) {
			double rate = rates[i];
			int nr = tempR + dr2[i];
			int nc = tempC + dc2[i];
			int movesands = (int)(rate*initSand);

			tempMap[nr][nc]=movesands;
			sandSum+=movesands;
		}
		int nr = tempR+dr2[0];
		int nc = tempC+dc2[0];
		
		int movesands = initSand - sandSum;

		tempMap[nr][nc] = movesands;
		
		for (int dd=0;dd<d;dd++) {
			for (int i = 1;i<=5;i++) {
				for (int j = 1;j<=5;j++) {
					tempMap2[5+1-j][i] = tempMap[i][j];
				}
			}
			
			for (int i = 1;i<=5;i++) {
				for (int j = 1;j<=5;j++) {
					tempMap[i][j] = tempMap2[i][j];
				}
			}
		}
		
		for (int i = 1;i<=5;i++) {
			for (int j = 1;j<=5;j++) {
				if(i+r-3<1 || i+r-3>N || j+c-3<1 || j+c-3>N) {
					answer+=tempMap[i][j];
				} else {
					map[i+r-3][j+c-3]+=tempMap[i][j];
				}
			}
		}
		
		map[r][c] = 0;
		
	}
}
