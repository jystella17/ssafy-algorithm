package day1018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class POG광물캐기 {

    static int[][] fatigue;
    static List<mines> list;

    public static int solution(int[] picks, String[] minerals) {
        int answer = 0;

        list = new ArrayList<mines>();
        fatigue = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        int pickCount = Arrays.stream(picks).sum(); // 곡괭이의 총 개수

        // 각각의 광물 상황에 대해 (5개의 쌍) 각 곡괭이로 캤을 때의 피로도를 리스트에 저장할 것임
        for (int i = 0; i < minerals.length; i += 5) {
            if (pickCount == 0) break; // 더 곡괭이가 없으면

            int dia = 0, iron = 0, stone = 0;
            for (int j = i; j < i + 5; j++) {
                if (j == minerals.length) break;

                String mineral = minerals[j];

                int val = 0;
                if (mineral.equals("stone")) val = 2;
                else if (mineral.equals("iron")) val = 1;
                else if (mineral.equals("diamond")) val = 0;

                dia += fatigue[0][val];
                iron += fatigue[1][val];
                stone += fatigue[2][val];

            }

            list.add(new mines(dia, iron, stone));
            if (list.size() == pickCount) break;

        }

        Collections.sort(list);

        for (mines m : list) {
            int dia = m.diamond;
            int iron = m.iron;
            int stone = m.stone;

            if (picks[0] > 0) {
                answer += dia;
                picks[0]--;
                continue;
            }
            if (picks[1] > 0) {
                answer += iron;
                picks[1]--;
                continue;
            }
            if (picks[2] > 0) {
                answer += stone;
                picks[2]--;
                continue;
            }
        }

        return answer;
    }

    static class mines implements Comparable<mines> {
        int diamond, iron, stone;


        @Override
        public String toString() {
            return "mines [diamond=" + diamond + ", iron=" + iron + ", stone=" + stone + "]";
        }


        public mines(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }


        @Override
        public int compareTo(mines o) { // 돌 기준으로 내림차순 정렬
            return o.stone - this.stone;
        }


    }


}
