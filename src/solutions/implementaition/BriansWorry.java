package solutions.implementaition;

import java.util.ArrayList;

public class BriansWorry {
  public String solution(String sentence) {
    String answer = "";
    ArrayList<Character> letterList = new ArrayList<>();

    for (int i = 0;i<sentence.length();i++) {
      if (sentence.charAt(i)>='a' && sentence.charAt(i)<='z' && !letterList.contains(sentence.charAt(i))) {
        letterList.add(sentence.charAt(i));
      }
    }

    String copyStr = sentence;

    for (int i = 0;i<letterList.size();i++) {
      char c = letterList.get(i);
      int start = copyStr.indexOf(c);
      int end = copyStr.lastIndexOf(c);

      String substring = sentence.substring(start,end+1);
      //case 1
    }

    return answer;
  }
}
