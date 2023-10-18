package day1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ1941_소문난칠공주 {

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    static int[] dir = {-5,5,-1,1};

    static char[][] girls = new char[5][];

    static boolean[] select;// 이건 방문 체크
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            girls[i] = br.readLine().toCharArray();
        } // end input

        select = new boolean[25];
        ans = 0;

        comb(0,0);

        System.out.println(ans);

    }

    private static void comb(int idx, int cnt){
       if(cnt == 7){
           //조합은 했어
           int start = -1;
           int dasom = 0;
           for (int i = 0; i < 25; i++) {
               if(select[i]){
                   start = i;
                   if(girls[i/5][i%5] == 'S'){
                       dasom++;
                   }
               }

           }

           if(dasom < 4) return;

           //연결성 확인
           if(bfs(start)){
               ans++;
           }


           return;
       }
       if(idx == 25) { return; }

        select[idx] = true;
        comb(idx+1, cnt+1);
        select[idx] = false;
        comb(idx+1, cnt);
    }

    private static boolean bfs(int start){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        int count = 0;
        boolean[] visit = new boolean[25];
        visit[start] = true;

        while(!queue.isEmpty()){
            count++;
            int now = queue.poll();
            for (int d = 0; d < dir.length; d++) {
                if(now % 5 == 0 && d == 2) continue;
                if(now % 5 == 4 && d == 3) continue;
                if(now+dir[d] >= 0 && now + dir[d] < 25 && select[now+dir[d]] && !visit[now+dir[d]]){
                    queue.add(now+dir[d]);
                    visit[now+dir[d]] = true;
                }
            }
        }

        if(count == 7) return true;

        return false;
    }

}
