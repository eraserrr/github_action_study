## Vue 선언
- 모든 Vue 앱은 Vue함수로부터 새 Vue 인스턴스를 만드는 것부터 시작
```
<div id="app">
  {{ message }}
</div>
var app = new Vue({
  el: '#app',
  data: {
    message: '안녕하세요 Vue!'
  }
  methods: {
    reverseMessage: function () {
      this.message = this.message.split('').reverse().join('')
    }
  }
})
```
- el : 인스턴스의 유효범위
```
<div id="app">
 
</div>
{{ message }}
```
위의 예시에서 el:#app일때, message는 app을 벗어나므로 제대로 출력이되지않는다<br>


## Vue 컴포넌트
- 미리 정의된 옵션을 가진 Vue 인스턴스, 독립적이고 재사용가능, 트리로 추상화
- 등록
```
Vue.component('todo-item', {
  template: '<li>할일 항목 하나입니다.</li>'
})

var app = new Vue(...)

```
=> 미리 구상된 데이터만 가지고 렌더링 됨 -> 데이터를 동적으로 가지고오고싶음<br>
- prop을 전달받을 수 있도록 수정 (부모->자식방향으로 데이터전송가능)
- 이를 통해 같은 레벨의 컴포넌트끼리도 통신 가능
```
<div id="app-7">
  <ol>
    <!--
      이제 각 todo-item 에 todo 객체를 제공합니다.
      화면에 나오므로, 각 항목의 컨텐츠는 동적으로 바뀔 수 있습니다.
      또한 각 구성 요소에 "키"를 제공해야합니다 (나중에 설명 됨).
     -->
    <todo-item
      v-for="item in groceryList"
      v-bind:todo="item"
      v-bind:key="item.id"
    ></todo-item>
  </ol>
</div>
Vue.component('todo-item', {
  props: ['todo'],
  template: '<li>{{ todo.text }}</li>'
})

var app7 = new Vue({
  el: '#app-7',
  data: {
    groceryList: [
      { id: 0, text: 'Vegetables' },
      { id: 1, text: 'Cheese' },
      { id: 2, text: 'Whatever else humans are supposed to eat' }
    ]
  }
})
```
## Axios
- API 형식이 다양해 단순한 호출 외에도 여러 설정값을 추가하여 함께 호출가능
```
axios.get('URL').then(...).catch(...);
axios.post('URL', {data: 'data'}).then(...).catch(...);
```
```
axios({
    method: 'get',
    url: 'URL'
    ...
});
```
