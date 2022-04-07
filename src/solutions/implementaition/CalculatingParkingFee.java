package solutions.implementaition;

import java.util.*;

public class CalculatingParkingFee {
    public static int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int addTime = fees[2];
        int addFee = fees[3];

        Map<String, String> parked = new HashMap<>();
        ArrayList<String> carIds = new ArrayList<>();
        Map<String, Integer> timeResults = new HashMap<>();

        for (String record : records) {
            String carId = record.split(" ")[1];
            if (!carIds.contains(carId)) {
                carIds.add(carId);
                timeResults.put(carId, 0);
            }
        }

        for (String record : records) {
            String time = record.split(" ")[0];
            String carId = record.split(" ")[1];
            String IN_OUT = record.split(" ")[2];

            if ("IN".equals(IN_OUT)) {
                parked.put(carId, time);
            } else {
                String inTime = parked.get(carId);

                int min1 = converToMin(inTime);
                int min2 = converToMin(time);
                int parkingTime = min2 - min1;
                int stackedTime = timeResults.get(carId);


//                    int num = (parkingTime - defaultTime)%addTime==0 ? (parkingTime - defaultTime)/addTime : ((parkingTime - defaultTime)/addTime)+1;
//                    int temp_fee = defaultFee + num * addFee;
                timeResults.put(carId, stackedTime + parkingTime);
                parked.remove(carId);
            }

        }

        for (String carId : parked.keySet()) {
            String inTime = parked.get(carId);

            int min1 = converToMin(inTime);
            int min2 = converToMin("23:59");
            int parkingTime = min2 - min1;
            int stackedTime = timeResults.get(carId);

            timeResults.put(carId, stackedTime + parkingTime);
        }

        Collections.sort(carIds, (o1, o2) -> {
            int n1 = Integer.parseInt(o1);
            int n2 = Integer.parseInt(o2);
            return n1-n2;
        });

        answer = new int[timeResults.size()];

        for (int i = 0;i< carIds.size();i++) {
            int stackedTime = timeResults.get(carIds.get(i));

            if (stackedTime <= defaultTime) {
                answer[i] = defaultFee;
            } else {
                int num = (stackedTime - defaultTime)%addTime==0 ? (stackedTime - defaultTime)/addTime : ((stackedTime - defaultTime)/addTime)+1;
                int fee = defaultFee + num * addFee;
                answer[i] = fee;
            }

        }

        return answer;
    }

    public static int converToMin(String time) {
        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);

        return minute + hour * 60;
    }

    public static void main(String[] args) {
        int[] arr = solution(new int[]{180, 5000, 10, 600}, new String[]{
                "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"
        });

        for (int num : arr) {
            System.out.println(num);
        }

    }
}
