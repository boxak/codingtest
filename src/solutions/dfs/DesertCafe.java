package solutions.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DesertCafe {

    static int N;
    static int[][] map;
    static final int[] dr = {1,1,-1,-1};
    static final int[] dc = {1,-1,-1,1};
    static boolean[] visited;
    static int answer;
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int TestCnt = Integer.parseInt(br.readLine());
    	int[] answers = new int[TestCnt+1];
    	
    	for (int test = 1;test<=TestCnt;test++) {
    		N = Integer.parseInt(br.readLine());
    		
    		map = new int[N+1][N+1];
    		visited = new boolean[101];
    		answer = 0;
    		
    		for (int i = 1;i<=N;i++) {
    			String str = br.readLine();
    			StringTokenizer st = new StringTokenizer(str," ");
    			
    			for (int j = 1;j<=N;j++) {
    				map[i][j] = Integer.parseInt(st.nextToken());
    			}
    			
    		}
    		
    		
    		for (int i = 1;i<=N;i++) {
    			for (int j = 1;j<=N;j++) {
    				dfs(i,j,i,j,0,0);
    			}
    		}
    		
    		answers[test] = answer==0 ? -1 : answer;
    	}
    	
    	for (int i = 1;i<=TestCnt;i++) System.out.println("#"+i+" "+answers[i]);
    	
    }
    
    static boolean isOut(int r,int c) {
    	return r<1 || r>N || c<1 || c>N;
    }
    
    static void dfs(int r,int c,int fr,int fc,int d,int desertCnt) {
    	if (r!=fr || c!=fc) visited[map[r][c]] = true;
    	if (r==fr && c==fc && d==3) {
    		if (desertCnt>answer) {
    			answer = desertCnt;
    		}
    		return;
    	}
    	
    	for (int i = 0;i<2;i++) {
    		if (i==1 && d==3) continue;
    		int dir = d+i;
	    	int nr = r + dr[dir];
	    	int nc = c + dc[dir];
	    	if (isOut(nr,nc)) continue;
	    	if (!visited[map[nr][nc]]) {
	    		dfs(nr,nc,fr,fc,dir,desertCnt+1);
	    	}
    	}
    	
    	visited[map[r][c]] = false;
    	
    }

}
