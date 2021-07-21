package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RollingDice {
	static int N,M,x,y,K;
	static int[][] map;
	static int[] dirs;
	static int[] dr = {0,0,0,-1,1};
	static int[] dc = {0,1,-1,0,0};
	
	static int[] dice = new int[6];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dirs = new int[K];
		
		for (int i = 0;i<N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		str = br.readLine();
		st = new StringTokenizer(str," ");
		for (int i = 0;i<K;i++) {
			dirs[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0;i<K;i++) {
			moveDice(dirs[i]);
		}
		
	}
	
	static void moveDice(int dir) {
		int nx = x + dr[dir];
		int ny = y + dc[dir];
		if (nx < 0 || nx>=N || ny<0 || ny>=M) return;
		x = nx;
		y = ny;
		arrangeDiceNumber(dir);
		if (map[x][y]==0) {
			map[x][y] = dice[5];
		} else {
			dice[5] = map[x][y];
			map[x][y] = 0;
		}
		System.out.println(dice[0]);
	}
	
	static void arrangeDiceNumber(int dir) {
		int[] tempArr = new int[6];
		if (dir==1) {
			tempArr[0] = dice[3];
			tempArr[1] = dice[1];
			tempArr[2] = dice[0];
			tempArr[3] = dice[5];
			tempArr[4] = dice[4];
			tempArr[5] = dice[2];
		} else if (dir == 2) {
			tempArr[0] = dice[2];
			tempArr[1] = dice[1];
			tempArr[2] = dice[5];
			tempArr[3] = dice[0];
			tempArr[4] = dice[4];
			tempArr[5] = dice[3];
		} else if (dir == 3) {
			tempArr[0] = dice[4];
			tempArr[1] = dice[0];
			tempArr[2] = dice[2];
			tempArr[3] = dice[3];
			tempArr[4] = dice[5];
			tempArr[5] = dice[1];
		} else if (dir==4) {
			tempArr[0] = dice[1];
			tempArr[1] = dice[5];
			tempArr[2] = dice[2];
			tempArr[3] = dice[3];
			tempArr[4] = dice[0];
			tempArr[5] = dice[4];
		}
		
		for (int i = 0;i<6;i++) dice[i] = tempArr[i];
	}
	
}
