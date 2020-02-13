## Vue 인스턴스 
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
```
<div id="app">
    <button v-on:click="getData">Request</button>
    <p>{{message}}</p>
</div>
...
<script>
    new Vue({
        el: '#app',
        methods: {
            getData: function() {
                var self = this;
                axios.get('http://demo9729611.mockable.io')
                    .then(function(response) {
                        console.log(response);
                        console.log(response.data.msg);
                        self.message += response.data.msg;
                    }
                );
            }
        },
        data: {
            message: 'Empty Message...'
        }
    });
</script>
```
## 싱글 파일 컴포넌트 체계
- 한 html 파일에서 모든 뷰인스턴스와 컴포넌트를 선언할 경우 코드가 복잡하기때문에 이용하게 된 방식이라고 이해를 하였다
- .vue 파일로 프로젝트 구조를 구성하는 방식<br>
확장자 .vue 파일 1개는 Vue 어플리케이션을 구성하는 1개의 컴포넌트 단위
1. 화면에 표시할 요소들을 정의하는 영역 ( HTML + Vue 데이터 바인딩 )
2. Vue 컴포넌트의 내용을 정의하는 영역 (template,,data,method)
```
<script>
export default {
    name: "App"
};
</script>
```
3. 템플릿에 추가한 HTML 태그의 CSS 스타일을 정의하는 영역(style)

## 사용할 라이브러리 간단하게 정리
- Vue cli : Node js 에서 프로젝트를 생성할때 편하게 구성할 수 있도록 하는 도구<br>사용시 하나의 .vue파일이 하나의 독립적인 컴포넌트를 정의.<br>
컴포넌트 정의를 별도로 하지않고
.vue 파일에서 export default{속성} 형식으로 정의<br>이때 각 속성 안에서 => 함수를 사용하게되면 this를 인식하지 않아 지양(불확실)
- Webpack : .vue파일을 웹브라우저가 인식할 수 있게 vue파일의 형태를 변환

## http proxy 설정
Vue CLI가 생성하는 프로젝트 템플릿 코드에서는 약간의 설정 파일만 작성하면 웹팩 개발서버를 이용해 프로시 서버 기능을 사용할 수 있다. 프로젝트 최상위 디렉토리에 vue.config.js파일을 생성하고 아래의 코드를 작성한다.이렇게 하면 개발용 서버에 /api/contacts를 요청하게되면 http://localshot:3000/contacts로 요청일 전달 도니다.  만약 위의 서비스 API 서버를 로컬에서 실행하지 않는다면 위의 target 값을 http://sample.bmaster.kro.kr 으로 지정하면 된다.<br>
라고 하는데 무슨말인지 잘 모르겠음 ㅜ
```
module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localshot:3000',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}
```
