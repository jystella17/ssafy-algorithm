//백준 S1 괄호의 값
import java.io.;
import java.util.Stack;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
		char[] bracket = bf.readLine().toCharArray();
		StackCharacter stack = new StackCharacter();
		int answer = 0, current = 1;
		
		for (int i=0; ibracket.length; i++) {
		    if (bracket[i] == '(') {  여는 괄호는 무조건 stack.push()
		        stack.push(bracket[i]);
		        current = 2;  괄호가 열리면 괄호값 곱하기
		    }
		    
		    else if (bracket[i] == '[') {
		        stack.push(bracket[i]);
		        current = 3;
		    }
		    
		    else if (bracket[i] == ')') {  닫는 괄호인 경우, 정상적인 입력인지 체크
		        if (stack.empty()  stack.peek() != '(') {
		             empty stack이거나 괄호를 완성할 수 없는 경우 비정상 입력
		            answer = 0;
		            break;
		        }
		        
		        else if (bracket[i-1] == '(') {  현재 괄호열이 최소 단위인 () or []이면
		            answer += current;  내부에 곱셈할 대상이 없으므로 단순 덧셈
		        }
		        
		        stack.pop();
		        current = 2;  괄호가 닫히면 괄호값 한번 나누기
		    }
		    
		    else {
		        if (stack.empty()  stack.peek() != '[') {
		            answer = 0;
		            break;
		        }
		        
		        else if (bracket[i-1] == '[') {
		            answer += current;
		        }
		        
		        stack.pop();
		        current = 3;
		    }
		}
		
		if (!stack.isEmpty()) {
		    answer = 0;
		}
		System.out.println(answer);
	}
}
