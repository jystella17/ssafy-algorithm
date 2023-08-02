// 백준 G5 강의실 배정
// Priority Queue 2개 사용

import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<Pair> timeTable = new PriorityQueue<Pair>(); // 시작 시간 오름차순
    static PriorityQueue<Pair> classRoom = new PriorityQueue<Pair>(); // 종료 시간 오름차순
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for (int i=0; i<n; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int s = Integer.parseInt(st.nextToken());
		    int t = Integer.parseInt(st.nextToken());
		    
		    timeTable.add(new Pair(s, t));
		}
		
		// 시간 1일 때 시작하는 수업 저장
		classRoom.add(new Pair(timeTable.peek().second, timeTable.peek().first));
		timeTable.remove();
		
		for (int i=0; i<n-1; i++) {
		    // 다음 수업의 시작 시간보다 먼저 종료하는 수업이 있다면
		    if (classRoom.peek().first <= timeTable.peek().first) {
		        classRoom.remove(); // classRoom에서 해당 수업 제거
		    }
		    
		    // 다음 수업을 classRoom에 저장
		    classRoom.add(new Pair(timeTable.peek().second, timeTable.peek().first));
		    timeTable.remove();
		}
		System.out.println(classRoom.size());
	}
}

class Pair implements Comparable<Pair> {
    int first, second;
    
    Pair(int s, int t) {
        this.first = s;
        this.second = t;
    }
    
    public int compareTo(Pair p) {
        if(this.first < p.first) {
            return -1;
        }
        else if(this.first == p.first) {
            if(this.second < p.second) {
                return -1;
            }
        }
        return 1;
    }
}
