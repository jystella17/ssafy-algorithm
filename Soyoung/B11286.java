import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class B11286 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(o1, o2) -> (o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0]));
				
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1 ; i <= n ; i++) {
			
			int command = Integer.parseInt(br.readLine());
			
			if(command != 0) {
				pq.add(new int[]{command, Math.abs(command)});
			} else {
				if(pq.isEmpty()) {
					sb.append(0).append('\n');
				} else {
					sb.append(pq.poll()[0]).append('\n');
				}
			}
		}
		
		System.out.println(sb);
	}

}
