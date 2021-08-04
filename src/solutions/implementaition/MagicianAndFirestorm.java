package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MagicianAndFirestorm {

    static int N,Q;
    static int[][] map;
    static int[][] cmap;
    static int[] magics;
    static int iceSum;
    static int iceCnt;
    static boolean[][] visited;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String str = br.readLine();
    	StringTokenizer st = new StringTokenizer(str," ");
    	
    	N = Integer.parseInt(st.nextToken());
    	Q = Integer.parseInt(st.nextToken());
    	
    	N = (int)Math.pow(2, N);
    	
    	map = new int[N+1][N+1];
    	cmap = new int[N+1][N+1];
    	visited = new boolean[N+1][N+1];
    	magics = new int[Q];
    	iceSum = 0;
    	iceCnt = 0;
    	
    	for (int i = 1;i<=N;i++) {
    		str = br.readLine();
    		st = new StringTokenizer(str," ");
    		for (int j = 1;j<=N;j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	str = br.readLine();
    	st = new StringTokenizer(str," ");
    	
    	for (int i = 0;i<Q;i++) {
    		magics[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	for (int i = 0;i<Q;i++) {
    		int magic = magics[i];
    		simulation(magic);
    	}
    	
    	int maxCnt = 0;
    	
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			if (map[i][j]>0 && !visited[i][j]) {
    				iceCnt=0;
    				dfs(i,j);
    				if (iceCnt>maxCnt) maxCnt = iceCnt;
    			}
    		}
    	}
    	
    	System.out.printf("%d\n%d", iceSum, maxCnt);
    }
    
    static void simulation(int magic) {
    	int size = (int)Math.pow(2, magic);
    	int[][] tempMap = new int[size+1][size+1];
    	
    	for (int r = 0;r<N/size;r++) {
    		for (int c = 0;c<N/size;c++) {
    			for (int i = r*size+1;i<=(r+1)*size;i++) {
    				for (int j = c*size+1;j<=(c+1)*size;j++) {
    					tempMap[i-r*size][j-c*size] = map[i][j];		
    				}
    			}
    			rotate(tempMap,size);
    			for (int i = r*size+1;i<=(r+1)*size;i++) {
    				for (int j = c*size+1;j<=(c+1)*size;j++) {
    					map[i][j] = tempMap[i-r*size][j-c*size];		
    				}
    			}
    		}
    	}
    	
    	meltIce();
    }
    
    static void meltIce() {
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			int cnt = 0;
    			int num = map[i][j];
    			if (num>0) {
	    			for (int d = 0;d<4;d++) {
	    				int nr = i + dr[d];
	    				int nc = j + dc[d];
	    				if (isOut(nr,nc)) continue;
	    				if (map[nr][nc]>0) cnt++;
	    			}
	    			if (cnt<3) num--;
    			}
    			cmap[i][j] = num;
    		}
    	}
    	
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			map[i][j] = cmap[i][j];
    		}
    	}
    }
    
    static void rotate(int[][] cmap,int size) {
    	int[][] tempMap = new int[size+1][size+1];
    	for (int i = 1;i<=size;i++) {
    		for (int j = 1;j<=size;j++) {
    			tempMap[j][size+1-i] = cmap[i][j];
    		}
    	}
    	
    	for (int i = 1;i<=size;i++) {
    		for (int j = 1;j<=size;j++) {
    			cmap[i][j] = tempMap[i][j];
    		}
    	}
    }
    
    static void dfs(int r,int c) {
    	visited[r][c] = true;
    	iceSum+=map[r][c];
    	iceCnt++;
    	for (int d = 0;d<4;d++) {
    		int nr = r+dr[d];
    		int nc = c+dc[d];
    		if (isOut(nr,nc)) continue;
    		if (!visited[nr][nc] && map[nr][nc]>0) {
    			dfs(nr,nc);
    		}
    	}
    }
	
    static boolean isOut(int r,int c) {
    	return r<1 || r>N || c<1 || c>N;
    }
}
