// 프로그래머스 Lv.2 광물캐기

#include <string>
#include <vector>
#include <cmath>
#define INF 987654321
using namespace std;

int length, usable;
int answer = INF;
int pick[3];
string mineral[50];

void mining(int fatigue, int start, int cnt){
    if(start >= length || cnt == usable){
        answer = min(answer, fatigue);
        return;
    }
    
    // 현재 사용할 곡괭이 선택 & 탐색
    for(int i=0; i<3; i++){
        if(pick[i] == 0) continue;
        
        pick[i]--;
        // 선택한 곡괭이로 5개의 광물 연속 탐색
        int diff, current = 0;
        for(int p=start; p<start+5; p++){
            if(p == length) break; // 모든 광물을 캤다면 stop
        
            if(mineral[p] == "diamond")
                diff = i - 0;
        
            else if(mineral[p] == "iron"){
                diff = i - 1;
                if(diff < 0) diff = 0; // 다이아몬드 곡괭이를 사용하는 경우
            }
        
            else if(mineral[p] == "stone"){
                diff = i - 2;
                if(diff < 0) diff = 0; // 다이아몬드 or 철 곡괭이를 사용하는 경우
            }
            current += pow(5, diff);
        }

        mining(fatigue+current, start+5, cnt+1);
        pick[i]++;
    }
}

int solution(vector<int> picks, vector<string> minerals) {
    length = minerals.size();
    
    for(int i=0; i<3; i++){
        pick[i] = picks[i];
        usable += picks[i];
    }

    for(int i=0; i<length; i++)
        mineral[i] = minerals[i];

    mining(0, 0, 0);
    return answer;
}
