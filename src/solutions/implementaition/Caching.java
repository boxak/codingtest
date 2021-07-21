package solutions.implementaition;

import java.util.LinkedList;
import java.util.Queue;

public class Caching {
    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;

        Queue<String> que = new LinkedList<>();

        for (int i = 0;i< cities.length;i++) {
            String city = cities[i];
            city = city.toUpperCase();
            cities[i] = city;
        }

        for (String city : cities) {
            if (!que.contains(city)) {
                answer+=5;
                que.add(city);
                if (que.size()>cacheSize) {
                    que.poll();
                }
            } else {
                answer++;
                que.remove(city);
                que.add(city);
            }
        }

        return answer;
    }

    public static void main(String args[]) {
        System.out.println(solution(2,new String[]{
                "Jeju","Pangyo","NewYork","newyork"
        }));
    }
}
