package day0823_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ2206_벽부수고이동하기 {

    static int N, M, ans;
    static int[][] map;
    static boolean[][][] visit;

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M][2];
        char[] line;
        for (int i = 0; i < N; i++) {
            line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = line[j]-'0';
            }
        } // end input

        ans = Integer.MAX_VALUE;

        if(map[N-1][M-1] == 1) {
            System.out.println(-1);
        }

        bfs(0,0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

    }

    static void bfs(int si, int sj){
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(si,sj, 1, 0)); //0: 안부숨 1+: 부숨
        visit[si][sj][0]= true;
        visit[si][sj][1] = true;

        while(!queue.isEmpty()){
            Point now = queue.poll();

            if(now.i == N-1 && now.j == M-1){
                if(ans > now.dis){
                ans = now.dis;
                }
            }


            for (int d = 0; d < 4; d++) {
                int ni = now.i + di[d];
                int nj = now.j + dj[d];

                if(ni>=0 && ni<N && nj>=0 && nj<M){ // 범위 안이야
                    if(map[ni][nj] == 1){
                        if(now.isCrash == 0){
                            visit[ni][nj][1] = true;
                            queue.add(new Point(ni,nj,now.dis+1, 1));
                        }
                    } else{
                        if(!visit[ni][nj][now.isCrash]){
                            visit[ni][nj][now.isCrash] = true;
                            queue.add(new Point(ni,nj, now.dis+1, now.isCrash));
                        }
                    }
                }
            }
        }
    }

    static class Point{
        int i, j, dis, isCrash;
        Point(int i ,int j, int dis, int isCrash){
            this.i = i;
            this.j = j;
            this.dis = dis;
            this.isCrash = isCrash;
        }
    }

}
