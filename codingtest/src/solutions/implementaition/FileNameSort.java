package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameSort {

    static class FileSort implements Comparable<FileSort>{
        String head;
        int number;
        String title;
        FileSort(String x,int y,String z) {
            head = x;
            number = y;
            title = z;
        }

        public int compareTo(FileSort fs) {
            if (this.head.equals(fs.head)) {
                return this.number - fs.number;
            } else return this.head.compareTo(fs.head);
        }
    }

    public static String[] solution(String[] files) {
        String[] answer = {};
        ArrayList<FileSort> fileList = new ArrayList<>();

        for (int i = 0;i< files.length;i++) {
            String fileName = files[i];
            Pattern pattern = Pattern.compile("[0-9]{1,}");
            Matcher matcher = pattern.matcher(fileName);
            String numberStr = "";
            String head = "";
            int inx = -1;
            if (matcher.find()) {
                numberStr = matcher.group();
                if (numberStr.length()>5) {
                    numberStr = numberStr.substring(0,5);
                }
                inx = fileName.indexOf(numberStr);
                head = fileName.substring(0,inx);

            }
            head = head.replaceAll("[^a-zA-Z]","");
            // -> 이 부분 때문에 틀린거, 문제 조건이랑 다르게 내가
            //스스로 작위적으로 넣은 부분이 어디인지 생각해보자!
            fileList.add(new FileSort(head.toLowerCase(),Integer.parseInt(numberStr),fileName));
        }
        Collections.sort(fileList);
        answer = new String[fileList.size()];
        for (int i = 0;i< fileList.size();i++) {
            answer[i] = fileList.get(i).title;
            System.out.println(fileList.get(i).title);
        }

        return answer;
    }

    public static void main(String args[]) {
        /*String a = "ABC001BC002";
        String b = "BBC";
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher(a);
        String numberStr = "";
        int inx = -1;
        if (matcher.find()) {
            numberStr = matcher.group();
            inx = a.indexOf(numberStr);
            System.out.println(a.substring(0,inx));
        }*/
        solution(
                new String[]{"img12.png", "img10.png",
                        "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"}
        );
    }
}
