package day0823_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17281_야구 {

    static int[][] plan;
    static int N, ans;
    static int[] order;
    static boolean[] select;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        order = new int[10]; //1~9 인덱스 사용
        select = new boolean[10];
        plan= new int[N][10];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                plan[i][j] = Integer.parseInt(st.nextToken());
            }
        }// end input;
//        print();

        ans = Integer.MIN_VALUE;
        order[4] = 1; // 4번타자는 1번선수라는 뜻
        select[1] = true; // 1번선수는 이미 골랐다는 뜻
        choose(1);

        System.out.println(ans);

    }

    private static void choose(int idx) {
        if(idx == 10){
//            System.out.println(Arrays.toString(order));

            go();
            return;
        }

        if(idx == 4){
            choose(idx+1);
            return;
        }
        for (int i = 2; i < 10; i++) { // 2번 선수부터 9번선수까지 순서정하기 (순열)
            if(!select[i]){
                order[idx] = i;
                select[i] = true;
                choose(idx+1);
                select[i] = false;
            }
        }

    }

    static void print(){
        for (int i = 0; i < plan.length; i++) {
            for (int j = 0; j < plan[0].length; j++) {
                System.out.print(plan[i][j]+" ");
            }
            System.out.println();
        }

    }

    static void go(){
        int inning = N;
        int score = 0;
        int batter = 1; // 1번타자부터 시작
        int[] ru = new int[4];
        for (int i = 0; i < inning; i++) {
            int out = 0;
            ru = new int[4]; // 루 초기화
//            System.out.println("다음 시작: " + batter);
            while(true){
                if(out == 3) break;
                switch(plan[i][order[batter]]){
                    case 0:
                        out++;
                        break;
                    case 1:
                        score += ru[3];
                        ru[3] = ru[2];
                        ru[2] = ru[1];
                        ru[1] = 1;
                        break;
                    case 2:
                        score += ru[3]+ ru[2];
                        ru[3] = ru[1];
                        ru[2] = 1;
                        ru[1] = 0;
                        break;
                    case 3: // 3루타
                        score += ru[3] + ru[2] + ru[1];
                        ru[3] = 1;
                        ru[2] = 0;
                        ru[1] = 0;
                        break;
                    case 4:
                        score += ru[3] + ru[2] + ru[1]+1;
                        ru[3] = 0;
                        ru[2] = 0;
                        ru[1] = 0;
                        break;
                }
                batter = batter+1 == 10 ? 1 : batter+1;
            }//end while
        }//end inning

        if(ans < score){
//            System.out.println(Arrays.toString(order));
            ans = score;
        }

    }


}
