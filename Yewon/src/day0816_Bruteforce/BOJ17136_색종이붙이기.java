package day0816_Bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17136_색종이붙이기 {
    static int[][] paper; //종이 상태
    static int[] type; // 붙일 색종이 타입 개수 배열
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = 10;
        paper = new int[N][N];
        type = new int[6]; // 0제외 1~5크기의 색종이 타입 배열
        result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }// 색종이 배열 입력 완료

        dfs(0, 0, 0);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    static void dfs(int x, int y, int count) {
        if (x == 10) {//행 탐색도 다했다.
            result = Math.min(result, count);
            return;
        }

        if (y == 10) { // 열탐색 다했다.
            dfs(x + 1, 0, count); // 다음 행의 열들 탐색할거임
            return;
        }

        if (paper[x][y] == 1) {//가려야 할 부분 발견!
            for (int size = 5; size >= 1; size--) { //5사이즈 부터 가릴거임
                if (type[size] < 5 && isPossible(x, y, size)) { //아직 색종이 개수 남았고 그 색종이 붙일 수 있는지 확인
                    cover(x, y, size, 0); //붙였다는 의미로 0으로 바꿈
                    type[size]++; //해당 색종이 썼음
                    dfs(x, y + 1, count + 1); //다음 열 탐색, 색종이 하나 썼다고 count
                    type[size]--; //반납
                    cover(x, y, size, 1); //다시 1 원상복귀
                }
            }
        } else {
            dfs(x, y + 1, count); //못찾았다. 다음 열 ㄱㄱ
        }
    }

    static boolean isPossible(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i >= 10 || j >= 10 || paper[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static void cover(int x, int y, int size, int value) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                paper[i][j] = value;
            }
        }
    }

}
