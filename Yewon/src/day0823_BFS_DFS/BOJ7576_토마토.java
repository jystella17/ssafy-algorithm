package day0823_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 좌표마다 날짜 기억하기 버전, 날짜 따로 기록하기 버전 두가지로 풀어보기
 */
public class BOJ7576_토마토 {
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    static int M, N, ans, cnt;
    static int[][] map;
    static ArrayDeque<Point> start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = -1;
        start = new ArrayDeque<Point>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1){
                    start.offer(new Point(i,j));
                }
            }
        }

        bfs(start);



 ex:       for (int i = 0; i < N; i++) {
            for (int tomato: map[i]) {
                if (tomato == 0) {
                    ans = -1;
                    break ex;
                }
            }
        }

        System.out.println(ans);

    }
    private static void bfs(ArrayDeque<Point> list){
        Queue<Point> queue = new ArrayDeque<>();
        while(!list.isEmpty()){
            queue.offer(list.poll());
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            while(!(size-- == 0)){
                Point now = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int ni = now.i + di[i];
                    int nj = now.j + dj[i];
                    if(ni>=0 && ni<N && nj>=0 && nj<M && map[ni][nj] == 0){ //익지 않은 토마토
                        queue.offer(new Point(ni, nj));
                        map[ni][nj] = 1; //방문 처리 대체
                    }
                }
            }
            ans++; //너비 1 종료



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
