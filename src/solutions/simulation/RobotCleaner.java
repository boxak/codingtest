package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotCleaner {
	
	static int N,M;
	static int r,c,dir;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] map;
	static boolean[][] visited;
	static boolean isStop;
	
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isStop = false;
        
        map = new int[N][M];
        visited = new boolean[N][M];
        
        str = br.readLine();
        st = new StringTokenizer(str," ");
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        
        for (int i = 0;i<N;i++) {
        	str = br.readLine();
        	st = new StringTokenizer(str," ");
        	for (int j = 0;j<M;j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        while(!isStop) {
        	simulation();
        }
        
        int answer = 0;
        
        for (int i = 1;i<N-1;i++) {
        	for (int j = 1;j<M-1;j++) {
        		if (visited[i][j]) answer++;
        	}
        }
        
        System.out.println(answer);
    }
    
    static void simulation() {
    	cleanHear();
    	search();
    }
    
    static void cleanHear() {
    	visited[r][c] = true;
    }
    
    static void search() {
    	boolean flag = false;
    	for (int cnt = 0;cnt<4;cnt++) {
    		dir = dir==0 ? 3 : dir-1;
    		int nr = r + dr[dir];
    		int nc = c + dc[dir];
    		if (map[nr][nc]==1 || visited[nr][nc]) continue;
    		if (map[nr][nc]==0 && !visited[nr][nc]) {
    			r = nr;
    			c = nc;
    			flag = true;
    			break;
    		}
    	}
    	if (!flag) {
    		int backDir = (dir+2)%4;
    		int back_r = r+dr[backDir];
    		int back_c = c+dc[backDir];
    		if (map[back_r][back_c]==0) {
    			r = back_r;
    			c = back_c;
    		} else {
    			isStop = true;
    		}
    	}
    }
}
