package day0823_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ10026_적록색약 {
    static int N;
    static char[][] map;
    static boolean[][] visit;
    static int cntA, cntB;
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }// end input
        visit = new boolean[N][N];
        cntA = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visit[i][j]){
                    bfsA(i,j);
                }
            }
        }


        visit = new boolean[N][N];
        cntB = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visit[i][j]){
                    bfsB(i, j);
                }
            }
        }

        System.out.println(cntA+" "+cntB);
    }
    //일반인 구역
    private static void bfsA(int starti, int startj) {
        Queue<Point> queue = new ArrayDeque<>();
        visit[starti][startj] = true;
        queue.add(new Point(starti, startj));
        while(!queue.isEmpty()){
            Point now = queue.poll();
            for (int d = 0; d < di.length; d++) {
                int ni = now.i + di[d];
                int nj = now.j + dj[d];
                if(ni>=0 && ni<N && nj>=0 && nj<N && map[now.i][now.j] == map[ni][nj] && !visit[ni][nj]){
                    queue.add(new Point(ni, nj));
                    visit[ni][nj] = true;
                }

            }


        }
        cntA++;
    }
    //색약자 구역
    static void bfsB(int starti, int startj){
        Queue<Point> queue = new ArrayDeque<>();
        visit[starti][startj] = true;
        queue.add(new Point(starti, startj));
        while(!queue.isEmpty()){
            Point now = queue.poll();
            for (int d = 0; d < di.length; d++) {
                int ni = now.i + di[d];
                int nj = now.j + dj[d];
                if(ni>=0 && ni<N && nj>=0 && nj<N && (map[ni][nj] == map[now.i][now.j] || (map[now.i][now.j]== 'G' && map[ni][nj] == 'R')|| (map[now.i][now.j]  == 'R' && map[ni][nj] == 'G') )&& !visit[ni][nj]){
                    queue.add(new Point(ni, nj));
                    visit[ni][nj] = true;
                }

            }


        }
        cntB++;
    }
    static void print(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(visit[i][j]);
            }
            System.out.println();
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
