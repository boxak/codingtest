package solutions.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class StartTaxi {
	
	static class Pair {
		int r;
		int c;
		int cost;
		Pair(int r,int c,int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
	}
	
	static class Guest {
		int sr;
		int sc;
		int er;
		int ec;
		Guest(int sr,int sc,int er,int ec) {
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
		}
	}
	
	static int answer;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<Pair> que = new LinkedList<>();
	static int[][] map;
	
}
