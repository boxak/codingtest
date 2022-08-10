package solutions.graph;

import java.util.HashMap;
import java.util.Map;

public class TeethBrushSelling {

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};

        Map<String, String> inviteMap = new HashMap<>();
        Map<String, Integer> moneyMap = new HashMap<>();

        for (int i = 0;i< enroll.length;i++) {
            moneyMap.put(enroll[i], 0);
        }

        moneyMap.put("Minho", 0);

        for (int i = 0;i< enroll.length;i++) {
            String invited = enroll[i];
            String invite = referral[i];

            if ("-".equals(invite)) {
                invite = "Minho";
            }

            inviteMap.put(invited, invite);
        }

        inviteMap.put("Minho", "Minho");

        for (int i = 0;i< seller.length;i++) {
            String sellerName = seller[i];
            String name = sellerName;
            int money = amount[i] * 100;
            while(money > 0 && !"Minho".equals(name)) {
                int part1 = money / 10;
                int part2 = money - part1;
                int currentHave = moneyMap.get(name);
                moneyMap.put(name, currentHave + part2);

                name = inviteMap.get(name);
                money = part1;
            }
        }

        answer = new int[enroll.length];

        for (int i = 0;i< enroll.length;i++) {
            String name = enroll[i];
            answer[i] = moneyMap.get(name);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        int[] result = solution(enroll, referral, seller, amount);

        for (int num : result) {
            System.out.printf("%d ",num);
        }

    }
}
