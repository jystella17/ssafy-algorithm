package day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 크리스마스 선물
public class BOJ14235 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder()); //우선순위 큐 라이브러리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 0){
                if(pq.isEmpty()){
                    System.out.println(-1);
                    continue;
                }
                else{
                    System.out.println(pq.poll());
                }
            }
            else{
                while(st.hasMoreTokens()){
                    pq.add(Integer.parseInt(st.nextToken()));
                }



            }
        }
    }
}
