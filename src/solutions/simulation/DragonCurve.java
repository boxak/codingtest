package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DragonCurve {
	static int N;
	static Curve[] curveArr;
	static boolean[][] map = new boolean[101][101];
	static int answer = 0;
	
	static class Curve {
		int x,y,d,g;
		Curve(int x,int y,int d,int g) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.g = g;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		curveArr = new Curve[N];
		
		for (int i = 0;i<N;i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			curveArr[i] = new Curve(x,y,d,g);
		}
		
		drawCurves();
		
		countSquares();
		
		System.out.println(answer);
		
	}
	
	static void drawCurves() {
		for (int i = 0;i<N;i++) {
			Curve curve = curveArr[i];
			drawOneCurve(curve.x,curve.y,curve.d,curve.g);
		}
	}
	
	static void drawOneCurve(int xx,int yy,int d,int g) {
		ArrayList<int[]> list = new ArrayList<>();
		
		int endx = 0;
		int endy = 0;
		
		if (d==0) {
			endx = 1;
			endy = 0;
		} 
		if (d==1) {
			endx = 0;
			endy = -1;
		}
		if (d==2) {
			endx = -1;
			endy = 0;
		}
		if (d==3) {
			endx = 0;
			endy = 1;
		}
		
		list.add(new int[] {0,0});
		list.add(new int[] {endx,endy});
		
		for (int time = 1;time<=g;time++) {
			//모든 좌표에서 endx,endy 만큼 뺀다.
			for (int i = 0;i<list.size();i++) {
				int x = list.get(i)[0];
				int y = list.get(i)[1];
				list.set(i, new int[] {x-endx,y-endy});
			}
			
			int listSize = list.size();
			
			for (int i = listSize-2;i>=0;i--) {
				int x = list.get(i)[0];
				int y = list.get(i)[1];
				list.add(new int[] {-y,x});
			}
			
			for (int i = 0;i<list.size();i++) {
				int x = list.get(i)[0];
				int y = list.get(i)[1];
				list.set(i, new int[] {x+endx, y+endy});
				
				if (i == list.size()-1) {
					endx = x+endx;
					endy = y+endy;
				}
			}
		}
		
		for (int i = 0;i<list.size();i++) {
			int x = list.get(i)[0];
			int y = list.get(i)[1];
			
			map[xx+x][yy+y] = true;
		}
		
	}
	
	static void countSquares() {
		for (int i = 0;i<100;i++) {
			for (int j = 0;j<100;j++) {
				if (map[i][j] && map[i+1][j]
					&& map[i][j+1] && map[i+1][j+1]) {
					answer++;
				}
			}
		}
	}
}
