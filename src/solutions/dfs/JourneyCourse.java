package solutions.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class JourneyCourse {

  static ArrayList<String> order = new ArrayList<>();
  static HashMap<String, Queue<String>> map;

  public static String[] solution(String[][] tickets) {
    String[] answer = {};

    map = new HashMap<>();

    for (int i = 0;i< tickets.length;i++) {
      String start = tickets[i][0];
      String end = tickets[i][1];
      if (!map.containsKey(start)) {
        Queue<String> temp = new LinkedList<>();
        temp.add(end);
        map.put(start,temp);
      } else {
        Queue<String> temp = map.get(start);
        temp.add(end);
        map.put(start,temp);
      }
    }

    for (String key : map.keySet()) {
      ArrayList<String> tempList = new ArrayList<>();
      for (String s : map.get(key)) {
        tempList.add(s);
      }
      Collections.sort(tempList);
      Queue<String> tempQue = new LinkedList<>();
      for (String s : tempList) {
        tempQue.add(s);
      }
      map.put(key,tempQue);
    }

    dfs("ICN");

    answer = new String[order.size()];

    for (int i = 0;i< order.size();i++) {
      answer[i] = order.get(i);
    }

    return answer;
  }

  static void dfs(String visit) {
    order.add(visit);
    Queue<String> tempQue = map.get(visit);
    if (tempQue != null && tempQue.size()>0) {
      String next = tempQue.peek();
      tempQue.poll();
      map.put(visit,tempQue);
      dfs(next);
    }
  }

  public static void main(String args[]) {
    String[] arr = solution(new String[][]{{"ICN","SFO"},{"ICN","ATL"},{"SFO","ATL"},{"ATL","ICN"},{"ATL","SFO"}});
    for (String s : arr) System.out.println(s);
  }

}
