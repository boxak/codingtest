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
	static Fireball[][] map;
	static Queue<Fireball>[][] cmap1;
	static Fireball[][] cmap2;
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
		
		map = new Fireball[N][N];
		cmap1 = new Queue[N][N];
		cmap2 = new Fireball[N][N];
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
			map[r][c] = new Fireball(r-1,c-1,m,s,d);
		}
		
		for (int time = 0;time<K;time++) {
			simulation();
		}
		
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				answer+=map[i][j].m;
			}
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
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr>=0) {
				nr = nr%N;
			} else if (nr<0) {
				nr = -(nr+1);
				nr = nr%N;
			}
			
			if (nc>=0) {
				nc = nc%N;
			} else if (nc<0) {
				nc = -(nc+1);
				nc = nc%N;
			}
			
			cmap1[nr][nc].add(new Fireball(nr,nc,m,s,d));
		}
		
	}
	
	static void arrange() {
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				if (cmap1[i][j].size()>0) {
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
						sumM+=m;
						sumS+=s;
						if (d%2==0) cntEven++;
						if (d%2==1) cntOdd++;
					}
				}
			}
		}
	}
  
}
