package day0928_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865_평범한배낭 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][K+1];

        int[] weight = new int[N+1];
        int[] value = new int[N+1];

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }



        for (int i = 1; i < N+1; i++) {
            for (int w = 1; w < K+1; w++) {
                if(weight[i] > w) {
                    dp[i][w] = dp[i-1][w];
                } else {
                    dp[i][w] = Math.max(dp[i-1][w-weight[i]]+value[i], dp[i-1][w]);
                }
            }
        }

//		print(dp);

        System.out.println(dp[N][K]);

    }

    static void print(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}

