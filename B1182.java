import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1182 {
    static int[] num;
    static int S;
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        S = Integer.parseInt(str[1]);

        num = new int[N];
        str = br.readLine().split(" ");
        for (int i = 0; i < N; i++)
            num[i] = Integer.parseInt(str[i]);

        boolean[] visited = new boolean[N];
        dfs(0, 0, visited);

        res = (S == 0) ? --res : res;
        System.out.println(res);q
    }

    static void dfs(int sum, int pos, boolean[] visited) {
        if (sum == S) {
            res++;
        }

        if (pos == num.length) {
            return;
        }

        for (int i = pos; i < num.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(sum + num[i], i + 1, visited);
                visited[i] = false;
            }
        }
    }
}