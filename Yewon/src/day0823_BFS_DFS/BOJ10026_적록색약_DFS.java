package day0823_BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10026_적록색약_DFS {
    static int N;
    static char[][] map;
    static boolean[][] visit;
    static int cntA, cntB;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

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
                if (!visit[i][j]) {
                    dfsA(i, j);
                    cntA++;
                }
            }
        }


        visit = new boolean[N][N];
        cntB = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    dfsB(i, j);
                    cntB++;
                }
            }
        }

        System.out.println(cntA + " " + cntB);
    }

    //일반인 구역
    private static void dfsA(int nowi, int nowj) {
        visit[nowi][nowj] = true;
        for (int d = 0; d < di.length; d++) {
            int ni = nowi + di[d];
            int nj = nowj + dj[d];
            if (ni >= 0 && ni < N && nj >= 0 && nj < N && map[nowi][nowj] == map[ni][nj] && !visit[ni][nj]) {
                dfsA(ni, nj);
            }
        }
    }

    //색약자 구역
    static void dfsB(int nowi, int nowj) {
        visit[nowi][nowj] = true;
        for (int d = 0; d < di.length; d++) {
            int ni = nowi + di[d];
            int nj = nowj + dj[d];
            if (ni >= 0 && ni < N && nj >= 0 && nj < N && (map[ni][nj] == map[nowi][nowj] || (map[nowi][nowj] == 'G' && map[ni][nj] == 'R') || (map[nowi][nowj] == 'R' && map[ni][nj] == 'G')) && !visit[ni][nj]) {
                dfsB(ni, nj);
            }

        }


    }


    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(visit[i][j]);
            }
            System.out.println();
        }
    }

}
