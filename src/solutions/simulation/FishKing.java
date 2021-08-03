package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class FishKing {
	static int R,C,M;
	static ArrayList<Shark> sharks;
	static ArrayList<Shark> tempList;
	static int answer;
	static int[][] map;
	static int[][] cmap;
	static final int[] dr = {0,-1,1,0,0};
	static final int[] dc = {0,0,0,1,-1};
	static int fisher_col;
	
	static class Shark implements Comparable<Shark>{
		int r,c,s,d,z;
		Shark(int r,int c,int s,int d,int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
		@Override
		public boolean equals(Object shark) {
			return this.r==((Shark)shark).r && this.c==((Shark)shark).c;
		}
		
		@Override
		public int compareTo(Shark shark) {
			return shark.z-this.z;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		cmap = new int[R][C];
		sharks = new ArrayList<>();
		tempList = new ArrayList<>();
		
		for (int i = 0;i<M;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			map[r-1][c-1] = z;
			sharks.add(new Shark(r-1,c-1,s,d,z));
		}
		
		answer = 0;
		fisher_col = 0;
		
		for (;fisher_col<C;fisher_col++) {
			huntShark();
			moveShark();
		}
		
		System.out.println(answer);
		
	}
	
	static void huntShark() {
		for (int i = 0;i<R;i++) {
			if (map[i][fisher_col]>0) {
				answer+=map[i][fisher_col];
				map[i][fisher_col] = 0;
				sharks.remove(new Shark(i,fisher_col,0,0,0));
				break;
			}
		}
	}
	
	static void moveShark() {
		int sharkCnt = sharks.size();
		int N = 0;
		Collections.sort(sharks);
		tempList.clear();
		
		for (int i = 0;i<R;i++) {
			for (int j = 0;j<C;j++) {
				cmap[i][j] = 0;
			}
		}
		
		for (int i = 0;i<sharkCnt;i++) {
			Shark shark = sharks.get(i);
			int r = shark.r;
			int c = shark.c;
			int s = shark.s;
			int d = shark.d;
			int z = shark.z;
			
			int nr = r + s*dr[d];
			int nc = c + s*dc[d];
			
			if (nr<0) {
				nr = -nr;
				d = 2;
			}
			if (nc<0) {
				nc = -nc;
				d = 3;
			}
			// d = 2
			if (nr>=R) {
				int a = nr%(R-1);
				int b = (nr-1)/(R-1);
				if (a!=0) {
					if (b%2==1) {
						nr = R-1-a;
						d = 1;
					} else {
						nr = a;
						d = 2;
					}
				} else {
					if (b%2==0) {
						nr = R-1;
						d = 2;
					} else {
						nr = 0;
						d = 1;
					}
				}
			}
			
			if (nc>=C) {
				int a = nc%(C-1);
				int b = (nc-1)/(C-1);
				if (a!=0) {
					if (b%2==1) {
						nc = C-1-a;
						d = 4;
					} else {
						nc = a;
						d = 3;
					}
				} else {
					if (b%2==0) {
						nc = C-1;
						d = 3;
					} else {
						nc = 0;
						d = 4;
					}
				}
			}
			if (cmap[nr][nc]==0) {
				cmap[nr][nc] = z;
				tempList.add(new Shark(nr,nc,s,d,z));
			}
		}
		
		sharks.clear();
		
		for (Shark shark : tempList) {
			sharks.add(shark);
		}
		
		for (int i = 0;i<R;i++) {
			for (int j = 0;j<C;j++) {
				map[i][j] = cmap[i][j];
			}
		}
		
	}
}
