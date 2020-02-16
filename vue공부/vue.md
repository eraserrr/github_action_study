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
.vue 파일에서 export default{속성} 형식으로 정의<br>이때 각 속성 안에서 => 함수를 사용해야 this를 인식할 수 있으므로 지향(불확실)
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
## vue-cli와 axios 이용하여 개발
```
<template>
  <button @click="fetchContacts">1페이지 연락처 조회</button>
</template>
<script>
import axios from 'axios';
export default {
    name : "app",
    data() {
        return {
            ...
        }
    },
    methods : {
        fetchContacts : function() {
            axios({
                method: 'GET',
                url : '/api/contacts',
                params : {
                    page : 1,
                    pagesize : 5
                }
            }).then((response) => {
                console.log(response);
                this.result = response.data;
            }).catch((ex)=> {
                console.log("ERR!!!!! : ", ex)
            })
        }
    }
}
</script>
```
- 이때 vue 인스턴스 내부에서 axios를 컴포넌트 내 선언없이 이용하기 위해서는 main.js에 아래 내용 추가
```
import axios from '.axios'

Vue.prototype.$axios = axios;
Vue.config.productionTip = false
```
이렇게 되면 위의 fetchContactone 를 아래와 같이 변경 가능(this.$axios)
```
fetchContactOne : function() {
    this.$axios.get('/api/contacts/' + this.no)
    .then((response) => {
        this.result = response.data
    })
},
```
## Vue session 기능
- 토큰을 이용한 로그인 유지(JWT이용) : 토큰을 http 헤더에 실어 서버로 전송해 인증하는 방식
- 토큰을 만들기 위한 3가지
1. header : 정보를 암호화할 방식(alg), 타입(type)
2. payload : 서버에서 보낼 데이터, 일반적으로 유저의 고유 ID값, 유효기간이 들어감
3. verify signature : base64방식으로 인코딩한 header, payload 에 secret key를 더한 후 서명<br>
=> Encoded Header + "." + Encoded Payload + "." + Verify Signature
- 기본 JWT 방식의 강화버전인 Access token & Refresh token 방식
- refresh token
-> 처음에 로그인을 완료했을 때 access token과 동시에 발급되며, 긴 유효기간을 가지며 access token이 만료됐을때 새로 발급해주는 key 가 됨
-> access token 의 유효기간을 짧게 설정할 수 있기 때문에 보안성 강화됨

- 동작
1. 사용자 로그인
2. 서버측에서 access token과 refresh token 발급 -> 프론트로 보냄
3. refresh token 은 db에 저장
4. 사용자 접속시 refresh token을 먼저 검사--> access token 발급
- 라이브러리 : jsonwebtoken

## Promise 동작
.. 뷰공부 그만하고 싶다
- 서버에 데이터를 요청하는 코드
```
$.get('url 주소/products/1', function (response) {
  // ...
});
```
- 이때 서버에서 응답받은 요청을 받은 뒤의 동작을 promise로 구현할때는 다음과 같은 소스로 구현을 하게 됨
```
function getData(callback) {
  // new Promise() 추가
  return new Promise(function (resolve, reject) {
    $.get('url 주소/products/1', function (response) {
      // 데이터를 받으면 resolve() 호출
      resolve(response);
    });
  });
}

// getData()의 실행이 끝나면 호출되는 then()
getData().then(function (tableData) {
  // resolve()의 결과 값이 여기로 전달됨
  console.log(tableData); // $.get()의 reponse 값이 tableData에 전달됨
});
```
- promise의 세 가지 상태
1. 대기 (pending)
```
new Promise(function (resolve, reject) {
  // ...
});
```
2. 이행 (fulfilled) : 콜백 함수를 resolve
```
new Promise(function(resolve, reject) {
  resolve();
});
```
이행 상태에서는 다음과 같이 then()을 이용한 처리 결과값을 받을 수 있다
```
getData().then(function (resolvedData) {
  console.log(resolvedData); // 100
});
```
3. 실패 (rejected) : reject인자로 reject()메서드 실행시 실패 이유를 catch()로 받을 수 있음
```
function getData() {
  return new Promise(function (resolve, reject) {
    reject(new Error("Request is failed"));
  });
}

// reject()의 결과 값 Error를 err에 받음
getData().then().catch(function (err) {
  console.log(err); // Error: Request is failed
});
```
- 예시
```
function getData() {
  return new Promise(function (resolve, reject) {
    $.get('url 주소/products/1', function (response) {
      if (response) {
        resolve(response);
      }
      reject(new Error("Request is failed"));
    });
  });
}

// Fulfilled 또는 Rejected의 결과 값 출력
getData().then(function (data) {
  console.log(data); // response 값 출력
}).catch(function (err) {
  console.error(err); // Error 출력
});
```

### Vuex store
- 단일 상태 트리라고한다. 이 단일 객체는 모든 애플리케이션 수준의 상태를 포함하며 원본 소스 역할을 함. 각 애플리케이션마다 하나의 저장소만 갖게 된다
```
const app = new Vue({
  el: '#app',
  // "store" 옵션을 사용하여 저장소를 제공하십시오.
  // 그러면 모든 하위 컴포넌트에 저장소 인스턴스가 삽입됩니다.
  store,
  components: { Counter },
  template: `
    <div class="app">
      <counter></counter>
    </div>
  `
})
```
=> this..$store.count 로 접근 가능
- 상태관리 구성요소
1. state : 컴포넌트 간에 공유할 data
2. view : 데이터가 표현될 template
3. actions : 사용자의 입력에 따라 반응할 methods

- state 등록
```
import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export const store = new Vuex.Store({
  // counter라는 state 속성을 추가
  state: {
    counter: 0
  }
});
```
- getters : 함수계산해서 return 할 수 있음<br> $store.getters.함수명 으로 접근
- mutations : vuex의 데이터, state를 변경하는 로직<br>
getters와 다른점은 인자를 받아 vuex에 넘겨줄 수 있다는 것이다. 동기적 동작
```
export const store = new Vuex.Store({
  // ...
  mutations: {
    addCounter: function (state, payload) {
      return state.counter++;
    }
  }
});
```
- mutations 사용
```
methods: {
  addCounter() {
    // this.$store.state.counter++;
    this.$store.commit('addCounter');
  }
},
인자값넘기기 
this.$store.commit('addCounter',10);
```
=> commit을 이용해 mutations 이벤트를 호출한다<br>
=> 추적가능한 상태 변화를 위해 이렇게 구조화되어있음
- actions :  mutations와 비슷하지만 비동기적으로 구현됨
- 선언 (내부적으로 mutations 이용)
```
actions: {
    addCounter: function (context) {
      // commit 의 대상인 addCounter 는 mutations 의 메서드를 의미한다.
      return context.commit('addCounter');
    }
  }
```
- HTTP get 요청이나 setTimeout 과 같은 비동기 처리 로직은 actions에 선언한다
- 호출
```
methods: {
  // Mutations 를 이용할 때
  addCounter() {
    this.$store.commit('addCounter');
  }
  // Actions 를 이용할 때
  addCounter() {
    this.$store.dispatch('addCounter');
  }
},
```

