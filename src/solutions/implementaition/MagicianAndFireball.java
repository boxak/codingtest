package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MagicianAndFireball {
	
	static class Fireball {
		int r;
		int c;
		int m;
		int s;
		int d;
		Fireball(int r,int c,int m,int s,int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	static int N,M,K;
	static Queue<Fireball>[][] cmap1;
	static Queue<Fireball> que;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		answer = 0;
		cmap1 = new Queue[N][N];
		que = new LinkedList<>();
		
		for (int i = 0;i<M;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			que.add(new Fireball(r-1,c-1,m,s,d));
		}
		
		for (int time = 0;time<K;time++) {
			simulation();
		}
		
		for (Fireball fireball : que) {
			answer+=fireball.m;
		}
		
		System.out.println(answer);
		
	}
	
	static void simulation() {
		moveFireball();
		arrange();
	}
	
	static void moveFireball() {
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				cmap1[i][j] = new LinkedList<>();
			}
		}
		
		while (!que.isEmpty()) {
			Fireball fireball = que.peek();
			int r = fireball.r;
			int c = fireball.c;
			int m = fireball.m;
			int s = fireball.s;
			int d = fireball.d;
			
			que.poll();
			
			int nr = r + s*dr[d];
			int nc = c + s*dc[d];
			
			if (nr>=0) {
				nr = nr%N;
			} else if (nr<0) {
				nr = -nr;
				nr = N - nr%N;
				if (nr==N) nr = 0;
			}
			
			if (nc>=0) {
				nc = nc%N;
			} else if (nc<0) {
				nc = -nc;
				nc = N - nc%N;
				if (nc==N) nc = 0;
			}
			
			cmap1[nr][nc].add(new Fireball(nr,nc,m,s,d));
		}
		
	}
	
	static void arrange() {
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				if (cmap1[i][j].size()==1) {
					Fireball fireball = cmap1[i][j].peek();
					que.add(new Fireball(fireball.r,fireball.c,fireball.m,fireball.s,fireball.d));
				}
				if (cmap1[i][j].size()>1) {
					Queue<Fireball> tempQue = cmap1[i][j];
					int sumM = 0;
					int sumS = 0;
					int cntOdd = 0;
					int cntEven = 0;
					int cnt = tempQue.size();
					while(!tempQue.isEmpty()) {
						int m = tempQue.peek().m;
						int s = tempQue.peek().s;
						int d = tempQue.peek().d;
						tempQue.poll();
						sumM+=m;
						sumS+=s;
						if (d%2==0) cntEven++;
						if (d%2==1) cntOdd++;
					}
					
					int mm = sumM/5;
					int ss = sumS/cnt;
					
					if (mm>0) {
						if (cntEven==0 || cntOdd==0) {
							que.add(new Fireball(i,j,mm,ss,0));
							que.add(new Fireball(i,j,mm,ss,2));
							que.add(new Fireball(i,j,mm,ss,4));
							que.add(new Fireball(i,j,mm,ss,6));
						} else {
							que.add(new Fireball(i,j,mm,ss,1));
							que.add(new Fireball(i,j,mm,ss,3));
							que.add(new Fireball(i,j,mm,ss,5));
							que.add(new Fireball(i,j,mm,ss,7));
						}
					}
				}
			}
		}
	}
  
}
