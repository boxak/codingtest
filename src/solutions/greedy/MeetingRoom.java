package solutions.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MeetingRoom {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N;
        N = Integer.parseInt(br.readLine());
        ArrayList<Meeting> arrayList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arrayList.add(new Meeting(a, b));
        }
        Collections.sort(arrayList);

        int cnt = 1;
        ArrayList<Meeting> selectedMeetings = new ArrayList<>();
        selectedMeetings.add(arrayList.get(0));

        for (int i = 1;i<N;i++) {
            if (selectedMeetings.get(cnt-1).endTime<=arrayList.get(i).startTime) {
                selectedMeetings.add(arrayList.get(i));
                cnt++;
            }
        }
        int answer = cnt;
        System.out.println(answer);
    }

    static class Meeting implements Comparable<Meeting> {
        int startTime;
        int endTime;

        Meeting(int a, int b) {
            startTime = a;
            endTime = b;
        }

        @Override
        public int compareTo(Meeting meeting) {
            //이 조건이 왜 필요한건지??
            if (this.endTime==meeting.endTime) {
                return this.startTime - meeting.startTime;
            }
            return this.endTime - meeting.endTime;
        }
    }

}
