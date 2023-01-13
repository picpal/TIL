# 불변성 ( immutability )

## 불변성이란?

- 자바스크립트에서 불변성이란 객체가 생성된 이후 그 상태를 변경할 수 없는 것을 의미

## 불변성 immutable vs 가변 mutable

- 자바스크립트에서는 불변성을 유지하는 값들과 그렇지 않은 값들이 나누어져 있다.
- Boolean, Number, String, null, undefined, Symbol과 같은 타입들은 불변성을 유지하는 타입들이고 Object타입들은 변경가능한 값.
- 이 말은 객체는 객체 내부의 값을 변경하면 객체를 참조하고 있는 다른 값들도 다 같이 변경된다는 의미.
- 예시
  ```js
  var coke = {
    name: "coca",
    price: 2980,
  };
  var new_coke = coke;
  coke.name = "pepsi";
  console.log(coke.name, new_coke.name); //'pepsi' 'pepsi'
  ```

## 일반적으로 데이터 변경에는 두 가지 방법

1. 데이터의 값을 직접 변경
2. 원하는 변경 값을 가진 새로운 사본으로 데이터를 교체

## 객체 변경을 통해 데이터 수정하기

```js
var player = {score: 1, name: 'Jeff'};
player.score = 2;
// 이제 player는 {score: 2, name: 'Jeff'}입니다.
객체 변경 없이 데이터 수정하기
var player = {score: 1, name: 'Jeff'};

var newPlayer = Object.assign({}, player, {score: 2});
// 이제 player는 변하지 않았지만 newPlayer는 {score: 2, name: 'Jeff'}입니다.

// 객체 spread 구문을 사용한다면 이렇게 쓸 수 있습니다.
// var newPlayer = {...player, score: 2};
```

## 불변성이 왜 중요할까?

- 복잡한 특징들을 단순하게 만듦
  - ex) 특정 행동을 취소하고 다시 실행하는 기능을 만들 때 이력을 유지하고 나중에 재사용할 수 있다.
- 변화를 감지함 ( 좀더 용이함 )
  - 직접적으로 수정하면 복제가 가능한 객체를 이전 사본과 비교하고 전체 객체 트리를 순회하여 비교해야 함.
  - 불변성을 유지한 복사객체는 참조하고 있는 불변 객체가 이전 객체와 다르다면 객체는 변한 것입니다
- 순수 컴포넌트를 만드는 데 도움을 준다. 컴포넌트 랜더링에 도움을 줌

## 불변성을 위해 사용되는 라이브러리

- immer
- cloneDeep in lodash
