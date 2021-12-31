package solutions.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MovePipe {
	static int N;
	static int[][] map;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		answer = 0;
		
		for (int i = 1;i<=N;i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			for (int j = 1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			} 
		}
		
		dfs(1,1,1,2,1);
		
		System.out.println(answer);
	}
	
	static void dfs(int r1,int c1,int r2,int c2,int d) {
		if (r2<1 || r2>N || c2<1 || c2>N) return;
		if (map[r2][c2]==1 && (d==1 || d==3)) return;
		
		else if ((map[r2][c2]==1 || map[r2-1][c2]==1 || map[r2][c2-1]==1) && d==2) return;
		
		if (r2==N && c2==N) {
			answer++;
			return;
		}
		
		if (d==1) {
			dfs(r2,c2,r2,c2+1,1);
			dfs(r2,c2,r2+1,c2+1,2);
		}
		
		else if (d==2) {
			dfs(r2,c2,r2,c2+1,1);
			dfs(r2,c2,r2+1,c2+1,2);
			dfs(r2,c2,r2+1,c2,3);
		}
		
		else if (d==3) {
			dfs(r2,c2,r2+1,c2+1,2);
			dfs(r2,c2,r2+1,c2,3);
		}
	}
 	
}
