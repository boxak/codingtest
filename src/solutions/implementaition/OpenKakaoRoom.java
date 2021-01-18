package solutions.implementaition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenKakaoRoom {
    public static String[] solution(String[] record) {
        String[] answer = {};

        HashMap<String, String> map = new HashMap<>();
        ArrayList<String> chating = new ArrayList<>();
        for (String str : record) {
            String arr[] = str.split(" ");
            String action = arr[0];
            String id = arr[1];
            if ("Enter".equals(action)) {
                String name = arr[2];
                map.put(id,name);
                chating.add(id+"님이 들어왔습니다.");
            } else if("Change".equals(action)) {
                String name = arr[2];
                map.put(id,name);
            } else {
                chating.add(id+"님이 나갔습니다.");
            }
        }

        for (int i = 0;i<chating.size();i++) {
            String str = chating.get(i);
            Pattern pattern = Pattern.compile("[A-Za-z0-9]{1,}");
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                String id = matcher.group();
                String name = map.get(id);
                str = str.replaceFirst(id,name);
            }
            chating.set(i,str);
        }

        answer = new String[chating.size()];

        for (int i = 0;i<chating.size();i++) {
            answer[i] = chating.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] arr = solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"});
        for (String str : arr) {
            System.out.println(str);
        }
    }
}
