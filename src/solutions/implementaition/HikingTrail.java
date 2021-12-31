package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HikingTrail {
	static int N,K;
	static int[][] map;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static int answer;
	static boolean[][] visited;
	static int maxHeight;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TestCnt = Integer.parseInt(br.readLine());
		int[] answers = new int[TestCnt+1];
		
		for (int test = 1;test<=TestCnt;test++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N+1][N+1];
			visited = new boolean[N+1][N+1];
			answer = 0;
			maxHeight = Integer.MIN_VALUE;
			
			for (int i = 1;i<=N;i++) {
				str = br.readLine();
				st = new StringTokenizer(str," ");
				for (int j = 1;j<=N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j]>maxHeight) maxHeight = map[i][j];
				}
			}
			
			for (int i = 1;i<=N;i++) {
				for (int j = 1;j<=N;j++) {
					if (map[i][j]==maxHeight) {
						dfs(i,j,1);
					}
				}
			}
			
			for (int i = 1;i<=N;i++) {
				for (int j = 1;j<=N;j++) {
					int save = map[i][j];
					for (int k = 1;k<=K;k++) {
						map[i][j]--;
						for (int r = 1;r<=N;r++) {
							for (int c = 1;c<=N;c++) {
								if (map[r][c]==maxHeight) {
									dfs(r,c,1);
								}
							}
						}
					}
					map[i][j] = save;
				}
			}
			answers[test] = answer;
		}
		
		for (int i = 1;i<=TestCnt;i++) System.out.println("#"+i+" "+answers[i]);
		
	}
	
	static void dfs(int r,int c,int length) {
		visited[r][c] = true;
		if (length>answer) answer = length;
		
		for (int d = 0;d<4;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if (nr<1 || nr>N || nc<1 || nc>N) continue;
			if (map[r][c]>map[nr][nc] && !visited[nr][nc]) {
				dfs(nr,nc,length+1);
			}
		}
		visited[r][c] = false;
	}
	
}
