package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken());
        PriorityQueue pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<N;i++){
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());

            if(n==0){//0 들어오고
                if(pq.isEmpty()){//비어있으면
                    System.out.println("0");
                    }
                else{
                    System.out.println(pq.poll()); //front꺼냄

                }
            }else {
                pq.add(n);
            }



        }
    }
}
