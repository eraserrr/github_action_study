## mutable(변경가능) vs immutable(변경불가능) 객체
- mutable : list, set, dic, 사용자정의클래스  ---> call by reference
- immutable : int, float, bool, str, tuple, unicode ---> call by value
- 1. x = 100
- 2. x += 1
- 1 과 2의 x 의 id값(메모리주소)는 다르다
- immutable 객체를 가리키는 변수는 값이 변경될 때 다른 객체를 가리키게 된다

-> 얕은복사(값들만 가져오기) 깊은복사(같은객체가리키기) 참고글
https://wikidocs.net/16038

