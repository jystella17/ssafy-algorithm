package Seonggyu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ3986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        stk = new StringTokenizer(br.readLine());
        Stack<Character> stack = new Stack<>();

        int N = Integer.parseInt(stk.nextToken());


        int res=0;
        for (int t=0; t<N;t++){

            String str = br.readLine();

            stack.push(str.charAt(0));

            for(int i=1; i<str.length();i++){
                char c = str.charAt(i);

                if (!stack.isEmpty()){
                    if(stack.peek() == c){
                        stack.pop();

                    }else{
                        stack.push(c);
                    }
                }else{
                    stack.push(c);
                }
            }

            if(stack.isEmpty()){
                res+=1;
            }
            stack.clear();


        }
        System.out.println(res);



    }
}
