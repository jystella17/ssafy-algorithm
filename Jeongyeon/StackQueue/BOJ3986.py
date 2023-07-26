# 백준 S4 좋은 단어
# Stack 활용

import sys

n = int(sys.stdin.readline())
count = 0

for _ in range(n):
    words = list(sys.stdin.readline())
    words = words[:len(words)-1]
    good = []
    
    for i in range(len(words)):
        if not good:
            good.append(words[i])
            continue
            
        if good[-1] == words[i]:
            good.pop()
            
        else:
            good.append(words[i])
            
    if not good:
        count += 1

print(count)
