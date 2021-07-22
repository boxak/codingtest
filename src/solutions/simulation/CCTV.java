package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCTV {
	static int N,M;
	static int[] arr;
	static int[][] map;
	static boolean checked[][];
	static ArrayList<Pair> cameras;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int answer;
	
	static class Pair {
		int r;
		int c;
		int num;
		Pair(int r,int c,int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		checked = new boolean[N][M];
		cameras = new ArrayList<>();
		
		for (int i = 0;i<N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]>=1 && map[i][j]<=5) {
					cameras.add(new Pair(i,j,map[i][j]));
				}
			}
		}
		
		int cameraCnt = cameras.size();
		arr = new int[cameraCnt];
		
		answer = Integer.MAX_VALUE;
		
		dfs(0,cameraCnt);
		
		System.out.println(answer);
		
	}
	
	static void dfs(int x,int n) {
		if (x==n) {
			simulation();
			return;
		}
		for (int i = 0;i<4;i++) {
			arr[x] = i;
			dfs(x+1,n);
			arr[x] = 0;
		}
	}
	
	static void simulation() {
		
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<M;j++) {
				checked[i][j] = false;
			}
		}
		
		for (int i = 0;i<cameras.size();i++) {
			Pair pair  = cameras.get(i);
			int r = pair.r;
			int c = pair.c;
			int num = pair.num;
			checkCamera(r,c,num,arr[i]);
		}
		
		int cnt = 0;
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<M;j++) {
				if (map[i][j]==0 && !checked[i][j]) {
					cnt++;
				}
			}
		}
		if (cnt<answer) {
			answer = cnt;
		}
	}
	
	static void checkCamera(int r,int c,int num,int d) {
		
		if (num==1) {
			for (int go = 1;go<=8;go++) {
				int nr = r+go*dr[d];
				int nc = c+go*dc[d];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
		}
		if (num==2) {
			for (int go = 1;go<=8;go++) {
				int nr = r+go*dr[d];
				int nc = c+go*dc[d];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
			for (int go = 1;go<=N;go++) {
				int nr = r+go*dr[(d+2)%4];
				int nc = c+go*dc[(d+2)%4];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
		}
		if (num==3) {
			for (int go = 1;go<=8;go++) {
				int nr = r+go*dr[d];
				int nc = c+go*dc[d];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
			for (int go = 1;go<=8;go++) {
				int nr = r+go*dr[(d+1)%4];
				int nc = c+go*dc[(d+1)%4];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
		}
		if (num==4) {
			for (int go = 1;go<=8;go++) {
				int nr = r+go*dr[d];
				int nc = c+go*dc[d];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
			for (int go = 1;go<=8;go++) {
				int nr = r+go*dr[(d+1)%4];
				int nc = c+go*dc[(d+1)%4];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
			for (int go = 1;go<=8;go++) {
				int nr = r+go*dr[(d+2)%4];
				int nc = c+go*dc[(d+2)%4];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
		}
		if (num==5) {
			for (int go = 1;go<=8;go++) {
				int nr = r+go*dr[d];
				int nc = c+go*dc[d];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
			for (int go = 1;go<=8;go++) {
				int nr = r+go*dr[(d+1)%4];
				int nc = c+go*dc[(d+1)%4];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
			for (int go = 1;go<=8;go++) {
				int nr = r+go*dr[(d+2)%4];
				int nc = c+go*dc[(d+2)%4];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
			for (int go = 1;go<=8;go++) {
				int nr = r+go*dr[(d+3)%4];
				int nc = c+go*dc[(d+3)%4];
				if (isOut(nr,nc)) break;
				checked[nr][nc] = true;
			}
		}
		
	}
	
	static boolean isOut(int r,int c) {
		boolean flag = false;
		if (r<0 || r>=N || c<0 || c>=M) {
			flag = true;
		} else {
			if (map[r][c]==6) {
				flag = true;
			}
		}
		return flag;
	}
}
