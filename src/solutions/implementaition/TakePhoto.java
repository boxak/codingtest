package solutions.implementaition;

public class TakePhoto {

  static char charArr[] = new char[8];
  static boolean checked[] = new boolean[8];
  static char friendList[] = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
  static int ans = 0;

  public static int solution(int n, String[] data) {
    int answer = 0;
    dfs(0, data);
    answer = ans;
    return answer;
  }

  public static void dfs(int inx, String[] data) {
    if (inx == 8) {
      if (checkCondition(data)) {
        ans++;
      }
      return;
    }
    for (int i = 0; i < 8; i++) {
      if (!checked[i]) {
        checked[i] = true;
        charArr[inx] = friendList[i];
        dfs(inx + 1, data);
        checked[i] = false;
      }
    }
  }

  public static boolean checkCondition(String[] data) {
    String temp = new String(charArr);
    for (String condition : data) {
      char friend1 = condition.charAt(0);
      char friend2 = condition.charAt(2);
      int inx1 = temp.indexOf(friend1);
      int inx2 = temp.indexOf(friend2);
      int dist = Math.abs(inx1 - inx2) - 1;
      int wishDist = condition.charAt(4) - '0';
      if (condition.charAt(3) == '=') {
        if (dist!=wishDist) {
          return false;
        }
      } else if(condition.charAt(3) == '>') {
        if (dist<=wishDist) {
          return false;
        }
      } else if (condition.charAt(3) == '<') {
        if (dist>=wishDist) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int answer = 0;
    answer = solution(2,new String[]{"M~C<2"});
    System.out.println("Answer : "+answer);
  }
}
