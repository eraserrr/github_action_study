linked list 가 가장 먼저 생각이 나서 linked list로 구현을 하였었는데, 

heapq 로 해도 heapq 삽입삭제 연산이 logN 밖에 안되기 때문에 구현이 가능할 것 같다.

커서까지의 목록을 max_heap 에 , 그 밑 목록을 min_heap 에 넣고 구현하였다.

위는 최대힙이고 아래는 최소힙이기 때문에 서로 pop push를 할 때에 우선순위 바꾸는 과정이 필요했다

진짜 기억하기!!!!!!!!!!!

linked list 가 삽입삭제 O(1) 이고 

그 다음에 또 고려할 수 있는 것은 heapq!!!

게다가 이 문제는 두개의 힙 당 하나의 제일 큰값/작은값만 연산에 필요하기 때문에

삽입 삭제도 빠르고 두 힙간 이동이 편한 heapq를 떠올렸으면 좋았을 것 같다.

```
import heapq
# heapq 써보기
def solution(n, k, cmd):
    max_heap = [(-x,x) for x in range(k+1)]
    min_heap = [(x,x) for x in range(k+1, n)]
    z = []
    # cursor 는 heap1 의 맨 아래
    print([x[1] for x in max_heap], [x[1] for x in min_heap])

    for c in cmd:
        a = c.split(' ')
        if a[0]=='D':
            down = int(a[1])
            for i in range(down):
                poped = heapq.heappop(min_heap)
                poped = (-poped[0],poped[1])
                heapq.heappush(max_heap,poped)
                print([x[1] for x in max_heap], [x[1] for x in min_heap])

        if a[0]=='U':
            up = int(a[1])
            for i in range(up):
                poped = heapq.heappop(max_heap)
                poped = (-poped[0],poped[1])
                heapq.heappush(min_heap, poped)
                print([x[1] for x in max_heap], [x[1] for x in min_heap])
        if a[0]=='C':
            deleted = heapq.heappop(max_heap)
            z.append(deleted)
            # 맨 위 값을 삭제했을 경우에는 아래 힙에서 커서 가져오기
            if not max_heap:
                poped = heapq.heappop(min_heap)
                poped = (-poped[0], poped[1])
                heapq.heappush(max_heap, poped)
            print("C->", [x[1] for x in max_heap], [x[1] for x in min_heap])

        if a[0]=='Z':
            deleted = z.pop()
            # 복원할 값이 현재 커서의 값보다 작으면 위의 힙에다가 놓기
            if max_heap[0][1] > deleted[1]:
                heapq.heappush(max_heap, deleted)
            # 복원할 값이 현재 커서의 값보다 크면 아래의 힙에다가 놓기
            else:
                deleted = (-deleted[0], deleted[1])
                heapq.heappush(min_heap, deleted)
            print("Z->",[x[1] for x in max_heap], [x[1] for x in min_heap])
    check = []
    while max_heap:
        check.append(heapq.heappop(max_heap)[1])
    check = check[::-1]

    while min_heap:
        check.append(heapq.heappop(min_heap)[1])
    print(check)
    answer = ''
    idx = 0
    for i in range(n):
        if check[idx]==i:
            idx += 1
            answer += 'O'
        else:
            answer += 'X'
    return answer

print(solution(8,2,["D 2","C","U 3","C","D 4","C","U 2","Z"]))
```
