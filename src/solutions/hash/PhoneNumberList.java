package solutions.hash;

import java.util.ArrayList;
import java.util.Collections;

public class PhoneNumberList {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        ArrayList<String> list = new ArrayList<>();
        for (String phoneNumber : phone_book) {
            list.add(phoneNumber);
        }
        Collections.sort(list);

        int len = phone_book.length;

        for (int i = 1;i<len;i++) {
            if (list.get(i).startsWith(list.get(i-1))) {
                answer = false;
                break;
            }
        }

        return answer;
    }
}
