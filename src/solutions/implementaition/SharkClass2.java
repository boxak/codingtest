package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SharkClass2 {
    static int N,M;
    static final int[] dr = {-1,0,1,0};
    static final int[] dc = {0,1,0,-1};
    static int[][] map;
    static int[][] cmap;
    static boolean[][] visited;
    static int answer;
    static int area;
    static int cnt;
    static boolean stop;
    static ArrayList<Pair> list;
    static int rr;
    static int cc;
    
    static class Pair implements Comparable<Pair>{
    	int r;
    	int c;
    	int rainbowCnt;
    	int area;
    	
    	Pair(int x,int y,int z,int w) {
    		r = x;
    		c = y;
    		rainbowCnt = z;
    		area = w;
    	}
    	
    	@Override
    	public int compareTo(Pair pair) {
    		if (this.area==pair.area) {
    			if (this.rainbowCnt==pair.rainbowCnt) {
    				if (this.r==pair.r) {
    					return pair.c - this.c;
    				} else return pair.r - this.r;
    			} else return pair.rainbowCnt - this.rainbowCnt;
    		} else return pair.area - this.area;
    	}
    	
    }
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	String str = br.readLine();
    	StringTokenizer st = new StringTokenizer(str," ");
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	map = new int[N+1][N+1];
    	cmap = new int[N+1][N+1];
    	visited = new boolean[N+1][N+1];
    	stop = false;
    	rr = -1;
    	cc = -1;
    	
    	for (int i = 1;i<=N;i++) {
    		str = br.readLine();
        	st = new StringTokenizer(str," ");
    		for (int j = 1;j<=N;j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	while(true) {
    		scan();
    		//print("scan");
    		if (stop) break;
    		remove(map[rr][cc],rr,cc);
    		//print("remove");
    		gravity();
    		//print("gravity1");
    		rotate();
    		//print("rotate");
    		gravity();
    		//print("gravity2");
    		//System.out.println(answer);
    	}
    	
    	System.out.println(answer);
    	
    }
    
    static void scan() {
    	list = new ArrayList<>();
    	initVisit(false);
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			if (!visited[i][j] && map[i][j]>0) {
    				area = 0;
    				cnt = 0;
    				initVisit(true);
    				dfs1(map[i][j],i,j);
    				if (area>=2) {
    					list.add(new Pair(i,j,cnt,area));
    				}
    			}
    		}
    	}
    	if (list.size()==0) {
    		stop = true;
    		return;
    	}
    	Collections.sort(list);
    	rr = list.get(0).r;
    	cc = list.get(0).c;
    	answer+=list.get(0).area*list.get(0).area;
    }
    
    static void remove(int num,int r,int c) {
    	map[r][c] = -2;
    	for (int d = 0;d<4;d++) {
    		int nr = r+dr[d];
    		int nc = c+dc[d];
    		if (isOut(nr,nc)) continue;
    		if (map[nr][nc]==0 || map[nr][nc]==num) {
    			remove(num,nr,nc);
    		}
    	}
    }
    
    static void dfs1(int num,int r,int c) {
    	visited[r][c] = true;
    	area++;
    	if (map[r][c]==0) cnt++;
    	for (int d = 0;d<4;d++) {
    		int nr = r+dr[d];
    		int nc = c+dc[d];
    		if (isOut(nr,nc)) continue;
    		if (!visited[nr][nc] && (map[nr][nc]==0 || map[nr][nc]==num)) {
    			dfs1(num,nr,nc);
    		}
    	}
    }
    
    static boolean isOut(int r,int c) {
    	return r<1 || r>N || c<1 || c>N;
    }
    
    static void gravity() {
    	for (int j = 1;j<=N;j++) {
    		for (int i = N-1;i>=1;i--) {
    			if (map[i][j]!=-2 && map[i][j]!=-1) {
	    			boolean flag = false;
	    			int num = map[i][j];
	    			map[i][j] = -2;
	    			int r = N;
	    			for (int k = i+1;k<=N;k++) {
	    				if (map[k][j]!=-2) {
	    					r = k-1;
	    					break;
	    				}
	    			}
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
    
    static void initVisit(boolean flag) {
    	if (!flag) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    visited[i][j] = false;
                }
            }
        } else {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j]==0) {
                        visited[i][j] = false;
                    }
                }
            }
        }
    }
    
    static void print(String str) {
    	System.out.println(str);
    	for (int i = 1;i<=N;i++) {
    		for (int j = 1;j<=N;j++) {
    			if (map[i][j]==-2) System.out.printf("%d ",0);
    			else if (map[i][j]==-1) System.out.printf("%d ",7);
    			else if (map[i][j]==0) System.out.printf("%d ",6);
    			else System.out.printf("%d ",map[i][j]);
    		}
    		System.out.println();
    	}
    	System.out.println();
    	
    }
}
