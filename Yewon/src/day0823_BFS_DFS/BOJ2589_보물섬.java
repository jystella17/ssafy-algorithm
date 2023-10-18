package day0823_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2589_보물섬 {
    static int ans,len, R, C;
    static char[][] map;
    static boolean[][] visit;

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map =new char[R][];
        visit = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'L'){
                    len = 0;
                    visit = new boolean[R][C];
                    bfs(i,j);
                    ans = Math.max(ans, len-1); //왜냐면 코드에서 맨 마지막에 4방을 찾지 못해도 너비가 ++되고 종료되기 때문
                }
            }
        }
        System.out.println(ans);

    }
    static void bfs(int si, int sj){
        Queue<Point> queue = new ArrayDeque<>();
        visit[si][sj] = true;
        queue.offer(new Point(si,sj));
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point now = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int ni = now.i + di[j];
                    int nj = now.j + dj[j];

                    if(ni>=0 && ni < R && nj>=0 && nj< C && map[ni][nj] == 'L' && !visit[ni][nj]){
                        queue.offer(new Point(ni, nj));
                        visit[ni][nj] = true;
                    }

                }

            }
            len++;
        }
    }

    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}
