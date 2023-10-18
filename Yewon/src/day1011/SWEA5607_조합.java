package day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5607_조합 {

    static long[] factorial;

    static int N, R;

    static final long MOD = 1234567891;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            long ans = 1; // n!에서 r! 약분값
            for (int i = R+1; i <= N; i++) {
                ans = (ans*i) % MOD;
            }

            long nmrFac = 1; // (n-r)!
            for (int i = 1; i <= N - R ; i++) {
                nmrFac = (nmrFac*i) % MOD;
            }

            long pmt = MOD-2;

            //분할 정복
            while(pmt > 0){
                if(pmt%2 == 1){
                    ans = (ans*nmrFac) % MOD;
                }
                pmt/=2;
                nmrFac = (nmrFac*nmrFac) % MOD;
            }

            System.out.println("#"+tc+" "+ans);

        }
    }
}
