package day0823_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17070_파이프옮기기1 {
    static int N, ans;
    static int[][] map;

    static int[] di = {0,1,1}; //우, 좌하, 하
    static int[] dj = {1,1,0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }//end input
        ans = 0;
        if(map[N-1][N-1] == 1){
            System.out.println(0);
        }
        else{
            bfs(0,0,0,1);
            System.out.println(ans);
        }

    }

    static void bfs(int si, int sj, int ei, int ej){
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(si, sj, ei, ej));

        while(!queue.isEmpty()){
            Point now = queue.poll();
            if(now.ei == N-1 && now.ej == N-1){
                ans++;
            }
            //가로의 경우
            //1. 가로 가능, 2. 대각선 내리기 가능
            if(now.si == now.ei){
                for (int i = 0; i < 2; i++) { //0~1
                    int ni = now.ei + di[i];
                    int nj = now.ej + dj[i];
                    if(i == 1){ //대각선의 경우는 벽 체크를 3군데 해야해서
                        if(ni>=0 && ni<N && nj>=0 && nj < N && map[ni][nj] == 0 && map[ni-1][nj] == 0 && map[ni][nj-1] == 0){//범위 초과 아니고 벽이 아니면
                            queue.offer(new Point(now.ei, now.ej, ni, nj));
                        }
                    }
                    else {
                        if (ni >= 0 && ni < N && nj >= 0 && nj < N && map[ni][nj] != 1 && !(nj == N-1 && ni != N-1)) {//범위 초과 아니고 벽이 아니면
                            queue.offer(new Point(now.ei, now.ej, ni, nj));
                        }
                    }
                }
            }//end 가로 경우
            else if(now.sj != now.ej && now.si != now.ei){//대각선인 경우
                for (int i = 0; i < 3; i++) { // 0 ~ 2
                    int ni = now.ei + di[i];
                    int nj = now.ej + dj[i];
                    if(i == 1){ //대각선의 경우는 벽 체크를 3군데 해야해서
                        if(ni>=0 && ni<N && nj>=0 && nj < N && map[ni][nj] != 1 && map[ni-1][nj] != 1 && map[ni][nj-1] != 1){//범위 초과 아니고 벽이 아니면
                            queue.offer(new Point(now.ei, now.ej, ni, nj));
                        }
                    }
                    else {
                        if (ni >= 0 && ni < N && nj >= 0 && nj < N && map[ni][nj] != 1) {//범위 초과 아니고 벽이 아니면
                            if(i == 0){
                                if(nj == N-1 && ni != N-1)
                                    continue;
                            }
                            else{
                                if(ni == N-1 && nj != N-1)
                                    continue;
                            }
                            queue.offer(new Point(now.ei, now.ej, ni, nj));
                        }
                    }
                }
            }//end 대각선
            else if(now.sj == now.ej){ // 세로
                for (int i = 1; i < 3; i++) {
                    int ni = now.ei + di[i];
                    int nj = now.ej + dj[i];
                    if(i == 1){ //대각선의 경우는 벽 체크를 3군데 해야해서
                        if(ni>=0 && ni<N && nj>=0 && nj < N && map[ni][nj] != 1 && map[ni-1][nj] != 1 && map[ni][nj-1] != 1){//범위 초과 아니고 벽이 아니면
                            queue.offer(new Point(now.ei, now.ej, ni, nj));
                        }
                    }
                    else {
                        if (ni >= 0 && ni < N && nj >= 0 && nj < N && map[ni][nj] != 1) {//범위 초과 아니고 벽이 아니면
                            if(!(ni == N-1 && nj != N-1))
                                queue.offer(new Point(now.ei, now.ej, ni, nj));
                        }
                    }
                }
            }
            //System.out.println(queue.toString());
        }// 큐 end


    }

    static class Point{
        int si, sj, ei, ej;
        Point(int si, int sj, int ei, int ej){
            this.si = si;
            this.sj = sj;
            this.ei = ei;
            this.ej = ej;
        }

        @Override
        public String toString() {
            return "[si,sj: "+si+","+sj+" "+"ei,ej: "+ei+","+ej+"] ";
        }
    }
}
