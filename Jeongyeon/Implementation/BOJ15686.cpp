// 백준 G5 치킨 배달
// 조합 & 백트래킹 응용 => 폐쇄할 치킨집을 선택하는 경우의 수를 조합으로 구하고
// 남아있는 치킨집들 중 가장 가까운 치킨집까지의 치킨 거리 계산

#include <iostream>
#include <vector>
#include <cstdlib>
#include <algorithm>
using namespace std;

int n, m, to_close, answer = 987654321;
vector<pair<int, int>> house, chicken;
bool is_closed[13] = {false, };
int city[51][51];
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

void chicken_distance(int idx, int further){
    if(further == 0){
        int distance = 0;
        // 각 집에서의 치킨 거리 구해서 더하기 = 도시의 치킨 거리
        for(int i=0; i<house.size(); i++){
            int x = house[i].first;
            int y = house[i].second;
            
            int cur_dist = 987654321;
            for(int j=0; j<chicken.size(); j++){
                if(is_closed[j] == true) continue;
                
                int diff = abs(x - chicken[j].first) + abs(y - chicken[j].second);
                cur_dist = min(cur_dist, diff);
            }
            
            distance += cur_dist;
        }
        answer = min(answer, distance);
        return;
    }
    
    if(idx == chicken.size()) return;
    
    is_closed[idx] = true;
    chicken_distance(idx+1, further-1);
    is_closed[idx] = false;
    chicken_distance(idx+1, further);
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    
    cin>>n>>m;
    int num_stores = 0;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin>>city[i][j];
            
            if(city[i][j] == 1)
                house.push_back(make_pair(i, j));
            
            if(city[i][j] == 2){
                num_stores++;
                chicken.push_back(make_pair(i, j));
            }
        }
    }
    int to_delete = num_stores - m;
    chicken_distance(0, to_delete);
    cout<<answer<<"\n";

    return 0;
}
