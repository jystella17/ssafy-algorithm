// 백준 G5 암호코드
// 케이스 구분에 주의

#include <iostream>
using namespace std;

int main(){
    int dp[5001]; int answer = -1;
    string cipher; cin>>cipher;
    
    // 잘못된 암호가 아닐 경우 (0으로 시작하는 암호) base case 설정
    if(cipher[0] != '0')
        dp[0] = 1, dp[1] = 1;
    
    for(int i=1; i<cipher.size(); i++){
        // 잘못된 암호
        if(cipher[i-1] == '0' && cipher[i] == '0'){
            answer = 0;
            break;
        }
        
        // 현재 자리만 0인 경우
        else if(cipher[i] == '0'){
            // 현재 자리가 0이면서 30 이상의 숫자가 만들어지는 경우 (잘못된 암호)
            if(cipher[i-1] - '0' > 2) dp[i+1] = 0;
            
            else dp[i+1] = dp[i-1] % 1000000;
        }
        
        // 바로 앞 자리만 0인 경우
        else if(cipher[i-1] == '0')
            dp[i+1] = dp[i] % 1000000;
        
        // 모두 0이 아닌 경우 (잘못된 암호일 가능성 없음)
        else{
            if(10*(cipher[i-1] - '0') + (cipher[i] - '0') <= 26)
                dp[i+1] = (dp[i] + dp[i-1]) % 1000000;
                
            else 
                dp[i+1] = dp[i] % 1000000;
        }
    }
    
    if(answer == 0)
        cout<<answer<<"\n";
    else
        cout<<dp[cipher.size()]<<"\n";
    return 0;
}
