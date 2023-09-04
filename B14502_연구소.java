package GraphSearch;

import java.io.*;
import java.util.*;

public class B14502_연구소 {
    static int n, m;
    static int[][] graph;
    static int answer;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MIN_VALUE;
        makeWall(0);
        System.out.println(answer);
    }

    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();

        int[][] tmpGraph = new int[n][m];
        for(int i=0; i<n; i++){
            tmpGraph[i] = graph[i].clone();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tmpGraph[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (tmpGraph[nx][ny] == 0) {
                    tmpGraph[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tmpGraph[i][j] == 0) {
                    cnt++;
                }
            }
        }

        answer = Math.max(answer, cnt);
    }

    static void makeWall(int cnt) { // dfs
        if (cnt == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0) {
                    graph[i][j] = 1;
                    makeWall(cnt + 1);
                    graph[i][j] = 0;
                }
            }
        }
    }
}
