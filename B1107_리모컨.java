package Implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B1107_리모컨 {
    static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        ArrayList<Integer> btns = new ArrayList<Integer>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) { // 고장난 버튼
            int btn = Integer.parseInt(st.nextToken());
            btns.add(btn);
        }

        int ans = Math.abs(100 - N); // 1.  +, - 버튼으로만 움직였을 때

        int min = Integer.MAX_VALUE;
        int cnt = 0;

        for (int i = 0; i <= 999999; i++) { // 2. 완전 탐색

            String str = String.valueOf(i); // 몇자리 수 인지 알기위해서 String으로 받아줌
            boolean check = true;
            for (int k = 0; k < str.length(); k++) {
                if (btns.contains(str.charAt(k) - '0')) { // 고장난 버튼 때문에 바로 i 못 누르면 스킵
                    check = false;
                    break;
                }
            }
            if (!check) continue;

            cnt = str.length() + Math.abs(i - N); // 고장난 버튼 없어서 바로 i를 누를 수 있으면 i 누르고 +/- 눌러서 이동
            // 숫자 누르는 횟수 + +,-로 이동하는 횟수

            if (cnt < min) {
                min = cnt;
            }
        }

        if (min < ans) {
            ans = min;
        }
        System.out.println(ans);

    }
}
