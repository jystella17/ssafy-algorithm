// 백준 G3 괄호 추가하기
// DFS+재귀 응용 (조합 코드 활용)

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int answer = -2147483648, bound;
vector<char> sign;
vector<int> operand;

int calculate(int a, int b, char op){
    if(op == '+')
        return a+b;
        
    else if(op == '-')
        return a-b;
        
    else if(op == '*')
        return a*b;
        
    else
        return a/b;
}

void bracket(int idx, int result){
    // 수식의 마지막까지 탐색했다면 탐색 종료  
    if(idx >= bound){
        answer = max(answer, result);
        return;
    }
    
    // 괄호를 추가하지 않는 경우
    int tmp = calculate(result, operand[idx+1], sign[idx]);
    bracket(idx+1, tmp);
    
    // 현재 수식에 괄호를 추가하는 경우 => idx+1이면 중첩 괄호가 되므로 idx+2
    if(idx < bound-1){
        int cur = calculate(operand[idx+1], operand[idx+2], sign[idx+1]);
        int tmp = calculate(result, cur, sign[idx]);
        bracket(idx+2, tmp);
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int n; cin>>n;
    bound = n/2;
    
    for(int i=0; i<n; i++){
        if(i%2 == 1){
            char tmp; cin>>tmp;
            sign.push_back(tmp);
        }
        else{
            int tmp; cin>>tmp;
            operand.push_back(tmp);
        }
    }

    bracket(0, operand[0]);
    cout<<answer<<"\n";
    return 0;
}
