package solutions.implementaition;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindBadUser {

  static int[] arr;
  static ArrayList<String> idList;
  static int cnt;

  public static int solution(String[] user_id, String[] banned_id) {
    int answer = 0;
    cnt = 0;

//    String id = "frodo";
//
//    Pattern pattern = Pattern.compile("fr[a-z0-9]{1}d[a-z0-9]{1}");
//    Matcher matcher = pattern.matcher(id);
//
//    if (matcher.find()) {
//      if (id.equals(matcher.group()))
//        System.out.println(id);
//    }

    String[] patterns = getPatterns(banned_id);

    // 밴 당한 아이디 리스트의 리스트
    ArrayList<ArrayList<String>> filtered_ids = new ArrayList<>();

    for (int i = 0;i< patterns.length;i++) filtered_ids.add(new ArrayList<>());

    for (int i = 0;i< patterns.length;i++) {
      String regex = patterns[i];
      for (int j = 0;j< user_id.length;j++) {
        String userid = user_id[j];

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userid);
        if (matcher.find()) {
          if (userid.equals(matcher.group())) {
            filtered_ids.get(i).add(userid);
          }
        }
      }
    }

    arr = new int[patterns.length];

    for (int i = 0;i< patterns.length;i++) arr[i] = filtered_ids.get(i).size();

    dfs(patterns.length, 0,filtered_ids);

    answer = cnt;

    return answer;
  }

  static void dfs(int N, int inx, ArrayList<ArrayList<String>> filtered_ids) {
    if (inx == N) return;
    for (int i = 0;i<arr[inx];i++) {
      if (!idList.contains(filtered_ids.get(inx).get(i))) {
        idList.add(filtered_ids.get(inx).get(i));
      }
    }
  }

  static String[] getPatterns(String[] banned_id) {
    String[] patterns = new String[banned_id.length];
    for (int i = 0;i< banned_id.length;i++) {
      StringBuilder sb = new StringBuilder();
      int starCnt = 0;
      for (int j = 0;j<banned_id[i].length();j++) {
        if (banned_id[i].charAt(j)!='*') {
          if (starCnt>0) {
            String tmpStr = "[a-z0-9]{" + starCnt + "}";
            sb.append(tmpStr);
            starCnt = 0;
          }
          sb.append(banned_id[i].charAt(j));
        } else {
          starCnt++;
        }
      }
      if (starCnt>0) {
        String tmpStr = "[a-z0-9]{" + starCnt + "}";
        sb.append(tmpStr);
      }
      patterns[i] = sb.toString();
      System.out.println(patterns[i]);
    }

    return patterns;
  }

  public static void main(String[] args) {
    solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},new String[]{"fr*d*", "abc1**"});
  }
}
