package solutions.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChickenDelivery {
	static int N,M;
	static int answer;
	static ArrayList<Pair> homes;
	static ArrayList<Pair> chickens;
	static int[] arr;
	static boolean[] check;
	
	static class Pair {
		int r,c;
		Pair(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		
		homes = new ArrayList<>();
		chickens = new ArrayList<>();
		answer = Integer.MAX_VALUE;
		
		for (int i = 0;i<N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 0;j<N;j++) {
				int a = Integer.parseInt(st.nextToken());
				if (a==1) {
					homes.add(new Pair(i,j));
				} else if (a==2) {
					chickens.add(new Pair(i,j));
				}
			}
		}
		
		check = new boolean[chickens.size()];
		
		dfs(0,0);
		
		System.out.println(answer);
	}
	
	static void dfs(int x,int from) {
		if (x==M) {
			int dist = getDistSum();
			if (dist<answer) answer =dist;
			return;
		}
		if (from>=chickens.size()) return;
		for (int i = from;i<chickens.size();i++) {
			arr[x] = i;
			dfs(x+1,i+1);
			arr[x] = 0;
		}
	}
	
	static int getDistSum() {
		for (int i = 0;i<check.length;i++) check[i] = false;
		
		for (int i = 0;i<arr.length;i++) {
			check[arr[i]] = true;
		}
		
		int chickenSum = 0;
		
		for (int i = 0;i<homes.size();i++) {
			int r1 = homes.get(i).r;
			int c1 = homes.get(i).c;
			int minValue = Integer.MAX_VALUE;
			for (int j = 0;j<chickens.size();j++) {
				if (check[j]) {
					int r2 = chickens.get(j).r;
					int c2 = chickens.get(j).c;
					int dist = getDist(r1,c1,r2,c2);
					if (minValue>dist) minValue = dist;
				}
			}
			chickenSum+=minValue;
		}
		
		return chickenSum;
		
	}
	
	static int getDist(int r1,int c1,int r2,int c2) {
		return Math.abs(r2-r1) + Math.abs(c2-c1);
	}
	
}
