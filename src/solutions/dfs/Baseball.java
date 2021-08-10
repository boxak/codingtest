package solutions.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baseball {

    static int N;
    static int[][] points;
    static int[] base;
    static int answer;
    static int[] arr;
    static boolean[] checked;
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	N = Integer.parseInt(br.readLine());
    	base = new int[3];
    	points = new int[N+1][10];
    	arr = new int[10];
    	checked = new boolean[10];
    	answer = 0;
    	
    	//4번째에 치는 타자는 1번 타자.
    	arr[4] = 1;
    	checked[1] = true;
    	
    	dfs(1);
    	
    	System.out.println(answer);
    	
    }
    
    static void dfs(int x) {
    	if (x == 10) {
    		simulation();
    		return;
    	}
    	
    	if (x==4) dfs(x+1);
    	else {
    		for (int i = 2;i<=9;i++) {
    			if (!checked[i]) {
    				checked[i] = true;
    				arr[x] = i;
    				dfs(x+1);
    				checked[i] = false;
    				arr[x] = 0;
    			}
    		}
    	}
    }
    
    static void simulation() {
    	int totalScore = 0;
    	int runner = 1;
    	
    	for (int ining=1;ining<=N;ining++) {
    		//이닝이 시작될 때, out은 0이고
    		//타순은 유지되며
    		//베이스엔 주자가 없다.
    		int out = 0;
    		for (int b=0;b<3;b++) base[b] = 0;
    		
    		while(out<3) {
    			int player = arr[runner];
    			int result = points[ining][player];
    			if ()
    		}
    		
    	}
    	
    }
	
}
