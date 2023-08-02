package day0726;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


//4949. 균형잡힌 세상 / 실버 4
public class BOJ4949 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String line = br.readLine();
            if(line.equals(".")){
                break;
            }
            System.out.println(solve(line));

        }

    }

    public static String solve(String s){
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            if(c == '[' || c == '(') {
                stack.push(c);

            }


            if(c == ')'){
                if(stack.isEmpty() || stack.peek() != '('){ //인덱스가 0이다 -> 쌍이 될 좌측기호가 없다. 마지막 문자가 [ 이다.-> 쌍이 안맞다
                    return "no";
                } else{
                    stack.pop();

                }
            }else if(c == ']'){
                if(stack.isEmpty() || stack.peek() != '[') {
                    return "no";
                }
                else {
                    stack.pop();
                }
            }



        }


        if(stack.isEmpty()){
            return "yes";
        }else{
            return "no";
        }

    }

}
