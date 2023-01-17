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

## React 에서 불변성

### state는 왜 immutable하게 다뤄야 하는가

- React는 개발자가 호출한 setState로 특정 state의 값이 실제로 변경되기 전에 shouldComponentUpdate를 호출시켜 개발자로 하여금 해당 state의 기존 값과 새로운 값을 비교하여 render 호출 여부를 결정하도록 기회를 줍니다.
- 만약 다음과 같은 state set이 있고 shouldComponentUpdate를 통해 incremental의 변경에 대해 최적화한다고 합시다.

```js
state = {
  incremental: 0,
  ...
};
```

- incremental를 다음과 같은 방식으로 변경한다면

```js
this.setState({
  incremental: ++incremental,
});
```

- 위의 setState 호출 후 바로 호출될 정의한 shouldComponentUpdate에서 현재 incremental값이 변경될 값과 동일하므로 변경점을 찾지 못할 것이고 결국 false를 반환하게 될 것입니다.

- 결론적으로 state를 immutable하게 취급하라는 이유는 위의 예시와 같이 다른 lifecycle 메소드들에서 여러분이 값을 직접 지정한 state에 대해 참조가 일어날 때 컴포넌트 전체 로직이 깨질 여지가 있기 때문입니다.
- 따라서 React는 React가 해당 state를 변경하기 전까지 현재 state 값을 지정하지 않기를 권장합니다.

- 그럼에도 현재 state에 직접 변경을 가하여 최적화하고 싶은 경우
- 그 사례는 대부분 Array 타입의 state를 변경하는 경우일 것이기에 그에 대한 예시로 설명해보겠습니다. 해당 state에 데이터를 추가 혹은 삭제 시 shouldComponentUpdate에서 검사할 필요도 없이 render는 반드시 호출돼야 합니다. 이 경우 아래와 같은 배열 복사는 무의미합니다.

```js
const newItems = Array.from(this.state.items);

... // data insertion into newItems

this.setState({
  items: newItems
});
```

- 다른 메소드에서 this.state.items의 어떤 원소들의 특정 값 혹은 그 원소 자체를 바꾸고 이를 shouldComponentUpdate에서 최적화 시키고 싶은 경우, 해당 변경을 알리는 변수를 따로 두면 쉽게 구현할 수 있습니다.

```js
private onInputChange(ev: React.FormEvent<HTMLInputElement>) {
  const input = ((ev.target as HTMLInputElement).value).toLocaleLowerCase();
  for (const item of this.state.items) {
    const preDisplay = item.display
    item.display = item.nameForFiltering.includes(input); // display, nameForFiltering은 각각 items[i]의 boolean, string property
    this.hasDataModified = preDisplay != item.display;
  }
  this.setState({
    items: this.state.items
  });
}

shouldComponentUpdate(nextProps: Props, nextState: State) {
  ...
  if (this.hasDataModified) {
   this.hasDataModified = false;
    return true;
  }
}
```

### useState hook과 spread 연산자

- 함수형 컴포넌트에서 state 관리를 위한 hook useState(...)는 class형 컴포넌트 state의 특정 키/값 쌍에 대응합니다.
- 풀어서 설명하자면
  - class형 컴포넌트의 this.state.<키> == 함수형 컴포넌트의 useState(...)[0]
  - class형 컴포넌트의 this.setState({키: 값}) == 함수형 컴포넌트의 useState(...)[1](값)
- 본문에서 state setter에서 spread 연산자를 사용한 이유는 저 값이 object타입이기 때문입니다.
- 함수형 컴포넌트에서 useState로 관리하는 어떤 state 값이 object 타입이 아니거나, class형 컴포넌트일 경우 spread 연산자를 사용할 필요가 없습니다.
