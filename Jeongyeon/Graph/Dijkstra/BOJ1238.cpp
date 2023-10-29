// 백준 G3 파티
// 시간복잡도를 O((N+M) * logN)으로 줄이는 방법 고민

#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int n, m, x, answer = 0;
vector<vector<pair<int, int>>> road(1001); // i = 출발, first = 도착, second = 소요시간

int find_shortest(int start, int end){
    priority_queue<pair<int, int>> pq; // first = 소요시간, second = 도착
    vector<int> distance(n+1, 987654321);
    
    distance[start] = 0;
    pq.push(make_pair(0, start));
    
    while(!pq.empty()){
        int cost = -pq.top().first;
        int cur = pq.top().second;
        pq.pop();
        
        for(int i=0; i<road[cur].size(); i++){
            int next = road[cur][i].first;
            int duration = cost + road[cur][i].second;
            
            // 기존보다 적은 시간이 걸리는 방법인 경우 값 업데이트
            if(duration < distance[next]) {
                distance[next] = duration;
                pq.push(make_pair(-duration, next));
            }
        }
    }
    return distance[end];
}

int main(){
    ios_base::sync_with_stdio(false);
	cin.tie(0);	cout.tie(0);
    
    cin>>n>>m>>x;
    for(int i=0; i<m; i++){
        int s, e, t;
        cin>>s>>e>>t;
        
        road[s].push_back(make_pair(e, t));
    }
    
    
    for(int i=1; i<=n; i++){
        // i -> x
        int current = find_shortest(i, x);
        // x -> i
        current += find_shortest(x, i);

        // 현재까지 이동 시간의 최댓값
        answer = max(answer, current);
    }
    cout<<answer<<"\n";
    return 0;
}
