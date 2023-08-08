import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int[][] board = new int[N][6];
		int[][] mainboard = new int[N][6];
		
		int[] pos = {0,1,2,3,4,5};
		int[] oppos = {5,3,4,1,2,0};
		int ans =Integer.MIN_VALUE;
		
		
		for (int i =0; i<N; i++) {
			stk = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<6; j++) {
				mainboard[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		//board=mainboard;
		for (int z=1; z<=6; z++) {//1층 주사위 중 위를 보는 거

			//Deep copy
			for(int i=0; i<N;i++){
				board[i]=mainboard[i].clone();
			}

			int target=z;

			
			for(int i=0; i<N; i++) {
				for(int j=0; j<6; j++) {
					
					if(board[i][j]==target) {//타겟 찾으면
						//System.out.println(board[i][j]);
						board[i][j]=0; //비우고
						target = board[i][oppos[j]]; //마주보는 부분 으로 타겟 변경후
						board[i][oppos[j]] =0; //마주보는 부분도 0
						//System.out.println(j+"  "+oppos[j]);
						break;
					}
				}
			}
			//만들어진거에서 층마다 최대값 찾음
			int temp=0;
			for(int i=0; i<N; i++){
				Arrays.sort(board[i]);
				temp+=board[i][5];	//층의 마지막에 최대값 있는거 층마다 temp에 더함
			}
			ans = Math.max(temp,ans);	//최대값 갱신


		}

		System.out.println(ans);
//		for(int i=0; i<N;i++) {
//
//			System.out.println(Arrays.toString(board[i]));
//		}
		
		
		
	}

}
