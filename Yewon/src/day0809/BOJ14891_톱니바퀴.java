package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14891_톱니바퀴 {

    private static int[][] gear;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        gear = new int[4][8];
        for(int i=0;i<4;i++){
            String line = br.readLine();
            for(int j=0;j<8;j++){
                gear[i][j] = line.charAt(j)-'0';
            }
        }//입력 완료
        int K = Integer.parseInt(br.readLine()); //회전 횟수
        for(int i=0; i<K;i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            recur(idx-1, dir); // idx 가 1,2,3,4 라서 0,1,2,3으로 변경해줌
        }
        int result = 0;
        int score = 1;
        for(int i=0; i<4; i++){
            if(gear[i][0] == 1){
                result += score;
            }
            score += score;

        }
        System.out.println(result);


    }//end of main

    /**
     *  idx: 회전 시작 기어 인덱스, d: 방향
     */
    public static void recur(int idx, int d){
        left(idx-1, -d); //idx-1 ~ 0까지
        right(idx+1,-d); //idx+1 ~ 3까지
        rotate(idx,d); //재귀 다 보내고 내꺼 회전
    }

    public static void right(int idx, int d) {
        if(idx> 3) return;
        if(gear[idx][6] == gear[idx-1][2]) return; //극이 같은거 처리
        right(idx+1, -d);
        rotate(idx, d);
    }

    public static void left(int idx, int d) {
        if(idx < 0) return;
        if(gear[idx][2] == gear[idx+1][6]) return;
        left(idx-1, -d);
        rotate(idx, d);
    }

    public static void rotate(int idx, int d){
        if(d==1) { //시계방향 회전
            int tmp = gear[idx][7];//마지막 녀석 저장
            for(int i=7; i>0; i--) {
                gear[idx][i] = gear[idx][i-1]; //땡기기
            }
            gear[idx][0] = tmp;
        }else {
            int tmp = gear[idx][0];
            for(int i=0; i<7; i++) {
                gear[idx][i] = gear[idx][i+1];
            }
            gear[idx][7] = tmp;
        }

    }
}// end of class
