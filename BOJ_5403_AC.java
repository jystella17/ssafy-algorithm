package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5403_AC {
    static int TC, N, idx;
    static String tmp, trash;
    static char func[];
    static Queue<String> queue;
    static Deque<Integer> deque;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringBuilder sb = new StringBuilder();
            deque = new ArrayDeque<>();
            func = br.readLine().toCharArray(); // 함수
            N = Integer.parseInt(br.readLine()); // 배열 수
            // st = new StringTokenizer(br.readLine(), ",");
            st = new StringTokenizer(br.readLine());

            // 1. N=0 일 경우, D가 있으면 error 리턴, 그 외 빈배열 리턴
            if (N == 0) {
                for (int k = 0; k < func.length; k++) {
                    if (func[k] == 'D') {
                        System.out.println("error");
                        break;
                    }
                    System.out.println("[]");
                }
            }

            // 2. N!=0일 경우
            else {
                tmp = st.nextToken(); // [ 넣음.

                // deque에다가 값 차례대로 넣어줌
                for (int i = 0; i < N; i++) {
                    deque.add(Integer.parseInt(st.nextToken()));
                    trash = st.nextToken(); // , 버리다가 마지막 trash는 ] 임.
                }
                idx = 0;
                for (int id = 0; id < func.length; id++) {

                    // 1) R일 경우 idx를 앞뒤로 나눠서 바꿔줌
                    if (func[id] == 'R') {
                        if (idx == 0) {
                            idx = deque.size();
                        } else {
                            idx = 0;
                        }
                    }
                    // 2) D일 경우 비어있으면 error, 아니면 idx에 따라 앞 혹은 뒤에서 제거
                    else {
                        if (deque.isEmpty()) {
                            System.out.println("error");
                            return;
                        } else {
                            if (idx == 0) {
                                deque.removeFirst();
                            } else {
                                deque.removeLast();
                            }
                        }
                    }
                }
                sb.append(tmp);
                if (idx == 0) {
                    while (!deque.isEmpty())
                        sb.append(deque.poll()).append(",");
                } else {
                    while (!deque.isEmpty())
                        sb.append(deque.pollLast()).append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(trash);
            }

        }
    }
}
