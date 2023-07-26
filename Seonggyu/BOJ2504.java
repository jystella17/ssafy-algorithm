package Seonggyu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> stack= new Stack<>();
        StringBuilder sb = new StringBuilder();
        int res=1;
        int ans =0;
        for (int i=0; i<str.length();i++){
//            if(i != 0){
//                System.out.println(res +" "+ans+" "+ str.charAt(i-1));
//            }

            char c = str.charAt(i);

            if (c =='('){
                stack.push(c);
                res*=2;
            } else if (c =='[') {
                stack.push(c);
                res*=3;
            }  else if (c==')') {//닫았는데?
                //System.out.println("++++++++++++++");
                if(stack.isEmpty() || stack.peek() != '('  ){//짝 안맞으거나 비어있으면 컷
                    ans=0;
                    break;
                }else if(str.charAt(i-1)=='('){// 짝 ㅇㅋ 근데? 원래 내앞에 바로 여는거면
                    //.out.println("JJJJJ");
                    ans+=res;   //더해줌
                }
                stack.pop();
                res/=2;
            }

            else if (c==']') {//닫았는데?
                if( stack.isEmpty() || stack.peek()!='['){//짝 안맞으거나 비어있으면 컷
                    ans=0;
                    break;
                }else if(str.charAt(i-1)=='['){// 짝 ㅇㅋ 근데? 원래 내앞에 바로 여는거면
                    ans+=res;   //더해줌
                }
                stack.pop();
                res/=3;
            }


        }
        //System.out.println(ans);
        //System.out.println(stack);
        if(!stack.isEmpty()) sb.append(0).append("\n");
        else sb.append(ans).append("\n");
        System.out.println(sb);


    }
}
