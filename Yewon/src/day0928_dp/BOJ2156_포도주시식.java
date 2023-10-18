package day0928_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2156_포도주시식 {

    static int n;
    static int[] grape;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grape = new int[n];
        for (int i = 0; i < n; i++) {
            grape[i] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[n];


        //기본 값
        if(n>2) {
            dp[0] = grape[0];
            dp[1] = grape[0] + grape[1];
            //  0,1 / 0,2 / 1,2  -> 중에 가장 큰 값으로 설정해야함
            dp[2] = Math.max(dp[1], Math.max(grape[0] + grape[2], grape[1] + grape[2]));

            for (int i = 3; i < n; i++) {
                dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + grape[i], dp[i - 3] + grape[i - 1] + grape[i]));
            }
        } else if(n == 1){
            dp[0] = grape[0];
        } else if (n == 2){
            dp[0] = grape[0];
            dp[1] = grape[0] + grape[1];
        }

        System.out.println(dp[n-1]);





    }


}
