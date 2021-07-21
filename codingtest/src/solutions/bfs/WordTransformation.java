package solutions.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class WordTransformation {

  class Pair {
    String word;
    int cost;
    Pair(String x, int y) {
      word = x;
      cost = y;
    }
  }

  public int solution(String begin, String target, String[] words) {
    int answer = 0;

    Queue<Pair> que = new LinkedList<>();
    que.add(new Pair(begin,0));
    boolean[] visited = new boolean[words.length];

    while(!que.isEmpty()) {
      String word = que.peek().word;
      int cost = que.peek().cost;
      que.poll();
      if (target.equals(word)) {
        answer = cost;
        break;
      }
      for (int i = 0;i<words.length;i++) {
        if (!visited[i] && check(word,words[i])) {
          que.add(new Pair(words[i],cost+1));
          visited[i] = true;
        }
      }
    }

    return answer;
  }

  boolean check(String s1, String s2) {
    int cnt = 0;
    for (int i = 0;i<s1.length();i++) {
      if (s1.charAt(i)!=s2.charAt(i)) cnt++;
    }
    return cnt == 1;
  }

}
