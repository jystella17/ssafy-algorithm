package day0906_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1774_우주신과의교감 {
    static int V, E;// 우주신들의개수(정점), 통로(간선)

    static ArrayList<Edge> edgeList; // 간선들의 길이를 저장할 간선리스트
    static Point[] vertexList;
    static int[] parents; // union-find 저장할 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        vertexList = new Point[V+1];
        double ans = 0;

        for(int v=1; v<V+1; v++){
            st = new StringTokenizer(br.readLine());
            int j = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            vertexList[v] = new Point(i, j);
        } // 좌표들 다 모았음

//        System.out.println(Arrays.toString(vertexList));
        make();

        //이미 연결된 간선 길이와 union 작업 해두기
        for(int e=0;e<E;e++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

//        System.out.println(Arrays.toString(parents));

        // 만들 수 있는 간선을 다 추가할거임 (양쪽 다 해야될지 의문)
        for(int i=1;i<V;i++){
            for (int j=i+1; j<V+1; j++){
                edgeList.add(new Edge(i, j, Math.sqrt(Math.pow((vertexList[i].i - vertexList[j].i), 2) + Math.pow((vertexList[i].j - vertexList[j].j), 2))));
            }
        }

        Collections.sort(edgeList); // 오름차순 정렬
        int count = E;
        for(Edge edge : edgeList){
            if(union(edge.from, edge.to)){
//                System.out.println(Arrays.toString(parents));
                ans+= edge.dist;
                if(count == V-1) break;
                count++;
            }
        }

        System.out.printf("%.2f", ans);



    }

    static void make() {
        parents = new int[V+1];
        for (int i = 0; i < V+1; i++) {
            parents[i] = i;
        }
        //최소 단위 서로소 집합 생성 완료
    }
    static int find(int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        double dist;

        public Edge(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }


        @Override
        public int compareTo(Edge o) {
            if(this.dist > o.dist){
                return 1;
            }
            else if (this.dist == o.dist){
                return 0;
            }
            else{
                return -1;
            }
        }
    }
    static class Point{
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }
}
