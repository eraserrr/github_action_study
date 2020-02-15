## HashMap
문제를 풀다보면 정렬을 할때에 key와 value 값이 있고<br>
이때 value 값으로 정렬을 해야하는 경우가 생기는데 <br>
이럴때마다 정렬하기가 까다로워서 앞으로 이런 문제를 풀때마다 <br>
key와 value 를 다룰 수 있는 hash map을 정리를 해놓고 써야겠다!!!!!!!!!!!<br>
왜냐면 찾아보기가 너무 귀찮기 때문이다<br>
Map 인터페이스 중에서도 왜 하필 hashmap이냐하면 아는게 hashmap 밖에 없기때문이다..
<br>
### Map.Entry
- key와 value로 하나의 쌍을 이룸
- map의 collection-view 를 return 한다고 한다
- 하지만 참조값을 담지는 않기때문에 참조값을 담으려면 반드시 타겟 컬렉션의 iterator에서 꺼내야한다

### Map.entrySet
- map에 담겨있는 key와 value의 연결들을 반환한다.
- entrySet에 변화가 있으면 이 변화가 적용된다.

### Map의 값 변경하기
- Map.Entry interface type으로 선언된 m 변수가 hashmap.entrySet()으로 return되는
  a Set of the mappings들을 하나씩 받아서 반복을 돌면서 key와 value에 따로 접근하며
  접근 후 값이 변화하면 map에서의 값도 변화하게 된다
```
Map<String, String> hashmap = new HashMap();
hashmap.put("1", "프랑스");
hashmap.put("2", "한국");
hashmap.put("3", "미국");
hashmap.put("4", "아랍에미리트");
hashmap.put("5", "일본");

System.out.println();
System.out.println("변경전");
for (Map.Entry m : hashmap.entrySet()) {
    System.out.printf("[번호 : %s\t국가 : %s]\n", m.getKey(), m.getValue());
}

// Value가 '일본'인 entry의 Value를 '중국'으로 변경
for (Map.Entry m : hashmap.entrySet()) {
    if (m.getValue().equals("일본")) {
        m.setValue("중국");
    }
}

System.out.println();
System.out.println("변경후");
for (Map.Entry m : hashmap.entrySet()) {
    System.out.printf("[번호 : %s\t국가 : %s]\n", m.getKey(), m.getValue());
}


/*
변경전
[번호 : 1 국가 : 프랑스]
[번호 : 2 국가 : 한국]
[번호 : 3 국가 : 미국]
[번호 : 4 국가 : 아랍에미리트]
[번호 : 5 국가 : 일본]

변경후
[번호 : 1 국가 : 프랑스]
[번호 : 2 국가 : 한국]
[번호 : 3 국가 : 미국]
[번호 : 4 국가 : 아랍에미리트]
[번호 : 5 국가 : 중국]    <-- entrySet()의 return 값에 대한 변경으로 인해
                                entrySet() 재실행 시의 값이 변한 것을 확인할 수 있다
*/
```
### sort 메소드
- 배열을 쓸때에는 Arrays.sort
- list타입을 정렬할 때에는 Collections.sort 를 사용한다.

### Collections.sort 
1. sort(List<T>)<T>
2. sort(List<T>),Comparator<? super T>)<T>

=> 따라서 sort함수는 List 타입을 지원해주기때문에 hashmap을 list형태로 바꿔준다<br>
대충 이런 형태
```
List<String> list = new ArrayList();
list.addAll(map.keySet());
Collections.sort(list,new Comparator() {
    public int compare(Object o1,Object o2) {
        Object v1 = map.get(o1);
        Object v2 = map.get(o2);
        return ((Comparable) v2).compareTo(v1);
    }
```
             
