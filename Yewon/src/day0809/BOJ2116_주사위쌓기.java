package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2116_주사위쌓기 {
	private static int N;
	private static int[][] dices;
	private static int[] pair = {5,3,4,1,2,0};
	private static int Result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		dices = new int[N][6];
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<6;j++){
				dices[i][j] = Integer.parseInt(st.nextToken());
			}
		}//입력 완료
		Result = -1;
		//1번 주사위 윗면 정하기
		for(int i=0; i<6;i++){
			int max = 0;
			for(int j=0; j<6; j++){ //옆면 max 값 구하기, top이거나 pair이면 continue
				if(j==i || j == pair[i]) continue;
				max = Math.max(max, dices[0][j]);
			}
			recur(dices[0][i], max, 1);
		}
		System.out.println(Result);
	}//end main

	public static void recur(int top, int sum, int cnt){
		if (cnt == N){
			Result = Math.max(Result, sum);
			return;
		}
		//이전 주사위의 윗면 값을 가진 다음 주사위의 인덱스를 찾아야함
		int idx = 0;
		for(int i=0;i<6;i++){
			if(dices[cnt][i] == top) {
				idx = i;
				break;
			}
		}
		//이전 윗면 값의 인덱스 반대 값이 다음 윗면 값이 된다.
		int nt = pair[idx];
		//옆면을 계산할거임
		int side = 0;
		for(int i=0;i<6;i++){
			if(i == nt || i == idx) continue;
			side = Math.max(side, dices[cnt][i]);
		}
		recur(dices[cnt][nt], sum+side, cnt+1);

	}
}
