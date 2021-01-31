package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;

public class ChusukTraffic {

  static class Pair implements Comparable<Pair> {
    int start;
    int end;
    Pair (int x,int y) {
      start = x;
      end = y;
    }

    public int compareTo (Pair pair) {
      return this.end - pair.end;
    }
  }

  public static int solution(String[] lines) {
    int answer = 0;
    ArrayList<Pair> list = new ArrayList<>();

    for (int i = 0;i< lines.length;i++) {
      String line = lines[i];
      String[] strArr = line.split(" ");
      String time = strArr[1];
      String elapse = strArr[2];

      String[] timeArr = time.split(":|\\.");
      int milSecond = Integer.parseInt(timeArr[2]+timeArr[3]);
      int minute = 60000 * Integer.parseInt(timeArr[1]);
      int hour = 3600000 * Integer.parseInt(timeArr[0]);
      int finishTime = hour + minute + milSecond;

      double d = Double.parseDouble(elapse.replace("s","")) * 1000;
      int cnt = (int)d;
      int startTime = Math.max(finishTime - cnt + 1, 0);
      list.add(new Pair(startTime, finishTime));
    }
  //로그의 시작과 끝 구간만 보면 된다.
    for (int i = 0;i< list.size();i++) {
      int startTime = list.get(i).start;
      int endTime = list.get(i).end;
      int startCnt = 0;
      int endCnt = 0;
      for (int j = 0;j<list.size();j++) {
        if (list.get(j).end>=startTime && list.get(j).start<=startTime + 999) {
         startCnt++;
        }
        if (list.get(j).end>=endTime && list.get(j).start<=endTime + 999) {
          endCnt++;
        }
      }
      answer = Math.max(answer,Math.max(startCnt, endCnt));
    }

    return answer;
  }

  public static void main(String args[]) {
    System.out.println(solution(new String[]{
            "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"
    }));
  }
}
