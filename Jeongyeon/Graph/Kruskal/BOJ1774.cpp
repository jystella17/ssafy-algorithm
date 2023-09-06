// 백준 G3 우주신과의 교감
// Kruskal 기본

#include <iostream>
#include <vector>
#include <queue>
#include <math.h>
using namespace std;

int n, m;
double answer;
vector<int> parents;
vector<pair<int, int>> gods; // 좌표 저장
priority_queue<pair<double, pair<int, int>>> paths; // 간선 리스트 (길이, 정점)

int find(int x){
    if(x == parents[x])
        return x;
        
    return parents[x] = find(parents[x]);
}

bool merge(int a, int b){
    int pa = find(a);
    int pb = find(b);
    
    if(pa == pb) return false;
    if(pa < pb) parents[pb] = pa;
    else if(pa > pb) parents[pa] = pb;
    return true;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    cin>>n>>m; parents.push_back(0);
    
    for(int i=0; i<n; i++){
        int x, y; cin>>x>>y;
        gods.push_back(make_pair(x, y));
        parents.push_back(i+1);
    }
    
    // 현재 연결 정보 저장
    for(int i=0; i<m; i++){
        int x, y; cin>>x>>y;
        bool flag = merge(x, y);
    }

    // Kruskal => 존재할 수 있는 모든 간선 및 가중치 저장 & 오름차순 정렬
    for(int i=0; i<gods.size(); i++){
        for(int j=i+1; j<gods.size(); j++){
            double dist = pow((gods[j].first - gods[i].first), 2) + pow((gods[j].second - gods[i].second), 2);
            dist = sqrt(dist);
            paths.push(make_pair(-dist, make_pair(i+1, j+1)));
        }
    }

    // 가중치가 작은 간선부터 선택, 해당 간선의 두 노드 체크
    while(!paths.empty()){
        int a = paths.top().second.first;
        int b = paths.top().second.second;
        double dist = paths.top().first;
        paths.pop();
        
        if(merge(a, b)){ // 두 노드가 연결되어 있지 않으면 연결 처리 및 통로 길이 업데이트
            answer += abs(dist);
        }
    }
    
    printf("%.2f", answer);
    return 0;
}
