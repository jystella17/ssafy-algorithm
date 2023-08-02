package day0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//절댓값 힙
public class BOJ11286 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> absHeap = new PriorityQueue<>((o1, o2) -> { //정렬 기준을 여기서 세웁니다. 람다 식
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);

            if(abs2 == abs1) return o1-o2;
            return abs1 - abs2;
        });
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n;i++){
            int x = Integer.parseInt(br.readLine());
            if(x == 0){
                if(absHeap.isEmpty()){
                    System.out.println(0);
                }
                else{
                    System.out.println(absHeap.poll());
                }
            }
            else{
                absHeap.add(x);
            }
        }




    }
}
