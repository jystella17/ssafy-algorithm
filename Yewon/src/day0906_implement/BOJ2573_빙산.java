package day0906_implement;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ2573_빙산 {
    static int N, M, cnt, year;
    static int[][] map;
    static boolean[][] visit;
    static boolean end;

    static int[] di = { -1, 1, 0, 0 };
    static int[] dj = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end input

        cnt = 0;
        year = 0;

        while (true) {

            end = true;
            cnt = 0;

            //빙하가 녹음
            int[][] result = melt(map);

//            print(result);
//            System.out.println("==============");

            map = result; // 덮어쓰기

//            print(map);
//            System.out.println("------------------");

            year++;
            
            //2개로 나누어 졌는지?
            visit = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j]>0 && !visit[i][j]) {
                        end = false;
                        bfs(i,j);
                    }
                }
            }

            // 다 녹을 때까지 분리가 안됐으
            if(end) {
                year = 0;
                break;
            }
            if (cnt >= 2)
                break;


        }

        System.out.println(year);

    }// end of main

    private static void bfs(int si, int sj) {
        ArrayDeque<Point> queue = new ArrayDeque<>();
        visit[si][sj] = true;
        queue.offer(new Point(si, sj));
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ni = now.i + di[d];
                int nj = now.j + dj[d];

                if(ni>=0 && ni<N && nj>=0 && nj < M && map[ni][nj] > 0 && !visit[ni][nj]) {
                    queue.offer(new Point(ni, nj));
                    visit[ni][nj] = true;
                }

            }

        }
        cnt++;
    }

    static int[][] melt(int[][] arr){
        int[][] copy = deepCopy(arr);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + di[d];
                        int nj = j + dj[d];
                        if(ni>=0 && ni < N && nj>=0 && nj < M && map[ni][nj] == 0) {
                            if(copy[i][j] > 0){
                                copy[i][j]--;
                            }
                        }
                    }
                }
            }
        }




        return copy;
    }

    private static int[][] deepCopy(int[][] arr) {
        int[][] copy = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
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
        int i, j;

        public Point(int i, int j) {
            super();
            this.i = i;
            this.j = j;
        }

    }
}
