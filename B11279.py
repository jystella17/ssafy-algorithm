import sys

class Heap:
    def __init__(self, data):
        self.heapArray = list()
        self.heapArray.append(None)
        if data != 0:
            self.heapArray.append(data)
    def moveUp(self, insertIndex):
        if insertIndex <= 1:
            return False
        parentIndex = insertIndex // 2
        
        if self.heapArray[insertIndex] > self.heapArray[parentIndex]:
            return True
        else:
            return False
    def insert(self, data):
        if len(self.heapArray) == 0:
            self.heapArray.append(None)
            self.heapArray.append(data)
            
        self.heapArray.append(data)
        
        insertIndex = len(self.heapArray) - 1
        while self.moveUp(insertIndex):
            parentIndex = insertIndex // 2
            self.heapArray[insertIndex], self.heapArray[parentIndex] = self.heapArray[parentIndex], self.heapArray[insertIndex]
            insertIndex = parentIndex
        
        return True
    def moveDown(self, poppedIndex):
        leftChildIndex = poppedIndex * 2
        rightChildIndex = poppedIndex * 2 + 1
        
        # 왼쪽 자식 없을 때
        if leftChildIndex >= len(self.heapArray):
            return False
        # 오른쪽 자식만 없을 때
        elif rightChildIndex >= len(self.heapArray):
            if self.heapArray[poppedIndex] < self.heapArray[leftChildIndex]:
                return True
            else:
                return False
        else:
            if self.heapArray[leftChildIndex] < self.heapArray[rightChildIndex]:
                if self.heapArray[poppedIndex] < self.heapArray[rightChildIndex]:
                    return True
                else:
                    return False
            else:
                if self.heapArray[poppedIndex] < self.heapArray[leftChildIndex]:
                    return True
                else:
                    return False
    def pop(self):
        if len(self.heapArray) <= 1:
            return None
        
        returnedData = self.heapArray[1]
        self.heapArray[1] = self.heapArray[-1]
        del self.heapArray[-1]
        poppedIndex = 1
        
        while self.moveDown(poppedIndex):
            leftChildIndex = poppedIndex * 2
            rightChildIndex = poppedIndex * 2 + 1
            
            # 왼쪽 자식만 있을 때
            if rightChildIndex >= len(self.heapArray):
                if self.heapArray[leftChildIndex] > self.heapArray[poppedIndex]:
                    self.heapArray[leftChildIndex], self.heapArray[poppedIndex] = self.heapArray[poppedIndex], self.heapArray[leftChildIndex]
                    poppedIndex = leftChildIndex
            # 왼쪽 오른쪽 자식 둘 다 있을 때
            else:
                if self.heapArray[leftChildIndex] < self.heapArray[rightChildIndex]:
                    if self.heapArray[poppedIndex] < self.heapArray[rightChildIndex]:
                        self.heapArray[rightChildIndex], self.heapArray[poppedIndex] = self.heapArray[poppedIndex], self.heapArray[rightChildIndex]
                        poppedIndex = rightChildIndex
                else:
                    if self.heapArray[poppedIndex] < self.heapArray[leftChildIndex]:
                        self.heapArray[poppedIndex], self.heapArray[leftChildIndex] = self.heapArray[leftChildIndex], self.heapArray[poppedIndex]
                        poppedIndex = leftChildIndex   
        return returnedData
 
   
numberOfInput = int(sys.stdin.readline())
heap = Heap(0)
for i in range(numberOfInput):
    number = int(sys.stdin.readline())
    if number == 0:
        if len(heap.heapArray) == 1:
            print(0)
        else:
            print(heap.pop())
    else:
        heap.insert(number)