#분할정복
: 문제를 나눌 수 없을 때까지 나누어서 각각을 풀면서 다시 합병하여 문제의 답을 얻는 알고리즘
문제가 분할이 가능한 경우, 2개으 문제로 나누고,, 문제들을 통합하여 문제의 답을 얻음

#장점
문제를 나눔으로써 어려운 문제를 해결할 수 있다

#단점
함수를 재귀적으로 호출 -> 오버헤드
다양한 데이터를 스택에 보관 -> 스택오버플로우, 과도한 메모리사용

#이분 검색 : 10개의 "정렬된" 배열에서 21 값을 가지는 인덱스 찾기
모든 인덱스를 검사했는지 알아보는 방법(while문 빠져나오는 조건)
-> low 의 값이 high 를 뛰어넘음

int binarySearch(int target){
  low = 0;
  high = arr.length;
  while(low<=high){
    middle = (low+high)/2;
    if(target == arr[middle]){
      return middle;
    } else if(target > array[middle]){
      low = middle + 1;
    } else {
      high = middle - 1;
    }
  }
  return -1;
}


#병합 정렬
시간복잡도 O(nlogn)

알고리즘
1. 정렬할 데이터 집합의 크기가 0또는 1이면 이미 정렬된 것으로 복, 그렇지 않다면
2. 데이터 집합을 반으로 나눈다
3. 원래 같은 집합에서 나뉘어져 나온 데이터 집합 둘을 병합해 하나의 데이터 집합으로 만든다
4. 데이터 집합이 하나가 될 때가지 3을 반복한다

<분할>
가운데를 기준으로 양쪽으로 나누는 것을 반복 (1,2 과정 반복)

<정복>
분할 된 데이터를 합치는 과정. 병합과 정렬을 한번에 한다
1. 두 데이터 집합의 크기 합만큼의 크기를 가지는 빈 데이터 집합을 만든다
2. 두 데이터 집합의 첫번째 요소부터 비교하여 더 작은 요소를 빈 데이터 집합에 추가한다. 추가된 데이터 요소는 원래 집합에서 삭제한다.
-> 이때 첫번째 요소는 삭제된 데이터를 제외한 두 데이터 집합에서 가장 첫번째에 있는 데이터를 의미
3. 두 데이터 집합의 요소가 모두 삭제될 때까지 2를 반복

/* 2개의 인접한 배열 list[left...mid]와 list[mid+1...right]의 합병 과정 */
void merge(int list[], int left, int mid, int right){
  int i, j, k, l;
  i = left; //정렬된 왼쪽 리스트에 대한 인덱스
  j = mid+1; //정렬된 오른쪽 리스트에 대한 인덱스
  k = left; //정렬될 리스트에 대한 인덱스

  /* 분할 정렬된 list의 합병 */
  while(i<=mid && j<=right){
    if(list[i]<=list[j])  //각각 첫번째 요소에 접근해 크기를 비교
      sorted[k++] = list[i++]; 
    else
      sorted[k++] = list[j++];
  }

  // 남아 있는 값들을 일괄 복사
  if(i>mid){
    for(l=j; l<=right; l++)
      sorted[k++] = list[l];
  }
  // 남아 있는 값들을 일괄 복사
  else{
    for(l=i; l<=mid; l++)
      sorted[k++] = list[l];
  }

  // 배열 sorted[](임시 배열)의 리스트를 배열 list[]로 재복사
  for(l=left; l<=right; l++){
    list[l] = sorted[l];
  }
}

// 합병 정렬
void merge_sort(int list[], int left, int right){
  int mid;

  if(left<right){
    mid = (left+right)/2 // 중간 위치를 계산하여 리스트를 균등 분할 -분할(Divide)
    merge_sort(list, left, mid); // 앞쪽 부분 리스트 정렬 -정복(Conquer)
    merge_sort(list, mid+1, right); // 뒤쪽 부분 리스트 정렬 -정복(Conquer)
    merge(list, left, mid, right); // 정렬된 2개의 부분 배열을 합병하는 과정 -결합(Combine)
  }
}

void main(){
  int i;
  int n = MAX_SIZE;
  int list[n] = {21, 10, 12, 20, 25, 13, 15, 22};

  // 합병 정렬 수행(left: 배열의 시작 = 0, right: 배열의 끝 = 7)
  merge_sort(list, 0, n-1);

  // 정렬 결과 출력
  for(i=0; i<n; i++){
    printf("%d\n", list[i]);
  }
}
