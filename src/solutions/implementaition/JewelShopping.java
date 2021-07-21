package solutions.implementaition;

import java.util.HashMap;

public class JewelShopping {
  public static int[] solution(String[] gems) {
    int[] answer = {};

    HashMap<String, Integer> map = new HashMap<>();

    for (String gem : gems) {
      if (map.containsKey(gem)) {
        int cnt = map.get(gem);
        map.put(gem,cnt+1);
      } else {
        map.put(gem,1);
      }
    }

    int start = 0;
    int end = gems.length-1;

    for (int i = gems.length-1;i>=0;i--) {
      String gem = gems[i];
      if (map.get(gem)>1) {
        end--;
        int cnt = map.get(gem);
        map.put(gem,cnt-1);
      } else if (map.get(gem)==1) {
        end = i;
        break;
      }
    }

    for (int i = 0;i<gems.length;i++) {
      String gem = gems[i];
      if (map.get(gem)>1) {
        start++;
        int cnt = map.get(gem);
        map.put(gem,cnt-1);
      } else if (map.get(gem)==1) {
        start = i;
        break;
      }
    }

    answer = new int[2];
    answer[0] = start+1;
    answer[1] = end+1;

    return answer;
  }

  public static void main(String[] args) {
    int[] a = solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
    System.out.println(a[0]+" "+a[1]);
  }
}
