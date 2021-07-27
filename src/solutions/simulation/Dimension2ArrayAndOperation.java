package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Dimension2ArrayAndOperation {
	static int r,c,k;
	static int[][] A;
	static int rowCnt,colCnt;
	static ArrayList<Pair> list;
	static Map<Integer, Integer> map;
	
	static class Pair implements Comparable<Pair> {
		int num;
		int cnt;
		
		Pair(int num,int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Pair pair) {
			if (this.cnt==pair.cnt) {
				return this.num-pair.num;
			} else return this.cnt-pair.cnt;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		A = new int[101][101];
		list  = new ArrayList<>();
		map = new HashMap<>();
		
		for (int i = 1;i<=3;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 1;j<=3;j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		rowCnt = 3;
		colCnt = 3;
		
		int loopCnt = 0;
		
		while(A[r][c]!=k && loopCnt<=100) {
			loopCnt++;
			simulation();
		}
		
		System.out.println(loopCnt>100 ? -1 : loopCnt);
		
	}
	
	
	static void simulation() {
		if (rowCnt>=colCnt) {
			R_simulation();
		} else {
			C_simulation();
		}
	}
	
	static void R_simulation() {
		int maxColLen = Integer.MIN_VALUE;
		for (int row = 1;row<=rowCnt;row++) {
			map.clear();
			list.clear();
			for (int col = 1;col<=colCnt;col++) {
				if (A[row][col]==0) continue;
				if (!map.containsKey(A[row][col])) {
					map.put(A[row][col], 1);
				} else {
					int cnt = map.get(A[row][col]);
					map.put(A[row][col], cnt+1);
				}                                                                          
			}
			
			for (Integer key : map.keySet()) {
				list.add(new Pair(key,map.get(key)));
			}
			
			Collections.sort(list);
			
			int len = 2*list.size();
			
			if (len>maxColLen) maxColLen = len;
			
			for (int i = 0;i<list.size();i++) {
				A[row][2*i+1] = list.get(i).num;
				A[row][2*i+2] = list.get(i).cnt;
			}
			
			for (int i=len+1;i<=100;i++) {
				A[row][i] = 0;
			}
			
		}
		colCnt = maxColLen;
	}
	
	static void C_simulation() {
		int maxRowLen = Integer.MIN_VALUE;
		for (int col = 1;col<=colCnt;col++) {
			map.clear();
			list.clear();
			for (int row = 1;row<=rowCnt;row++) {
				if (A[row][col]==0) continue;
				if (!map.containsKey(A[row][col])) {
					map.put(A[row][col], 1);
				} else {
					int cnt = map.get(A[row][col]);
					map.put(A[row][col],cnt+1);
				}
			}
			
			for (Integer key : map.keySet()) {
				list.add(new Pair(key,map.get(key)));
			}
			
			Collections.sort(list);
			
			int len = 2*list.size();
			
			if (len>maxRowLen) maxRowLen = len;
			
			for (int i = 0;i<list.size();i++) {
				A[2*i+1][col] = list.get(i).num;
				A[2*i+2][col] = list.get(i).cnt;
			}
			
			for (int i = len+1;i<=100;i++) {
				A[i][col] = 0;
			}
		}
		rowCnt = maxRowLen;
	}
	
}
