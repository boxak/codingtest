package solutions.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class JourneyCourse {

  ArrayList<String> order = new ArrayList<>();

  class Pair implements Comparable<Pair> {
    String visit;
    boolean visited;
    Pair(String a, boolean b) {
      visit = a;
      visited = b;
    }

    public int compareTo(Pair pair) {
      return this.visit.compareTo(pair.visit);
    }
  }

  public String[] solution(String[][] tickets) {
    String[] answer = {};
    HashMap<String,ArrayList<Pair>> map = new HashMap<>();

    for (int i = 0;i<tickets.length;i++) {
      String a = tickets[i][0];
      String b = tickets[i][1];
      if (!map.containsKey(a)) map.put(a,new ArrayList<>());
      if (!map.containsKey(b)) map.put(b,new ArrayList<>());
      map.get(a).add(new Pair(b,false));
    }

    for (String key : map.keySet()) {
      ArrayList<Pair> list = map.get(key);
      Collections.sort(list);
      map.put(key,list);
    }

    dfs(map,"ICN");

    answer = new String[order.size()];

    for (int i = 0;i<order.size();i++) {
      answer[i] = order.get(i);
    }

    return answer;
  }

  void dfs(HashMap<String,ArrayList<Pair>> map, String visit) {
    ArrayList<Pair> list = map.get(visit);
    order.add(visit);
    for (int i = 0;i<list.size();i++) {
      if (!list.get(i).visited) {
        list.get(i).visited = true;
        dfs(map,list.get(i).visit);
      }
    }
  }

}
