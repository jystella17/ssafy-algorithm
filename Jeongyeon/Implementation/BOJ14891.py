# 백준 G5 톱니바퀴 (삼성 A형 기출)
# 문제 꼼꼼히 읽기 & deque rotate 외우기

import sys
from collections import deque

gear = [] # 0 = N극, 1 = S극

def wheel_rotate(idx, direction, is_left):
    # 모든 톱니바퀴를 탐색했다면 종료
    if is_left and idx < 0: return
    if is_left == False and idx > 3: return
    
    # 왼쪽으로 인접한 톱니바퀴를 회전시켜야 하는 경우
    if is_left and gear[idx][2] != gear[idx+1][6]:
        wheel_rotate(idx-1, direction*-1, is_left)
        gear[idx].rotate(direction)
    
    # 오른쪽으로 인접한 톱니바퀴를 회전시켜야 하는 경우
    if is_left == False and gear[idx][6] != gear[idx-1][2]:
        wheel_rotate(idx+1, direction*-1, is_left)
        gear[idx].rotate(direction)


for i in range(4):
    wheel = list(sys.stdin.readline().replace('\n', ''))
    gear.append(deque(wheel))
    
k = int(sys.stdin.readline())
for _ in range(k):
    gear_num, clockwise = map(int, sys.stdin.readline().split())
    gear_num -= 1
    
    wheel_rotate(gear_num-1, clockwise*-1, True) # 왼쪽으로 인접한 톱니바퀴 회전
    wheel_rotate(gear_num+1, clockwise*-1, False) # 오른쪽으로 인접한 톱니바퀴 회전
    gear[gear_num].rotate(clockwise)

answer = 0
for i in range(4):
    if gear[i][0] == '1':
        answer += pow(2, i)
print(answer)
