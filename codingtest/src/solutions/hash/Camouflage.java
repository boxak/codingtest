package solutions.hash;

import java.util.HashMap;

public class Camouflage {

  static HashMap<String, Integer> map = new HashMap<>();
  static int cntKind = 0;
  static String[] keyArr = new String[100];
  static int ans = 0;

  public static int solution(String[][] clothes) {
    int answer = 0;
    for (String[] strArr : clothes) {
      String clothesKind = strArr[1];
      if (map.containsKey(clothesKind)) {
        int cnt = map.get(clothesKind);
        map.put(clothesKind,cnt+1);
      } else {
        map.put(clothesKind,1);
        keyArr[cntKind++] = clothesKind;
      }
    }

    dfs(0);

    answer = ans - 1;

    return answer;
  }

  public static void dfs(int inx) {
    if (inx == cntKind) {
      ans++;
      return;
    }
    String key = keyArr[inx];
    int cnt = map.get(key);
    for (int i = 0;i<=cnt;i++) {
      dfs(inx+1);
    }
  }

  public static void main(String[] args) {
    System.out.println(solution(new String[][]{{"crow_mask", "face"},{"blue_sunglasses","face"},
        {"smoky_makeup","face"}}));
  }
}
