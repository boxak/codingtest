package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class StudentShark {
	
	static int answer;
	static int[] dr = {0,-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,0,-1,-1,-1,0,1,1,1};
	
	static class Fish implements Comparable<Fish>{
		int num;
		int d;
		int r;
		int c;
		Fish(int r,int c,int num,int dir) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.d = dir;
		}
		
		@Override
		public int compareTo(Fish fish) {
			return this.num-fish.num;
		}
		
		@Override
		public boolean equals(Object fish) {
			return this.num==((Fish)fish).num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Fish[][] map = new Fish[4][4];
		ArrayList<Fish> fishes = new ArrayList<>();
		answer = Integer.MIN_VALUE;
		
		for (int i = 0;i<4;i++) {
			String str  = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			for (int j = 0;j<4;j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[i][j] = new Fish(i,j,a,b);
				fishes.add(new Fish(i,j,a,b));
			}
		}

		dfs(map,fishes,0,0,0);
		
		System.out.println(answer);
		
	}
	
	static void dfs(Fish[][] map,ArrayList<Fish> fishes,int r,int c,int sum) {
		
		Fish[][] cmap = copyMap(map);
		ArrayList<Fish> clist = copyList(fishes);
		
		int inx = cmap[r][c].num;
		int dir = cmap[r][c].d;
		if (sum+inx>answer) answer = sum+inx;
		
		cmap[r][c] = new Fish(r,c,0,0);
		clist.remove(new Fish(0,0,inx,0));
		
		moveFish(cmap,clist,r,c);
		
		for (int go = 1;go<=3;go++) {
			int nr = r+go*dr[dir];
			int nc = c+go*dc[dir];
			if (isOut(nr,nc)) break;
			if (cmap[nr][nc].num!=0) {
				dfs(cmap,clist,nr,nc,sum+inx);
			}
		}
		
		
	}
	
	static void moveFish(Fish[][] map,ArrayList<Fish> fishes,int sharkR,int sharkC) {
		Collections.sort(fishes);
		int len = fishes.size();
		for (int i = 0;i<len;i++) {
			Fish fish = fishes.get(i);
			
			int r = fish.r;
			int c = fish.c;
			int d = fish.d;
			int num = fish.num;
			
			d = changeDir(map,sharkR,sharkC,r,c,d);
			if (d==-1) continue;
			
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if (map[nr][nc].num==0) {
				map[nr][nc] = new Fish(nr,nc,num,d);
				fishes.set(i, new Fish(nr,nc,num,d));
				map[r][c] = new Fish(r,c,0,0);
			} else {
				int index = fishes.indexOf(new Fish(0,0,map[nr][nc].num,0));
				
				int num2 = map[nr][nc].num;
				int dir2 = map[nr][nc].d;
				map[nr][nc] = new Fish(nr,nc,num,d);
				fishes.set(i, new Fish(nr,nc,num,d));
				
				map[r][c] = new Fish(r,c,num2,dir2);
				fishes.set(index, new Fish(r,c,num2,dir2));
			}
			
		}
	}
	
	static Fish[][] copyMap(Fish[][] map) {
		Fish[][] cmap = new Fish[4][4];
		for (int i = 0;i<4;i++) {
			for (int j = 0;j<4;j++) {
				cmap[i][j] = new Fish(i,j,map[i][j].num,map[i][j].d);
			}
		}
		return cmap;
	}
	
	static ArrayList<Fish> copyList(ArrayList<Fish> list) {
		ArrayList<Fish> clist = new ArrayList<>();
		for (Fish fish : list) {
			clist.add(new Fish(fish.r,fish.c,fish.num,fish.d));
		}
		return clist;
	}
	
	static int changeDir(Fish[][] map,int sharkR,int sharkC,int r,int c,int d) {
		int tempD = d;
		boolean find = false;
		for (int i = 0;i<=8;i++) {
			int nr = r+dr[tempD];
			int nc = c+dc[tempD];
			if (isOut(nr,nc) || (nr==sharkR && nc==sharkC)) {
				tempD++;
				if (tempD==9) tempD = 1;
				continue;
			} else {
				find = true;
				break;
			}
		}
		
		if (!find) tempD = -1;
		
		return tempD;
	}
	
	static boolean isOut(int r,int c) {
		return r<0 || r>=4 || c<0 || c>=4;
	}
  
}
