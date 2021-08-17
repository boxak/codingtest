package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ConnectProcessor {
	static int N;
	static int[][] map;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static int[] answers;
	static ArrayList<Core> cores;
	static int answer;
	static int length;
	static int maxCnt;
	static boolean drawable;
	
	static class Core {
		int r;
		int c;
		Core(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TestCnt = Integer.parseInt(br.readLine());
		answers = new int[TestCnt+1];
		
		for (int test = 1;test<=TestCnt;test++) {
		
			N = Integer.parseInt(br.readLine());
			map = new int[N+1][N+1];
			cores = new ArrayList<>();
			answer = 0;
			length = Integer.MAX_VALUE;
			maxCnt = 0;
			
			for (int i = 1;i<=N;i++) {
				String str = br.readLine();
				StringTokenizer st = new StringTokenizer(str," ");
				for (int j = 1;j<=N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j]==1) {
						cores.add(new Core(i,j));
					}
				}
			}
			
			dfs(0,0,0);
			
			answers[test] = length;
		
		}
		
		for (int i = 1;i<=TestCnt;i++) {
			System.out.println("#"+i+" "+answers[i]);
		}
		
	}
	
	static void dfs(int x,int cnt,int sum) {
		if (cores.size()-x+cnt<maxCnt) return;
		if (x==cores.size()) {
			if (cnt>maxCnt) {
				maxCnt = cnt;
				length = sum;
			} else if (cnt==maxCnt) {
				if (length>sum) length = sum;
			}
			return;
		}
		
		int r = cores.get(x).r;
		int c = cores.get(x).c;
		
		for (int d = 0;d<4;d++) {
			drawable = true;
			int go = draw(r,c,d);
			if(drawable) {
				dfs(x+1,cnt+1,sum+go);
			}
			erase(r,c,d,go);
		}
		dfs(x+1,cnt,sum);
		
	}
	
	static void erase(int r,int c,int d,int go) {
		for (int i = 1;i<=go;i++) {
			int nr = r+i*dr[d];
			int nc = c+i*dc[d];
			map[nr][nc] = 0;
		}
	}
	
	static int draw(int r,int c,int d) {
		int go = 1;
		
		for (;;go++) {
			int nr = r+go*dr[d];
			int nc = c+go*dc[d];
			if (isOut(nr,nc)) break;
			if (map[nr][nc]==1) {
				drawable = false;
				break;
			}
			map[nr][nc] = 1;
		}
		
		return go-1;
	}
	
	static boolean isOut(int r,int c) {
		return r<1 || r>N || c<1 || c>N;
	}
	
	
}
