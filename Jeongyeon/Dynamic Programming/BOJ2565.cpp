// 백준 G5 전깃줄

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main(){
    vector<pair<int, int>> electro;
    electro.push_back(make_pair(0, 0));
    int conn[501]; conn[0] = 0;
    
    int answer = 0;
    int n; cin>>n; 
    for(int i=0; i<n; i++){
        int start, end;
        cin>>start>>end;
        electro.push_back(make_pair(start, end));
        conn[i+1] = 0;
    }
    
    // 출발 지점 index 기준 오름차순 정렬
    sort(electro.begin(), electro.end());
    
    for(int i=1; i<=n; i++){
        for(int j=0; j<i; j++){
            // 지점 i에서 출발하는 전깃줄에 대해 
            // 지점 1 ~ i-1에서 출발하는 전깃줄이 교차하지 않는 경우
            if(electro[i].second > electro[j].second)
                conn[i] = max(conn[i], conn[j] + 1);
        }
        answer = max(answer, conn[i]);
    }

    cout<<n - answer<<"\n";
    return 0;
}
