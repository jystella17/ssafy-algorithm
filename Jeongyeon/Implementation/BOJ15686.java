// 백준 G5 치킨 거리
// C++ -> java 번역중

import java.util.*;
import java.io.*;

public class Main {

    static int toDelete, answer, n, m;
    static int[] store;
    static int[][] city;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        city = new int[n+1][n+1];
        store = new int[n];
        int chicken = 0;
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if(city[i][j] == 2) chicken++;
            }
        }

        toDelete = chicken - m;
        chickenDistance(toDelete);
        System.out.println(answer);
    }

    static void chickenDistance(int toDelete) {
        if(toDelete == 0) {
            int distance = 0;
            for(int k=0; k<n; )
        }
        
    }
}
