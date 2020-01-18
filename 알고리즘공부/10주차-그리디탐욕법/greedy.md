## 그리디 알고리즘
미리 정한 기준에 따라서 매번 가장 좋아보이는 답을 선택하는 알고리즘
최적화 문제를 푸는데 사용
동적계획법보다 효율적이지만 반드시 최적의 해를 구하지는 못한다

### 알고리즘
1. 해 선택 : 지금 당시에 가장 최적인 해를 구한뒤, 이를 집합에 추가
2. 적절성 검사 : 새로운 부분해 집합이 적절한 지 검사
3. 해  검사 : 새로우 부분해 집합이 문제의 해가 되는지 검사. 아니라면 1번으로 감

### 선택조건
- 앞의 선택이 이후의 선택에 영향을 주지 않는 조건
- 문제에 대한 최종 해결 방법이 부분 문제에 대해서도 또한 최적 문제 해결 방법이라는 조건

### 프로그래머스 - 구명보트
```
public int solution(int[] people, int limit) {
    int answer = 0;
    int[] check = new int[people.length];

    Arrays.sort(people); //배열 오름차순 정렬
    for(int i=0;i<check.length;i++) // check 배열 1로 초기화
        check[i] = 1;

    for(int i=0;i<check.length-1;i++){ //i 가 0부터 n-1
        if(check[i]==0)
            continue;
        int idx= -1;
        for(int j=i+1;j<check.length;j++){ // j는 1부터 n까지
            if(check[j]==0)
                continue;
            //System.out.println(i+","+j);

            if(people[i] + people[j] <= limit){
                idx = j;
            }
        }
        if(idx==-1) { // 더할만한 인자가없음
            //System.out.println(i + "->" + i);
            answer++;
            continue;
        }
        check[idx] = 0;
        //System.out.println(i+","+idx+"->"+people[i] + people[idx]);
        answer++;
    }
    if(check[check.length-1]==1)
        answer++;
    return answer;

}
```
  
처음 코드였는데 시간초과가 나서 다른 소스코드를 참고해보았다
내껀 시간복잡도가 O(nlogn) 이다
소스코드를 바꿔서 돌려보면!?! 뚜둔
```
public int solution(int[] people, int limit) {
    int answer = 0;
    Arrays.sort(people); //배열 오름차순 정렬

    int i=0;
    for(int j=people.length-1;j>=i;j--){
        if(i==j) {
            answer++;
            break;
        }
        if(people[i]+people[j]<=limit){
            i++;
            answer++;
        }
        else
            answer++;
    }
    return answer;
}
```
됐다!!
이 코드에서 주의할점은 i==j 가 되는 시퀀스에서는 어차피 남은 개수가 하나일때 경우이므로 처리를 따로 해줘야한다는 것이다
정말 머리가 좋은 사람이 많으것 
