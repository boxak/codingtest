package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MarbleExit {
	
	static int N,M;
	
	//빨간 구슬 위치
	static int r1,c1;
	//파란 구슬 위치
	static int r2,c2;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	
	static char[][] map;
	static boolean isSuccess;
	static int[] arr;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N+1][M+1];
		arr = new int[10];
		isSuccess = false;
		
		for (int i = 1;i<=N;i++) {
			str = br.readLine();
			for (int j = 1;j<=M;j++) {
				map[i][j] = str.charAt(j-1);
				if (map[i][j]=='R') {
					r1 = i;
					c1 = j;
					map[i][j] = '.';
				} else if (map[i][j]=='B') {
					r2 = i;
					c2 = j;
					map[i][j] = '.';
				}
			}
		}
		
		int answer = -1;
		
		for (int i = 1;i<=10;i++) {
			dfs(0,i);
			if (isSuccess) {
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);
		
	}
	
	static void dfs(int x,int n) {
		if (isSuccess) return;
		if (x==n) {
			simulation(n);
			return;
		}
		for (int i = 0;i<4;i++) {
			arr[x] = i;
			dfs(x+1,n);
			arr[x] = 0;
		}
	}
	
	static void simulation(int n) {
		int temp_r1 = r1;
		int temp_c1 = c1;
		int temp_r2 = r2;
		int temp_c2 = c2;
		
		for (int i = 0;i<n;i++) {
			boolean flag = moveMarbles(arr[i]);
			if (flag) break;
		}
		
		r1 = temp_r1;
		c1 = temp_c1;
		r2 = temp_r2;
		c2 = temp_c2;
		
	}
	
	static boolean moveMarbles(int d) {
		int[][] pos = new int[2][2];
		boolean isRedFirst = redFirst(d, pos);
		boolean isBlueInside = false;
		
		int temp_r1 = pos[0][0];
		int temp_c1 = pos[0][1];
		int temp_r2 = pos[1][0];
		int temp_c2 = pos[1][1];
		
		
		for (int i = 1;i<=10;i++) {
			int nr = temp_r1 + i*dr[d];
			int nc = temp_c1 + i*dc[d];
			if (map[nr][nc]=='#' || map[nr][nc]=='O') {
				if (map[nr][nc]=='O') {
					if (!isRedFirst) {
						isBlueInside = true;
					} else {
						isSuccess = true;
					}
					temp_r1 = nr;
					temp_c1 = nc;
				} else {
					temp_r1 = temp_r1 + (i-1)*dr[d];
					temp_c1 = temp_c1 + (i-1)*dc[d];
				}
				break;
			}
		}
		
		if (!isBlueInside) {
			for (int i = 1;i<=10;i++) {
				int nr = temp_r2 + i*dr[d];
				int nc = temp_c2 + i*dc[d];
				if (map[nr][nc]=='#' || map[nr][nc]=='O') {
					if (map[nr][nc]=='O') {
						if (!isRedFirst) {
							temp_r2 = nr;
							temp_c2 = nc;
							isSuccess = true;
						} else {
							temp_r2 = temp_r2 + (i-1)*dr[d];
							temp_c2 = temp_c2 + (i-1)*dc[d];
							isSuccess = false;
							isBlueInside = true;
						}
					} else {
						temp_r2 = temp_r2 + (i-1)*dr[d];
						temp_c2 = temp_c2 + (i-1)*dc[d];
					}
					break;
				}
			}
		}
		
		if (isRedFirst) {
			r1 = temp_r1;
			c1 = temp_c1;
			r2 = temp_r2;
			c2 = temp_c2;
		} else {
			r1 = temp_r2;
			c1 = temp_c2;
			r2 = temp_r1;
			c2 = temp_c1;
		}
		
		return isBlueInside;
	}
	
	static boolean redFirst(int d,int[][] pos) {
		boolean isRedFirst = false;
		if (d == 0) {
			if (r1<=r2) {
				pos[0][0] = r1; 
				pos[0][1] = c1;
				pos[1][0] = r2;
				pos[1][1] = c2;
				isRedFirst = true;
			} else {
				pos[0][0] = r2; 
				pos[0][1] = c2;
				pos[1][0] = r1;
				pos[1][1] = c1;
			}
		} else if (d == 1) {
			if (c1>=c2) {
				pos[0][0] = r1; 
				pos[0][1] = c1;
				pos[1][0] = r2;
				pos[1][1] = c2;
				isRedFirst = true;
			} else {
				pos[0][0] = r2; 
				pos[0][1] = c2;
				pos[1][0] = r1;
				pos[1][1] = c1;
			}
		} else if (d == 2) {
			if (r1>=r2) {
				pos[0][0] = r1; 
				pos[0][1] = c1;
				pos[1][0] = r2;
				pos[1][1] = c2;
				isRedFirst = true;
			} else {
				pos[0][0] = r2; 
				pos[0][1] = c2;
				pos[1][0] = r1;
				pos[1][1] = c1;
			}
		} else if (d == 3) {
			if (c1<=c2) {
				pos[0][0] = r1; 
				pos[0][1] = c1;
				pos[1][0] = r2;
				pos[1][1] = c2;
				isRedFirst = true;
			} else {
				pos[0][0] = r2; 
				pos[0][1] = c2;
				pos[1][0] = r1;
				pos[1][1] = c1;
			}
		}
		
		return isRedFirst;
	}
	
}
