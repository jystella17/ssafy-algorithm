package day0913_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10844_쉬운계단수 {
    static int N;
    static double memo[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        memo = new double[N+1][10];
        for (int i = 1; i < 10; i++) {
            memo[1][i] = 1;
        }

        for (int i = 2; i < N+1; i++) {
            for (int j = 0; j < 10; j++) {
                if(j == 0){
                    memo[i][j] = memo[i-1][1] % 1000000000;
                }
                else if (j == 9){
                    memo[i][j] = memo[i-1][8] % 1000000000;
                }
                else{
                    memo[i][j] = (memo[i-1][j-1] + memo[i-1][j+1]) % 1000000000;
                }
            }
        }


//        print();
        double sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum+memo[N][i]) % 1000000000;
        }




        System.out.printf("%.0f", sum);

    }
    static void print(){
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                System.out.print(memo[i][j]+" ");
            }
            System.out.println();
        }
    }
}
