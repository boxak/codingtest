package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SharkClass {

    static int N;
    static int[][] map;
    static int[][] friendMap;
    static int[][] emptyMap;
    static int maxFriendCnt;
    static int maxEmptyCnt;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static Map<Integer,ArrayList<Integer>> friendList;
    static int answer;
    static int[] order;
    static final int[] happiness = {0,1,10,100,1000};
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	N = Integer.parseInt(br.readLine());
    	
    	map = new int[N+1][N+1];
    	friendMap = new int[N+1][N+1];
    	emptyMap = new int[N+1][N+1];
    	order = new int[N*N+1];
    	
    	friendList = new HashMap<>();
    	answer = 0;
    	
    	for (int i = 1;i<=N*N;i++) {
    		String str = br.readLine();
    		StringTokenizer st = new StringTokenizer(str," ");
    		ArrayList<Integer> tempList = new ArrayList<>();
    		
    		int num = Integer.parseInt(st.nextToken());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		int d = Integer.parseInt(st.nextToken());
    		
    		tempList.add(a);
    		tempList.add(b);
    		tempList.add(c);
    		tempList.add(d);
    		
    		friendList.put(num,tempList);
    		order[i] = num;
    	}
    	
    	findSeat();
    	findSumOfHappy();
    	
    	System.out.println(answer);
    }
    
    static void findSumOfHappy() {
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			int cnt = 0;
    			ArrayList<Integer> tempList = friendList.get(map[i][j]);
    			for (int d = 0;d<4;d++) {
    				int nr = i + dr[d];
    				int nc = j + dc[d];
    				if (isOut(nr,nc)) continue;
    				if (tempList.contains(map[nr][nc])) cnt++;
    			}
    			answer+=happiness[cnt];
    		}
    	}
    }
    
    static void findSeat() {
    	for (int i = 1;i<=N*N;i++) {
    		int studentNum = order[i];
    		findFriends(studentNum);
    		findEmptySeat(studentNum);
    		sitDown(studentNum);
    	}
    }
    
    static void findFriends(int num) {
    	maxFriendCnt = 0;
    	ArrayList<Integer> tempList = friendList.get(num);
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			if (map[i][j]!=0) continue;
    			int cnt = 0;
    			for (int d = 0;d<4;d++) {
    				int nr = i + dr[d];
    				int nc = j + dc[d];
    				if (isOut(nr,nc)) continue;
    				if (tempList.contains(map[nr][nc])) {
    					cnt++;
    				}
    			}
    			if (cnt>maxFriendCnt) maxFriendCnt = cnt;
    			friendMap[i][j] = cnt;
    		}
    	}
    }
    
    static void findEmptySeat(int num) {
    	maxEmptyCnt = 0;
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			if (map[i][j]!=0) continue;
    			if (map[i][j]==0 && friendMap[i][j]==maxFriendCnt) {
    				int cnt = 0;
    				for (int d = 0;d<4;d++) {
    					int nr = i + dr[d];
    					int nc = j + dc[d];
    					if (isOut(nr,nc)) continue;
    					if (map[nr][nc]==0) {
    						cnt++;
    					}
    				}
    				if (cnt>maxEmptyCnt) maxEmptyCnt = cnt;
    				emptyMap[i][j] = cnt; 
    			}
    		}
    	}
    }
    
    static void sitDown(int num) {
    	for (int i = 1;i<=N;i++) {
    		boolean flag = false;
    		for (int j = 1;j<=N;j++) {
    			if (map[i][j]==0 && friendMap[i][j]==maxFriendCnt && emptyMap[i][j]==maxEmptyCnt) {
    				map[i][j] = num;
    				flag = true;
    				break;
    			}
    		}
    		if (flag) break;
    	}
    }
    
    static boolean isOut(int r,int c) {
    	return r<1 || r>N || c<1 || c>N;
    }
	
}
