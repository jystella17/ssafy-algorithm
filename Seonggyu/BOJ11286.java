package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>();//양수
        PriorityQueue<Integer> npq = new PriorityQueue<>(Collections.reverseOrder());//음수일때
        for(int i=0;i<N;i++){
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());

            if(n==0){//0 들어오고
                if(pq.isEmpty() && npq.isEmpty()){//둘다 비어있으면
                    System.out.println("0");
                }
                else{
                    if(pq.isEmpty()) {//양수부분이 없으면?
                        System.out.println(npq.poll()); //음수에서 front꺼냄
                    }else if(npq.isEmpty()) {//음수부분이 없으면?
                        System.out.println(pq.poll()); //양수에서 front꺼냄
                    }else{//둘다있으면?
                         int res =  ((Math.abs(pq.peek()) >= Math.abs( npq.peek()) )?npq.poll():pq.poll());
                         System.out.println(res);
                    }



                }
            }else if(n>0){// 양수면 pq에 add
                pq.add(n);
            }else{//음수면 npq에 add
                npq.add(n);
            }



        }
    }
}
