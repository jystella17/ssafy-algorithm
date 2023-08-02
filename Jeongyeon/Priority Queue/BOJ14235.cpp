// 백준 S3 크리스마스 선물

#include <iostream>
#include <queue>
using namespace std;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    
    priority_queue<int> gifts;
    int n; cin>>n;
    
    for(int i=0; i<n; i++) {
        int tmp; cin>>tmp;
        
        if(tmp == 0){
            if(gifts.empty())
                cout<<-1<<"\n";
                
            else{
                cout<<gifts.top()<<"\n";
                gifts.pop();
            }
        }
        
        else{
            while(tmp--){
                int t; cin>>t;
                gifts.push(t);
            }
        }
    }
    return 0;
}
