package solutions.implementaition;

import java.util.ArrayList;

public class LottoRanking {
	
	int maxValue;
	int minValue;
	ArrayList<Integer> list1;
	ArrayList<Integer> list2;
	int arr[];
	int N;
	
	public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {};
        
        maxValue = Integer.MIN_VALUE;
        minValue = Integer.MAX_VALUE;
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        
        int zeroCnt = 0;
        for (int i = 0;i<6;i++) {
        	if (lottos[i]==0) zeroCnt++;
        }
        
        if (zeroCnt==6) {
        	answer = new int[] {1,6};
        } else {
        	for (int i = 0;i<6;i++) {
        		if (lottos[i]>0) { 
        			list1.add(lottos[i]);
        		}
        	}
        	for (int i = 0;i<6;i++) {
        		list2.add(win_nums[i]);
        	}
        	N = 6-list1.size();
        	arr = new int[N];
        	dfs(0,1);
        	answer = new int[] {minValue,maxValue};
        }
        
        return answer;
    }
	
	void dfs(int x,int from) {
		if (x==N) {
			int score = getScore();
			if (score>maxValue) maxValue = score;
			if (score<minValue) minValue = score;
			return;
		}
		if (from>45) return;
		for (int i = from;i<=45;i++) {
			if (!list1.contains(i)) {
				arr[x] = i;
				dfs(x+1,from+1);
				arr[x] = 0;
			}
		}
	}
	 
	int getScore() {
		int cnt = 0;
		for (int i = 0;i<list1.size();i++) {
			int num = list1.get(i);
			if (list2.contains(num)) cnt++;
		}
		
		for (int i = 0;i<N;i++) {
			if (list2.contains(arr[i])) cnt++;
		}
		
		if (cnt>=2) {
			return 7-cnt;
		} else return 6;
	}
}
