package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FishKing {
	static int R,C,M;
	static ArrayList<Shark> sharks;
	static int answer;
	static int[][] map;
	static final int[] dr = {0,-1,1,0,0};
	static final int[] dc = {0,0,0,1,-1};
	static int fisher_col;
	
	static class Shark {
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
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		sharks = new ArrayList<>();
		
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
		for (int i = 0;i<sharkCnt;i++) {
			Shark shark = sharks.get(i);
			int r = shark.r;
			int c = shark.c;
			int s = shark.s;
			int d = shark.d;
			int z = shark.z;
			
			map[r][c] = 0;
			
			int nr = r + s*dr[d];
			int nc = c + s*dc[d];
			
			if (d==1 && nr<1) {
				nr = -nr;
				if (nr%(R-1)==0 && (nr/(R-1))%2==0) {
					d = 2;
					nr = (R-1);
				} else if ((nr/(R-1))%2==1) {
					d = 1;
					nr = 0;
				} else if ()
			}
			
		}
	}
}
