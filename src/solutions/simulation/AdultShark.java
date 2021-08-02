package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class AdultShark {
	static int N,M,K;
	static int[][] map;
	static int[][] tempMap;
	static int[] initDirs;
	static int[][][] priorities;
	static int answer;
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,-1,1};
	static ArrayList<Shark> sharks;
	static Smell[][] smells;
	static ArrayList<Shark> tempList;
	 
	static class Smell {
		int num;
		int time;
		Smell(int x,int y) {
			num = x;
			time = y;
		}
	}
	
	
	static class Shark implements Comparable<Shark>{
		int r;
		int c;
		int d;
		int num;
		int[][] priority;
		
		Shark(int r,int c,int d,int num,int[][] priority) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.num = num;
			this.priority = priority;
		}
		
		@Override
		public int compareTo(Shark shark) {
			return this.num - shark.num;
		}
		
		@Override
		public boolean equals(Object shark) {
			return this.r==((Shark)shark).r && this.c==((Shark)shark).c;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		tempMap = new int[N+1][N+1];
		smells = new Smell[N+1][N+1];
		
		for (int i = 1;i<=N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				smells[i][j] = new Smell(0,0);
			}
		}
		
		initDirs = new int[M+1];
		
		str = br.readLine();
		st = new StringTokenizer(str," ");
		
		for (int i = 1;i<=M;i++) {
			initDirs[i] = Integer.parseInt(st.nextToken());
		}
		
		priorities = new int[M+1][5][5];
		sharks = new ArrayList<>();
		tempList = new ArrayList<>();
		
		for (int i = 1;i<=M;i++) {
			for (int j = 1;j<=4;j++) {
				str = br.readLine();
				st = new StringTokenizer(str," ");
				for (int k = 1;k<=4;k++) {
					priorities[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				int num = map[i][j];
				if (num!=0) {
					sharks.add(new Shark(i,j,initDirs[num],num,priorities[num]));
				}
			}
		}
		
		Collections.sort(sharks);
		
		answer = 0;
		
		while(sharks.size()>1) {
			if (answer>1000) break;
			answer++;
			simulation();
		}
		
		System.out.println(answer>1000 ? -1 : answer);
		
	}
	
	static void simulation() {
		makeAndRemoveSmells();
		moveSharks();
		eatSharks();
	}
	
	static void makeAndRemoveSmells() {
		for (Shark shark : sharks) {
			int r = shark.r;
			int c = shark.c;
			int num = shark.num;
			
			smells[r][c] = new Smell(num,0);
		}
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				if (smells[i][j].num!=0) {
					int num = smells[i][j].num;
					int time = smells[i][j].time;
					time++;
					if (time>K) {
						smells[i][j] = new Smell(0,0);
					} else {
						smells[i][j] = new Smell(num,time);
					}
				}
			}
 		}
	}
	
	static void moveSharks() {
		int sharkCnt = sharks.size();
		tempList.clear();
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				tempMap[i][j] = 0;
			}
		}
		
		for (int i = 0;i<sharkCnt;i++) {
			Shark shark = sharks.get(i);
			int r = shark.r;
			int c = shark.c;
			int d = shark.d;
			int num = shark.num;
			int[][] priority = shark.priority;
			
			d = searchDir(r,c,d,num,priority);
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			tempList.add(new Shark(nr,nc,d,num,priority));
		}
		
		sharks.clear();
		
		for (Shark shark : tempList) {
			if (!sharks.contains(shark)) {
				tempMap[shark.r][shark.c] = shark.num;
				sharks.add(new Shark(shark.r,shark.c,shark.d,shark.num,shark.priority));
			}
		}
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				map[i][j] = tempMap[i][j];
			}
		}
	}
	
	static void eatSharks() {
		tempList.clear();
		for (Shark shark : sharks) {
			if (!tempList.contains(shark)) {
				tempList.add(new Shark(shark.r,shark.c,shark.d,shark.num,shark.priority));
			}
		}
		sharks.clear();
		for (Shark shark : tempList) {
			sharks.add(new Shark(shark.r,shark.c,shark.d,shark.num,shark.priority));
		}
	}
	
	static int searchDir(int r,int c,int d,int num,int[][] priority) {
		int dir = d;
		boolean find = false;
		for (int i = 1;i<=4;i++) {
			int tempD = priority[d][i];
			int nr = r+dr[tempD];
			int nc = c+dc[tempD];
			if (nr<1 || nr>N || nc<1 || nc>N) continue;
			if (smells[nr][nc].num==0) {
				dir = tempD;
				find = true;
				break;
			}
		}
		if (!find) {
			for (int i = 1;i<=4;i++) {
				int tempD = priority[d][i];
				int nr = r+dr[tempD];
				int nc = c+dc[tempD];
				if (nr<1 || nr>N || nc<1 || nc>N) continue;
				if (smells[nr][nc].num==num) {
					dir = tempD;
					break;
				}
			}
		}
		return dir;
	}
}
