package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;

public class Tuple {

  static class TupleList implements Comparable<TupleList>{
    ArrayList<Integer> list;
    TupleList(ArrayList<Integer> list) {
      this.list = list;
    }

    public int compareTo(TupleList tuplelist) {
      return this.list.size() - tuplelist.list.size();
    }
  }

  public static int[] solution(String s) {
    int[] answer = {};

    ArrayList<TupleList> list = new ArrayList<>();

    StringBuilder sb = new StringBuilder();
    ArrayList<Integer> tmpList = new ArrayList<>();
    int limitLen = 0;
    for (int i = 0;i<s.length();i++) {
      if (s.charAt(i)>='0' && s.charAt(i)<='9') {
        sb.append(s.charAt(i));
      } else if(s.charAt(i)==',') {
        if (sb.length()!=0) { //프로그래머스엔 sb.isEmpty() 메서드가 안먹히는듯.
          tmpList.add(Integer.parseInt(sb.toString()));
          sb = new StringBuilder();
        }
      } else if(s.charAt(i)=='}') {
        if (sb.length()!=0) {
          tmpList.add(Integer.parseInt(sb.toString()));
          sb = new StringBuilder();
          list.add(new TupleList(tmpList));
          if (tmpList.size()>limitLen) limitLen = tmpList.size();
        }
      } else if(s.charAt(i)=='{') {
        tmpList = new ArrayList<>();
      }
    }

    Collections.sort(list);

    answer = new int[limitLen];
    answer[0] = list.get(0).list.get(0);

    for (int i = 1;i<list.size();i++) {
      ArrayList<Integer> list2 = list.get(i-1).list;
      ArrayList<Integer> list3 = list.get(i).list;
      for (int j = 0;j<list3.size();j++) {
        if (!list2.contains(list3.get(j))) { //왜 정렬해서 비교하면 안되는걸까?
          answer[i] = list3.get(j);
          break;
        }
      }
    }

    return answer;
  }

  public static void main(String args[]) {
    int [] arr = solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
    for (int i : arr) {
      System.out.printf("%d ",i);
    }
  }
}
