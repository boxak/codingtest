package solutions.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Lab {
	static int N,M;
	static int[][] map;
	static int[][] cmap;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int answer;
	
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
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = Integer.MIN_VALUE;
		
		map = new int[N+1][M+1];
		cmap = new int[N+1][M+1];
		
		for (int i = 1;i<=N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeWalls();
		
		System.out.println(answer);
		
	}
	
	static void makeWalls() {
		for (int r1 = 1;r1<=N;r1++) {
			for (int c1=1;c1<=M;c1++) {
				if (map[r1][c1]==0) {
					map[r1][c1] = 1;
					for (int r2=r1;r2<=N;r2++) {
						for (int c2=1;c2<=M;c2++) {
							if (r2==r1 && c2<=c1) continue;
							if (map[r2][c2]==0) {
								map[r2][c2] = 1;
								for (int r3=r2;r3<=N;r3++) {
									for (int c3=1;c3<=M;c3++) {
										if (r3==r2 && c3<=c2) continue;
										if (map[r3][c3]==0) {
											map[r3][c3] = 1;
											bfs();
											map[r3][c3] = 0;
										}
									}
								}
								map[r2][c2] = 0;
							}
						}
					}
					map[r1][c1] = 0;
				}
			}
		}
	}
	
	static void bfs() {
	
		
		Queue<Pair> que = new LinkedList<>();
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=M;j++) {
				cmap[i][j] = map[i][j];
			}
		}
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=M;j++) {
				if (cmap[i][j]==2) {
					que.add(new Pair(i,j));
				}
			}
		}
		
		while(!que.isEmpty()) {
			int r = que.peek().r;
			int c = que.peek().c;
			que.poll();
			for (int d = 0;d<4;d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if (nr<1 || nr>N || nc<1 || nc>M) continue;
				if (cmap[nr][nc]==0) {
					cmap[nr][nc] = 2;
					que.add(new Pair(nr,nc));
				}
			}
		}
		
		int cnt = 0;
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=M;j++) {
				if (cmap[i][j]==0) cnt++;
			}
		}
		
		if (answer < cnt) answer = cnt;
		
	}
	
}
