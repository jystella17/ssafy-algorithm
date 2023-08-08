package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503_로봇청소기 {
    static int N, M, rooms[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rooms = new int[N][M];
        st = new StringTokenizer(br.readLine());
        int ni = Integer.parseInt(st.nextToken()); //로봇 청소기 시작 행
        int nj = Integer.parseInt(st.nextToken()); //로봇 청소기 시작 열
        int d = Integer.parseInt(st.nextToken()); //로봇 청소기가 바라보는 방향 (0:북쪽, 1:동쪽, 2:남쪽, 3:서쪽)
        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, 1, 0, -1};
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                rooms[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //입력 end
        int count = 0; // 청소 칸 개수
        while (true) {

            //현재 칸이 청소되어있지 않으면 청소한다
            if (rooms[ni][nj] == 0) {
                rooms[ni][nj] = 2;
                count++;
            }
            //주변 4칸 탐색 (청소 됐거나, 벽이거나
            if ((rooms[ni + di[0]][nj + dj[0]] == 2 || rooms[ni + di[0]][nj + dj[0]] == 1)&& (rooms[ni + di[1]][nj + dj[1]] == 2 ||rooms[ni + di[1]][nj + dj[1]] == 1 )&& (rooms[ni + di[2]][nj + dj[2]] == 2 || rooms[ni + di[2]][nj + dj[2]] == 1)&& (rooms[ni + di[3]][nj + dj[3]] == 2 || rooms[ni + di[3]][nj + dj[3]] == 1)) {
                if (rooms[ni - di[d]][nj - dj[d]] == 1) break; //벽이면 작동 중지
                ni = ni - di[d]; //후진
                nj = nj - dj[d]; //후진
            }
            else {
                d = (d == 0 ? 3 : d - 1);//반시계 회전
                if (rooms[ni + di[d]][nj + dj[d]] == 0) {
                    ni = ni + di[d];
                    nj = nj + dj[d];
                }
            }
            //주변 4칸이 전부 청소되어있으면 -> 바라보는 방향을 유지 한 채 한칸 후진 -> 청소/ 후진 불가하면 작동 중지
            //주변 4칸 중 0인 부분이 있으면 -> 반시계방향 90도 회전 그 방향 앞이 0이면 한 칸 전진 -> 청소
        }
        System.out.println(count);

    }

    static void print(){ //확인용
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(rooms[i][j]+" ");
            }
            System.out.println();
        }
    }

}
