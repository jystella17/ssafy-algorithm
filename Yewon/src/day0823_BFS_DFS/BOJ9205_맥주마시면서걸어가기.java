package day0823_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9205_맥주마시면서걸어가기 {
    static int N;
    static Point[] coordinate;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=TC; tc++){
            N = Integer.parseInt(br.readLine()); // 편의점 개수
            coordinate = new Point[N+2];
            visit = new boolean[N+2];
            st =  new StringTokenizer(br.readLine());
            coordinate[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); // 집 좌표
            for(int i=1;i<=N; i++){
                st = new StringTokenizer(br.readLine());
                coordinate[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); // 편의점 좌표
            }
            st = new StringTokenizer(br.readLine());
            coordinate[N+1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); //락페 좌표

            System.out.println(bfs(coordinate[0]));

        }
    }
    static String bfs(Point start){
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(start);
        visit[0] = true;
        while(!queue.isEmpty()){
            Point now = queue.poll();
            if(now.i == coordinate[N+1].i && now.j == coordinate[N+1].j) return "happy";
            for(int i=1;i<N+2;i++){
                Point next = coordinate[i];
                if(Math.abs(now.i - next.i) + Math.abs(now.j - next.j) <= 1000 && !visit[i]){
                    queue.offer(next);
                    visit[i] = true;
                }
            }

        }

        return "sad";
    }

    static class Point{
        int i, j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}
