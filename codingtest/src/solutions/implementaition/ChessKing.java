package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChessKing {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        String king = st.nextToken();
        String rock = st.nextToken();
        int N = Integer.parseInt(st.nextToken());

        int kr = 8 - (king.charAt(1)-'0');
        int kc = king.charAt(0) - 'A';

        int rr = 8 - (rock.charAt(1)-'0');
        int rc = rock.charAt(0) - 'A';


        int dr[] = {0,0,1,-1,-1,-1,1,1};
        int dc[] = {1,-1,0,0,1,-1,1,-1};


        String dirs[] = new String[N];

        for (int i = 0;i<N;i++){
            dirs[i] = br.readLine();
        }
        for (int i = 0;i<N;i++) {
            String dir = dirs[i];
            int dir2 = 0;
            switch (dir) {
                case "R" :
                    dir2 = 0;
                    break;
                case "L" :
                    dir2 = 1;
                    break;
                case "B" :
                    dir2 = 2;
                    break;
                case "T" :
                    dir2 = 3;
                    break;
                case "RT" :
                    dir2 = 4;
                    break;
                case "LT" :
                    dir2 = 5;
                    break;
                case "RB" :
                    dir2 = 6;
                    break;
                case "LB" :
                    dir2 = 7;
                    break;
            }
            int knr = kr+dr[dir2];
            int knc = kc+dc[dir2];
            if (knr<0 || knr>=8 || knc<0 || knc>=8) continue;
            if (knr==rr && knc == rc) {
                int rnr = rr+dr[dir2];
                int rnc = rc+dc[dir2];
                if (rnr<0 || rnr>=8 || rnc<0 || rnc>=8) continue;
                rr = rnr;
                rc = rnc;
            }
            kr = knr;
            kc = knc;
        }

        kr = 8 - kr;
        rr = 8 - rr;

        char charkc = (char) (kc+'A');
        char charrc = (char) (rc+'A');

        System.out.println(charkc+String.valueOf(kr));
        System.out.println(charrc+String.valueOf(rr));

    }
}
