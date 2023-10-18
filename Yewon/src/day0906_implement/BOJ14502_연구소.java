package day0906_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ14502_연구소 {
    static int N,M, ans;
    static int[][] map;
    static boolean[] isSelect;
    static boolean[][] visit;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isSelect = new boolean[N*M];
        ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);


        System.out.println(ans);






    }

    private static void comb(int idx, int cnt) {
        if(cnt == 3){
            int[][] copy = deepCopy(map);
            // 좌표 3개를 뽑았네? 그럼 벽 세워
            for (int i = 0; i < N*M; i++) {
                if(isSelect[i]){

                    // 근데 뽑은게 1이거나 2야? return 해
                    if(copy[i/M][i%M] == 1 || copy[i/M][i%M] == 2){
                        return;
                    }
                    copy[i/M][i%M] = 1;
                }
            }// 벽 설치 완료

//            print(copy);
//            System.out.println("==================");
            visit = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(copy[i][j] == 2 && !visit[i][j]){
                        bfs(i,j, copy);
                    }
                }
            } // 전파완료

//            print(copy);
//            System.out.println("--------------");
            // 0 개수 세기
            int zero = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(copy[i][j] == 0){
                        zero++;
                    }
                }
            }

            ans = Math.max(ans, zero);

            return;
        }
        if(idx == N*M){
            return;
        }

        isSelect[idx] = true;
        comb(idx+1, cnt+1);
        isSelect[idx] = false;
        comb(idx+1, cnt);

    }

    static void bfs(int si, int sj, int[][] arr){
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(si, sj));
        visit[si][sj] = true;
        while(!queue.isEmpty()){
            Point now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ni = now.i + di[d];
                int nj = now.j + dj[d];

                if(ni>= 0 && ni< N && nj>=0 && nj<M && arr[ni][nj] == 0){
                    queue.offer(new Point(ni,nj));
                    visit[ni][nj] = true;
                    arr[ni][nj] = 2;
                }
            }


        }


    }

    static void print(int[][] arr){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    static class Point{
        int i, j, num;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
//            this.num = num;
        }
    }

    static int[][] deepCopy(int[][] arr){
        int[][] copy = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = arr[i][j];
            }
        }

        return copy;
    }

}
