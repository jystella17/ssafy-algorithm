def solution(picks, minerals):
    answer = 0
    print(minerals)
    
    chunk = len(minerals)//5 +1
    board = [[0,0,0] for _ in range(chunk)]
    #print(board)
    
    pickcal =0;
    for i in picks:
        pickcal += i;
        
    #돌이 더 많으면 곡괭이 만큼 컷
    cut = pickcal * 5
    if len(minerals) > pickcal:
        minerals = minerals[:cut]
    # print(minerals)
    
    for i in range(len(minerals)):
        if minerals[i] == 'diamond':    #해당 chunk에 속한 광물 체크
            board[i//5][0] += 1
        elif minerals[i] == 'iron':    #해당 chunk에 속한 광물 체크
            board[i//5][1] += 1
        elif minerals[i] == 'stone':    #해당 chunk에 속한 광물 체크
            board[i//5][2] += 1
    
    #####################################################3
    board.sort(key= lambda a:(-a[0],-a[1],-a[2]))
    print(board)   
    
    for q in board:
        
        d,i,s = q
        print(q,"here")     
        
        for z in range(len(picks)): 
            #곡괭이 수 ==> chunk의 수
            if picks[z] != 0 and z == 0 :   # 해당 곡 남아있고 그게 다이아야 ?
                picks[z] -= 1
                answer += d + i + s
                break
            elif picks[z] != 0 and z == 1 :   # 해당 곡 남아있고 그게 다이아야 ?
                picks[z] -= 1
                answer += 5*d + i + s
                break
            elif picks[z] != 0 and z == 2 :   # 해당 곡 남아있고 그게 다이아야 ?
                picks[z] -= 1
                answer += 25*d + 5*i + s
                break
            
            
    
    
    return answer
