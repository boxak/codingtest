package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SharkClass2 {
    static int N,M;
    static final int[] dr = {-1,0,1,0};
    static final int[] dc = {0,1,0,-1};
    static int[][] map;
    static int[][] cmap;
    static boolean[][] visited;
    static ArrayList<BlockGroup> blockList;
    static ArrayList<Pair> tempList;
    static boolean stop;
    static int answer;
    static int cntRainbow;
    
    static class Pair implements Comparable<Pair>{
    	int r;
    	int c;
    	int color;
    	int isRainbow;
    	
    	Pair(int r,int c,int color,int isRainbow) {
    		this.r = r;
    		this.c = c;
    		this.color = color;
    		this.isRainbow = isRainbow;
    	}
    	
    	@Override
    	public int compareTo(Pair pair) {
    		if (this.isRainbow==pair.isRainbow) {
    			if (this.r==pair.r) {
    				return this.c - pair.c;
    			} else return this.r - pair.r;
    		} else return this.isRainbow - pair.isRainbow;
    	}
    	
    }
    
    static class BlockGroup implements Comparable<BlockGroup>{
    	int rainbowCnt;
    	ArrayList<Pair> list;
    	
    	BlockGroup(int raindbowCnt,ArrayList<Pair> list) {
    		this.rainbowCnt = rainbowCnt;
    		this.list = list;
    	}
    	
    	@Override
    	public int compareTo(BlockGroup blockGroup) {
    		Pair pair1 = this.list.get(0);
    		Pair pair2 = blockGroup.list.get(0);
    		if (this.list.size()==blockGroup.list.size()) {
    			if (this.rainbowCnt==blockGroup.rainbowCnt) {
    				if (pair2.r==pair1.r) {
    					return pair2.c-pair1.c;
    				} else return pair2.r-pair1.r;
    			} else return blockGroup.rainbowCnt - this.rainbowCnt;
    		} else return blockGroup.list.size() - this.list.size();
    	}
    }
    
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String str = br.readLine();
    	StringTokenizer st = new StringTokenizer(str," ");
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	map = new int[N+1][N+1];
    	cmap = new int[N+1][N+1];
    	visited = new boolean[N+1][N+1];
    	blockList = new ArrayList<>();
    	stop = false;
    	answer = 0;
    	
    	// -1 : 검은색 블록, 0 : 무지개, 1 ~ M : 일반, -2 : 빈 칸
    	for (int i = 1;i<=N;i++) {
    		str = br.readLine();
    		st = new StringTokenizer(str," ");
    		for (int j = 1;j<=N;j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			if (map[i][j]==-1) map[i][j] = 7;
    			if (map[i][j]==0) map[i][j] = 6;
    		}
    	}
    	
    	while(true) {
    		findBlockGroup();
    		printArr("");
    		if (stop) break;
    		removeBiggestGroup();
    		printArr("remove");
    		gravityWork();
    		printArr("gravity");
    		rotate();
    		printArr("rotate");
    		gravityWork();
    		printArr("gravity");
    		System.out.println("Answer : "+answer);
    	}
    	
    	System.out.println(answer);
    }
    
    static void findBlockGroup() {
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			visited[i][j] = false;
    		}
    	}
    	
    	blockList.clear();
    	
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			if (map[i][j]>=1 && map[i][j]<=M && !visited[i][j]) {
    				tempList = new ArrayList<>();
    				cntRainbow = 0;
    				dfs(map[i][j],i,j);
    				if (tempList.size()<2) continue;
    				Collections.sort(tempList);
    				blockList.add(new BlockGroup(cntRainbow,tempList));
    			}
    		}
    	}
    	
    	if (blockList.size()==0) stop = true;
    }
    
    static void removeBiggestGroup() {
    	Collections.sort(blockList);
    	ArrayList<Pair> list = blockList.get(0).list;
    	int cnt = list.size();
    	for (Pair pair : list) {
    		map[pair.r][pair.c] = 0;
    	}
    	answer+=cnt*cnt;
    }
    
    static void dfs(int num,int r,int c) {
    	visited[r][c] = true;
    	if (map[r][c] == 0) {
    		tempList.add(new Pair(r,c,0,1));
    		cntRainbow++;
    	} else {
    		tempList.add(new Pair(r,c,num,0));
    	}
    	
    	for (int d = 0;d<4;d++) {
    		int nr = r+dr[d];
    		int nc = c+dc[d];
    		if (isOut(nr,nc)) continue;
    		if (!visited[nr][nc] && (map[nr][nc]==num || map[nr][nc]==6)) {
    			dfs(num,nr,nc);
    		}
    	}
    }
    
    static void gravityWork() {
    	for (int j = 1;j<=N;j++) {
	    	for (int i = N-1;i>=1;i--) {
	    		if (map[i][j]!=0 && map[i][j]!=7) {
	    			int r = i;
	    			int num = map[i][j];
	    			boolean flag = false;
	    			for (int k = i+1;k<=N;k++) {
	    				if (map[k][j]!=0) {
	    					r = k-1;
	    					flag = true;
	    					break;
	    				}
	    			}
	    			if (!flag) r = N;
	    			map[i][j] = 0;
	    			map[r][j] = num;
	    		}
	    	}
    	}
    }
    
    static void rotate() {
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			cmap[N+1-j][i] = map[i][j];
    		}
    	}
    	
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			map[i][j] = cmap[i][j];
    		}
    	}
    }
    
    static boolean isOut(int r,int c) {
    	return r<1 || r>N || c<1 || c>N;
    }
    
    static void printArr(String str) {
    	System.out.println(str);
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			System.out.printf("%d ",map[i][j]);
    		}
    		System.out.println();
    	}
    	System.out.println();
    }
}
