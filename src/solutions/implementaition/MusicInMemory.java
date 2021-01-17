package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;

public class MusicInMemory {

    static class Pair implements Comparable<Pair> {
        String title;
        int time;
        int inx;

        Pair(String x, int y, int z) {
            title = x;
            time = y;
            inx = z;
        }

        public int compareTo(Pair pair) {
            if (this.time == pair.time) {
                return this.inx - pair.inx;
            } else return pair.time - this.time;
        }
    }

    public static String solution(String m, String[] musicinfos) {
        String answer = "";

        ArrayList<Pair> list = new ArrayList<Pair>();
        int cnt = 0;

        for (String music : musicinfos) {
            String arr[] = music.split(",");
            String start = arr[0];
            String end = arr[1];
            String title = arr[2];
            String melody = arr[3];

            int streamTime = 0;
            String[] time1 = start.split(":");
            String[] time2 = end.split(":");
            int hour1 = Integer.parseInt(time1[0]);
            int hour2 = Integer.parseInt(time2[0]);
            int minute1 = Integer.parseInt(time1[1]);
            int minute2 = Integer.parseInt(time2[1]);
            streamTime = 60 * (hour2 - hour1) + (minute2 - minute1);

            ArrayList<String> melodyList = new ArrayList<>();
            for (int i = 0; i < melody.length(); i++) {
                if (melody.charAt(i) != '#') {
                    melodyList.add(String.valueOf(melody.charAt(i)));
                } else {
                    melodyList.set(melodyList.size() - 1, melodyList.get(melodyList.size() - 1) + '#');
                }
            }

            int inx = 0;
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < streamTime; i++) {
                sb.append(melodyList.get(inx));
                inx = (inx+1)%(melodyList.size());
            }
            String result = sb.toString();

            if (result.contains(m+"#")) result = result.replaceAll(m+"#","");

            if (result.contains(m)) {
                list.add(new Pair(title, streamTime, cnt));
            }
            cnt++;
        }

        Collections.sort(list);

        if (!list.isEmpty()) answer = list.get(0).title;
        else answer = "(None)";

        return answer;
    }

    public static void main(String[] args) {

        /*System.out.println(solution("ABCDEFG",
                new String[]{"12:00,12:05,HELLO,ABCDE",
                        "13:00,13:07,WORLD,GGGG"
        }));*/

    }
}