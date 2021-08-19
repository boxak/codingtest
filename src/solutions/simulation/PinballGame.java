package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PinballGame {
	static int N;
	static int[][] map;
	static WarmHole[] warmHoles;
	static int answer;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};

	static class WarmHole {
		int r1,c1,r2,c2;
		WarmHole(int a,int b,int c,int d) {
			r1 = a;
			c1 = b;
			r2 = c;
			c2 = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TestCnt = Integer.parseInt(br.readLine());
		int[] answers = new int[TestCnt+1];
		
		for (int test = 1;test<=TestCnt;test++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N+1][N+1];
			warmHoles = new WarmHole[11];
			
			for (int i = 6;i<=10;i++) warmHoles[i] = new WarmHole(0,0,0,0);
			
			answer = 0;
			
			for (int i = 1;i<=N;i++) {
				String str = br.readLine();
				StringTokenizer st = new StringTokenizer(str," ");
				for (int j = 1;j<=N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j]>=6) {
						int num = map[i][j];
						if (warmHoles[num].r1==0 && warmHoles[num].c1==0) {
							warmHoles[num].r1 = i;
							warmHoles[num].c1 = j;
						} else {
							warmHoles[num].r2 = i;
							warmHoles[num].c2 = j;
						}
					}
				}
			}
			
			for (int i = 1;i<=N;i++) {
				for (int j = 1;j<=N;j++) {
					if (map[i][j]==0) {
						for (int d = 0;d<4;d++) {
							int collision = simulation(i,j,d);
							if (collision>answer) answer = collision;
						}
					}
				}
			}
			
			answers[test] = answer;
			
		}
		
		for (int i = 1;i<=TestCnt;i++) System.out.println("#"+i+" "+answers[i]);
		
	}
	
	static int simulation(int sr,int sc,int sdir) {
		int r = sr;
		int c = sc;
		int dir = sdir;
		int score = 0;
		
		while(true) { 
			if (map[r][c] >= 1 && map[r][c] <= 5) {
				dir = changeDir(r, c, dir);
				score++;
			} else if (map[r][c] >= 6) {
				int num = map[r][c];
				if (warmHoles[num].r1 == r && warmHoles[num].c1 == c) {
					r = warmHoles[num].r1;
					c = warmHoles[num].c1;
				} else {
					r = warmHoles[num].r2;
					c = warmHoles[num].c2;
				}
			}

			r = r + dr[dir];
			c = c + dc[dir];
			
			if (r<1 || r>N || c<1 || c>N) {
				dir = (dir+2)%4;
				r = r + dr[dir];
				c = c + dc[dir];
				score++;
			}

			if ((r == sr && c == sc) || map[r][c] == -1) break;
			
		}
		
		return score;
		
	}
	
	static int changeDir(int r,int c,int d) {
		int num = map[r][c];
		int dir = d;
		if (num==1) {
			if (d==0) dir = 2;
			else if (d==1) dir = 3;
			else if (d==2) dir = 1;
			else if (d==3) dir = 0;
		} else if (num==2) {
			if (d==0) dir = 1;
			else if (d==1) dir = 3;
			else if (d==2) dir = 0;
			else if (d==3) dir = 2;
		} else if (num==3) {
			if (d==0) dir = 3;
			else if (d==1) dir = 2;
			else if (d==2) dir = 0;
			else if (d==3) dir = 1;
		} else if (num==4) {
			if (d==0) dir = 2;
			else if (d==1) dir = 0;
			else if (d==2) dir = 3;
			else if (d==3) dir = 1;
		} else if (num==5) {
			dir = (d+2)%4;
		}
		
		return dir;
	}
}
