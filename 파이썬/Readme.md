항상 헷갈려서 활용하지 못했던 개념들 정리하는 파일!

## mutable(변경가능) vs immutable(변경불가능) 객체
- mutable : list, set, dic, 사용자정의클래스  ---> call by reference
- immutable : int, float, bool, str, tuple, unicode ---> call by value
- 1. x = 100
- 2. x += 1
- 1 과 2의 x 의 id값(메모리주소)는 다르다
- immutable 객체를 가리키는 변수는 값이 변경될 때 다른 객체를 가리키게 된다

-> 얕은복사(값들만 가져오기) 깊은복사(같은객체가리키기) 참고글
https://wikidocs.net/16038

***

## 파이썬 bytes : b'hello' 이면 인코딩 되어있는 'hello'앞에 b가 붙어있는 건가? 아니면 'hello'앞에 b를 붙이면 인코딩 한 값이 내부적으로 저장되는건가?
- ascii : 1byte 문자표
- UTF-8 : 1~3 bytes, 세계 각국의 언어 표현가능한 문자표 
- b'안녕' -> 을 실행하면 bytes는 ascii 만을 포함할 수 있다고 한다
- 즉 !! b'hello'일 때 b'hello'는 실행 시 hello 라는 ascii 로 표현될 수 있는 값을 진짜 ascii로 인코딩
- bytes : 1바이트 단위의 값을 연속적으로 저장하는 시퀀스 자료형
- 문자열 앞에 b 가 붙여져있는 모양이 byte 객체
- 파이썬에서 문자열(str)의 기본 인코딩 : UTF-8
- 'hello' 를 바이트 객체로 만들면 각 문자를 utf-8 이 아닌 ascii 로 처리
- 'hello' 
```
b'안녕'     # ERROR : bytes can only contain ASCII
bytes('안녕')     # ERROR : string argument without an encoding
bytes('안녕', encoding='utf-8')    # b'\xec\x95\x88\xeb\x85\x95'  // 타입은 bytes
'안녕'.encode()     # b'\xec\x95\x88\xeb\x85\x95' // 타입은 bytes
```
## 비트 연산자

파이썬에서 10진수를 2진수로 변환 -> bin(정수)

이 때 반환되는 값은 0b 로 시작하는 문자열

```
bin(13) #'0b1101'
type(bin(13)) #<class 'str'>
```

2진수를 10진수로 변환 -> int(이진수문자열, 2)

이 때 0b로 시작하는 문자열이든 비트만 표현된 문자열이든 상관없음

```
int('0b1101', 2) # 13
int('1101', 2) # 13
```

