
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int ans=Integer.MAX_VALUE;

    static int[][] board;
    static boolean[] checked;
    static ArrayList<int[]> homeList = new ArrayList<>();
    static ArrayList<int[]> chickenList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        stk=new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        board =new int[N][N];


        for(int i=0; i<N; i++){
            stk=new StringTokenizer(br.readLine()," ");
            for(int j=0; j<N; j++){
                board[i][j]=Integer.parseInt(stk.nextToken());
                if(board[i][j] == 1){ //그냥 집
                    homeList.add(new int[]{i,j});
                }
                if(board[i][j] ==2){//치킨집
                    chickenList.add(new int[]{i,j});
                }
            }
        }
        checked=new boolean[chickenList.size()];//치킨집 개수 만큼 체크리스트 생성
        //nCm 조합
        comb(0,0);
        System.out.println(ans);



    }

    private static void comb(int idx, int depth) {
        if(depth ==M){
            //조합 완성
            int total=0;    //이건 조합생길때 마다 최소 도시치킨값 저장용
            for(int z=0; z<homeList.size();z++){
                //모든 집들에 대해서
                //System.out.println("집 좌표");
                //System.out.println(Arrays.toString(homeList.get(z))+" ");//이게 집들의 좌표
                int dist = Integer.MAX_VALUE;
                int hx=homeList.get(z)[0];
                int hy=homeList.get(z)[1];

                for(int i=0; i<checked.length; i++) {//여기는 조합 나온 치킨집들 반복
                    if(checked[i]) {
                        //System.out.print("clzls 좌표");
                        //System.out.println(Arrays.toString(chickenList.get(i))+" "); //여기가 치킨집 경우의 수
                        int cx=chickenList.get(i)[0];
                        int cy=chickenList.get(i)[1];
                        //System.out.println("좌표들"+hx+" "+hy+" "+cx+" "+cy);
                        //한 집이랑 조합나온 모든 치킨집들이랑 거리 비교해서 최소값을 dist에 갱신
                        dist = Math.min(dist,  Math.abs(hx-cx)+ Math.abs(hy-cy)  );
                        //System.out.println("Dist"+dist);

                    }
                }
                total+=dist;
                //System.out.println("토탈"+total);
            }
            ans=Math.min(ans,total);

            //System.out.println();
            return;


        }
        if(idx== checked.length) return;

        checked[idx]=true;
        comb(idx+1,depth+1);
        checked[idx]=false;
        comb(idx+1,depth);
    }



}
