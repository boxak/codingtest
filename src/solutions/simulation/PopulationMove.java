package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PopulationMove {
	static int N,lowerLimit,upperLimit;
	static int[][] map;
	static int[][] cmap;
	static boolean[][] visited;
	static boolean[][] visited2;
	static int answer;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static int peopleSum,nationCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		N = Integer.parseInt(st.nextToken());
		lowerLimit = Integer.parseInt(st.nextToken());
		upperLimit = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		cmap = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		visited2 = new boolean[N+1][N+1];
		answer = 0;
		
		for (int i = 1;i<=N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			boolean continuable = move();
			if (!continuable) break;
			answer++;
		}
		
		
		System.out.println(answer);
		
	}
	
	static boolean move() {
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				visited[i][j] = false;
				visited2[i][j] = false;
				cmap[i][j] = 0;
			}
		}
		
		boolean continuable = false;
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				if (!visited[i][j]) {
					peopleSum = 0;
					nationCnt = 0;
					dfs(i,j);
					if (nationCnt>1) continuable = true;
					dfs2(i,j);
					
				}
			}
		}
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				map[i][j] = cmap[i][j];
			}
		}
		
		return continuable;
	}
	
	static void dfs(int r,int c) {
		visited[r][c] = true;
		nationCnt++;
		peopleSum+=map[r][c];
		for (int d = 0;d<4;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if (isOut(nr,nc)) continue;
			if (!visited[nr][nc] && isOpen(r,c,nr,nc)) {
				dfs(nr,nc);
			}
		}
	}
	
	static void dfs2(int r,int c) {
		visited2[r][c] = true;
		cmap[r][c] = peopleSum/nationCnt;
		for (int d = 0;d<4;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if (isOut(nr,nc)) continue;
			if (!visited2[nr][nc] && isOpen(r,c,nr,nc)) {
				dfs2(nr,nc);
			}
		}
	}
	
	static boolean isOut(int r,int c) {
		return r<1 || r>N || c<1 || c>N;
	}
	
	static boolean isOpen(int r1,int c1,int r2,int c2) {
		return Math.abs(map[r1][c1] - map[r2][c2])>=lowerLimit && Math.abs(map[r1][c1]-map[r2][c2])<=upperLimit;
	}
	
}
