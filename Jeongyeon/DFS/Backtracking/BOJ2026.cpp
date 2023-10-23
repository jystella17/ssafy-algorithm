// 백준 G2 소풍
// 주의할 점: 현재 선택한 학생과 기존에 선택한 학생들 간의 친구 관계 확인

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int k, n, f;
bool flag = false, visited[901], go_picnic[901];
bool friends[901][901] = {false, };

bool is_friend(int current){
    for(int i=1; i<=n; i++){
        if(visited[i]){
            if(!friends[current][i])
                return false;
        }
    }
    return true;
}

void picnic(int student, int cnt){
    if(flag) return;
    
    if(cnt == k){
        fill_n(go_picnic, n+1, false);
        
        for(int i=1; i<=n; i++){
            if(visited[i])
                go_picnic[i] = true;
        }
        flag = true; return;
    }
    
    // 현재 학생과 연결된 학생들
    for(int conn=student+1; conn<=n; conn++){
        if(!friends[student][conn]) continue;
        if(visited[conn]) continue;
        
        // conn(현재 추가하려는 학생)과 기존에 선택한 학생들이
        // 모두 친구인지 체크하는 코드 필요
        if(!is_friend(conn)) continue;
        
        visited[conn] = true;
        picnic(conn, cnt+1);
        visited[conn] = false;
    }
}

int main(){
    cin>>k>>n>>f;
    for(int i=0; i<f; i++){
        int a, b; cin>>a>>b;
        friends[a][b] = true;
        friends[b][a] = true;
    }
    
    // 첫번째로 포함시킬 학생 선택
    for(int i=1; i<=n; i++){
        if(flag) break;
        fill_n(visited, n+1, false);
        
        visited[i] = true;
        picnic(i, 1);
        visited[i] = false;
    }
    
    if(!flag)
        cout<<-1<<"\n";
    
    else{
        for(int i=1; i<=n+1; i++){
            if(go_picnic[i])
                cout<<i<<"\n";
        }
    }
    return 0;
}
