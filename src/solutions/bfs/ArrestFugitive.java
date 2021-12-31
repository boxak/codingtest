package solutions.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ArrestFugitive {
	static int N,M,R,C,L;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static int answer;
	static boolean[][] visited;
	static boolean[][][] check;
	static int[][] map;
	static Queue<Pair> que;
	
	static class Pair {
		int r;
		int c;
		Pair(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TestCnt = Integer.parseInt(br.readLine());
		int[] answers = new int[TestCnt+1];
		
		for (int test = 1;test<=TestCnt;test++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			answer = 0;
			
			map = new int[N][M];
			visited = new boolean[N][M];
			check = new boolean[N][M][4];
			que = new LinkedList<>();
			
			for (int i = 0;i<N;i++) {
				str = br.readLine();
				st = new StringTokenizer(str," ");
				for (int j = 0;j<M;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			checkMovableInfo();
			bfs();
			
			answers[test] = answer;
			
		}
		
		for (int i = 1;i<=TestCnt;i++) System.out.println("#"+i+" "+answers[i]);
		
	}
	
	static void bfs() {
		que.add(new Pair(R,C));
		visited[R][C] = true;
		int repeat = 0;
		
		while(!que.isEmpty()) {
			int queSize = que.size();
			if (repeat==L) break;
			for (int time = 0;time<queSize;time++) {
				int r = que.peek().r;
				int c = que.peek().c;
				//System.out.println(r+" "+c+" "+repeat);
				que.poll();
				answer++;
				for (int d = 0;d<4;d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr<0 || nr>=N || nc<0 || nc>=M) continue;
					if (!visited[nr][nc] && check[r][c][d] && check[nr][nc][(d+2)%4] && map[nr][nc]>0) {
						visited[nr][nc] = true;
						que.add(new Pair(nr,nc));
					}
				}
			}
			repeat++;
		}
	}
	
	static void checkMovableInfo() {
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<M;j++) {
				if (map[i][j]==1) {
					check[i][j][0] = true;
					check[i][j][1] = true;
					check[i][j][2] = true;
					check[i][j][3] = true;
				} else if (map[i][j]==2) {
					check[i][j][0] = true;
					check[i][j][2] = true;
				} else if (map[i][j]==3) {
					check[i][j][1] = true;
					check[i][j][3] = true;
				} else if (map[i][j]==4) {
					check[i][j][0] = true;
					check[i][j][1] = true;
				} else if (map[i][j]==5) {
					check[i][j][1] = true;
					check[i][j][2] = true;
				} else if (map[i][j]==6) {
					check[i][j][2] = true;
					check[i][j][3] = true;
				} else if (map[i][j]==7) {
					check[i][j][0] = true;
					check[i][j][3] = true;
				}
			}
		}
	}
	
}
