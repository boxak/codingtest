package solutions.implementaition;

public class CalculateLackOfMoney {
	public long solution(int price, int money, int count) {
        long answer = -1;
        
        answer = findTotalPrice(price,count);
        
        answer = answer - money;
        
        return answer;
    }
	
	long findTotalPrice(int price,int count) {
		long sum = 0;
		
		for (int i = 1;i<=count;i++) {
			int temp = count*price;
			sum = sum + temp;
		}
		
		return sum;
	}
}
