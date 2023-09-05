import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2206 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int bfs(int[][][] visit, char[][] maps, int N, int M) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            int isCrashed = current[2];

            if (x == M - 1 && y == N - 1) {
                return visit[y][x][isCrashed] + 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    if (maps[ny][nx] == '0' && visit[ny][nx][isCrashed] == 0) {
                        q.offer(new int[]{nx, ny, isCrashed});
                        visit[ny][nx][isCrashed] = visit[y][x][isCrashed] + 1;
                    }
                    if (maps[ny][nx] == '1' && isCrashed == 0) {
                        q.offer(new int[]{nx, ny, isCrashed + 1});
                        visit[ny][nx][isCrashed + 1] = visit[y][x][isCrashed] + 1;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] maps = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            maps[i] = line.toCharArray();
        }

        int[][][] visit = new int[N][M][2];
        System.out.println(bfs(visit, maps, N, M));
    }
}