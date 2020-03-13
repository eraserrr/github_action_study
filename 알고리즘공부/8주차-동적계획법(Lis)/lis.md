## 동적 계획법 (Dynamic Programming)
여러개의 하위 문제들을 먼저 푼 후 그 결과를 쌓아올려 문제를 해결하는 알고리즘, 문제를 해결하기 위한 점화식을 찾아낸 후 점화식의 항을 밑에서부터 차례로 구해나가 답을 알아내는 형태이다.

### 예시
최대값을 구하는 문제
-> 반복문으로 한걸음 한걸음마다 나올 수 있는 상황에서의 최대값을 구해준다.
arr[0]에서의 최대값 -> arr[1]에서의 최대값 -> arr[2]에서의 최대값
마지막에 나오는 최대값이 답

### 피보나치수열 구하기
int fibonacci(int n){
  if(n==0){
    print("0")
    return 0;
  }else if(n==1){
    print("1");
    return 1;
  }else {
    return fibonacci(n-1)+fibonacci(n-2);
  }
}

### 동적계획법으로 접근한 Lis (Longest Increasing Subsequence;최장 증가 부분 수열)
: 수열이 주어졌을 때, 수가 점점 증가하는 가장 긴 부분 수열을 찾는 문제
ex) A={10,20,10,30,20,50} 일때 Lis 는 A={"10","20",10,"30",20,"50"}

### O(n^2) 풀이
```
int[] array = new int[N];
int[] dp = new int[N];

int max = 0;
dp[0] = 1;

for(int i=1;i<N;i++){
  dp[i] = 1;
  for(int j=0;j<i;j++){
    if(array[i]>array[j] && dp[j]+1>dp[i]){ //array i j 가 증가추세 && 0..j까지도 증가인지 구분하기위한 조건
      dp[i] = dp[j] + 1; // i의 값이 j의 값+1이 되게 설정
    }
  }
  if(max < dp[i]) {
    max = dp[i];
  }
}
```
