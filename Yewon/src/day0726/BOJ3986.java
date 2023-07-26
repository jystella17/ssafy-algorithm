package day0726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//3986.좋은 단어
public class BOJ3986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int count = 0; //좋은글자 개수를 담을 변수
        for(int i=0;i<N;i++){
            Stack<Character> stack = new Stack<>();
            String line = br.readLine();
            char[] chars = line.toCharArray();

            //스택작업
            for(int j=0; j<chars.length;j++){
                if(stack.isEmpty() || !stack.peek().equals(chars[j])){//스택이 비었거나 맨 위에 있는 글자가 자신과 같지 않으면
                    stack.push(chars[j]);
                }
                else if(!stack.isEmpty() && stack.peek().equals(chars[j])){//비어있지 않거나 맨위 문자가 일치하면
                    stack.pop();
                }
            }

            //스택이 비었다? => 좋은 단어다!
            if(stack.isEmpty())
                count++;

        }

        System.out.println(count);
    }
}
