package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DragonCurve {
	static int N;
	static Curve[] curveArr;
	static int[][] map = new int[101][101];
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
			drawOneCurve()
		}
	}
}
