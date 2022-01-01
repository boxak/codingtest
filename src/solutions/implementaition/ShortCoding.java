package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShortCoding {

    static ArrayList<String> variables = new ArrayList<>();
    static ArrayList<Pair> pairs = new ArrayList<>();
    static HashMap<String, String> parents = new HashMap<>();
    static HashMap<String, ArrayList<String>> notEquals = new HashMap<>();

    static class Pair implements Comparable<Pair> {
        String v1;
        String v2;

        Pair(String v1, String v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public int compareTo(Pair pair) {
            return (this.v1.length()+this.v2.length())-(pair.v1.length()+pair.v2.length());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String coding = br.readLine();

        Pattern pattern = Pattern.compile("[a-zA-Z0-9\\-]{1,}");
        Matcher matcher = pattern.matcher(coding);

        while(matcher.find()) {
            String variable = matcher.group();
            if (!variables.contains(variable)) {
                variables.add(variable);
            }
        }

        String[] logics = coding.split("&&");

        for (String logic : logics) {
            if (logic.contains("!=")) continue;
            String[] split = logic.split("==");
            String v1 = split[0];
            String v2 = split[1];

            pairs.add(new Pair(v1, v2));
        }

        Collections.sort(pairs);

        kruskal();

        for (String logic : logics) {
            if (logic.contains("==")) continue;
            String[] split = logic.split("!=");
            String v1 = split[0];
            String v2 = split[1];

            String parent1 = parents.get(v1);
            String parent2 = parents.get(v2);

            if (parent1.length()<parent2.length()) {
                if (!notEquals.containsKey(parent1)) {
                    notEquals.put(parent1, new ArrayList<>());
                    notEquals.get(parent1).add(parent2);
                } else {
                    if (!notEquals.get(parent1).contains(parent2)) {
                        notEquals.get(parent1).add(parent2);
                    }
                }
            }

            else {
                if (!notEquals.containsKey(parent2)) {
                    notEquals.put(parent2, new ArrayList<>());
                    notEquals.get(parent2).add(parent1);
                } else {
                    if (!notEquals.get(parent2).contains(parent1)) {
                        notEquals.get(parent2).add(parent1);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (String key : parents.keySet()) {
            String value = parents.get(key);
            if (key.equals(value)) continue;
            sb.append(key+"=="+value+"&&");
        }

        for (String key : notEquals.keySet()) {
            ArrayList<String> notEqualList = notEquals.get(key);
            for (String str : notEqualList) {
                sb.append(key + "!=" +str+"&&");
            }
        }

        String answer = sb.toString();

        answer = answer.substring(0,answer.length()-2);

        System.out.println(answer);
    }

    static void kruskal() {
        int N = pairs.size();

        for (String variable : variables) {
            parents.put(variable, variable);
        }

        for (int i = 0;i<N;i++) {
            String v1 = pairs.get(i).v1;
            String v2 = pairs.get(i).v2;

            String parent1 = parents.get(v1);
            String parent2 = parents.get(v2);

            if (parent2.equals(parent1)) continue;

            if (parent2.length()<parent1.length()) {
                parents.put(parent1, findParent(parent2));
            } else {
                parents.put(parent2, findParent(parent1));
            }
        }
    }

    static String findParent(String child) {
        String parent = parents.get(child);
        if (child.equals(parent)) {
            return parent;
        } else {
            return findParent(parent);
        }
    }
}
