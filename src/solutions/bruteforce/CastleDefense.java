package solutions.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CastleDefense {
	static int N,M,D;
	static int[][] map;
	static int[][] cmap;
	static int answer;
	static int[] temp;
	static int[][] check;
	static boolean[] find;
	static int enemyCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		cmap = new int[N+1][M+1];
		answer = 0;
		temp = new int[3];
		find = new boolean[3];
		check = new int[3][2];
		
		for (int i = 1;i<=N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==1) enemyCnt++;
			}
		}
		
		for (int i = 1;i<=M;i++) {
			for (int j = i+1;j<=M;j++) {
				for (int k = j+1;k<=M;k++) {
					simulation(i,j,k);
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	static void simulation(int c1,int c2,int c3) {
		temp[0] = c1;
		temp[1] = c2;
		temp[2] = c3;
		int cnt = 0;
		int tempEnemyCnt = enemyCnt;
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=M;j++) {
				cmap[i][j] = map[i][j];
			}
		}
		
		
		while(tempEnemyCnt>0) {
		
			for (int i = 0;i<3;i++) {
				check[i][0] = 0;
				check[i][1] = 0;
			}
			
			find[0] = false;
			find[1] = false;
			find[2] = false;
			
			for (int i = 0;i<3;i++) {
				int r = N+1;
				int c = temp[i];
				int minValue = Integer.MAX_VALUE;
				int minR = -1;
				int minC = -1;
				
				for (int j = 1;j<=M;j++) {
					for (int k = N;k>=1;k--) {
						if (cmap[k][j]==1) {
							int dist = distance(r,c,k,j);
							if (dist<minValue && dist<=D) {
								minValue = dist;
								minR = k;
								minC = j;
								find[i] = true;
							}
						}
					}
				}
				
				if (find[i]) {
					check[i][0] = minR;
					check[i][1] = minC;
				}
				
			}
			
			for (int i = 0;i<3;i++) {
				if (find[i]) {
					int r = check[i][0];
					int c = check[i][1];
					if (cmap[r][c]==1) {
						cmap[r][c] = 0;
						cnt++;
						tempEnemyCnt--;
					}
				}
			}
			
			for (int j = 1;j<=M;j++) {
				if (cmap[N][j]==1) tempEnemyCnt--;
			}
			
			
			for (int i=N;i>=2;i--) {
				for (int j = 1;j<=M;j++) {
					cmap[i][j] = cmap[i-1][j];
				}
			}
			
			for (int j = 1;j<=M;j++) cmap[1][j] = 0;
		}
		
		if (cnt>answer) answer = cnt;
		
	}
	
	static int distance(int r1,int c1,int r2,int c2) {
		return Math.abs(r2-r1)+Math.abs(c2-c1);
	}
}
