# Primitive Type (원시 타입)

## 자바스크립트의 6가지 원시 데이터 타입

> 객체가 아닌 것들이며 값 그 자체로 저장된 것

- Booleans true or false
- null
- undefined
- number (두 배의 정밀함을 가진 64-bit float입니다. - 자바스크립트에는 정수 타입은 존재하지 않습니다.)
- string
- symbol (ES6에서 처음 생긴 원시타입입니다.)

## Object

> Primitive Type이 아닌 것은 Object

## 원시 타입

> 원시타입은 불변적이다. (Primitive types are immutable)

원시 타입에는 어떠한 메소드도 붙어있지 않습니다. 이를테면 undefined.toString()과 같은 형태의 메소드는 본적이 없을 것입니다. 또한 이러한 특성 때문에, 원시 타입은 변하지 않는(immutable) 속성을 갖습니다. 왜냐하면 원시 타입은 자신을 변경할 수 있는 메소드를 갖지 않으니까요.

물론 변수에 원시 타입을 얼마든지 재할당 할 수 있습니다. 하지만 이것은 실제로 변수에 할당되었던 원시타입의 값이 바뀌는 것이 아니라 새로운 원시타입의 값이 들어가는 개념입니다. 원시타입 값 자체는 절대 바뀔 수 없습니다.

게다가, 원시 타입은 참조(reference)로 저장되는 Object와 다르게 값 그 자체로 저장되어 있습니다. 값이 동일한지 체크할 때 앞의 문장이 정확히 무슨 의미인지 알 수 있습니다. 아래 배열과 객체는 내용은 같지만 다른 곳을 참조하고 있기 때문에 false를 리턴합니다.

```js
"dog" === "dog"; // true
14 === 14; // true

{} === {}; // false
[] === []; // false
(function () {}) === (function () {}); // false
```

## 함수

> 함수는 특별한 프로퍼티들을 가진 새로운 형태의 객체입니다. 생성자와 콜처럼 말이죠.

```js
const foo = function (baz) {};
```

일반적인 객체와 같이 함수에 새로운 프로퍼티를 추가하는 것도 가능합니다.

```js
foo.bar = "baz";
foo.bar; // "baz"
```

함수의 이러한 특성은 함수를 1급 객체로 만듭니다. 왜냐하면 다음과 같은 조건을 만족하기 때문입니다.

1. 다른 함수의 인자값으로 넘겨질 수 있다.
2. 변수나 데이터에 할당 가능하다.
3. 객체의 리턴 값으로 리턴 가능하다.

이러한 특성은 자바스크립트의 객체들이 갖는 특성과 같습니다.

## 메소드

> 메소드는 함수와 같이 객체의 프로퍼티입니다.

```js
const foo = {};
foo.bar = function () {
  console.log("baz");
};
foo.bar(); // "baz"
```

## 생성자 함수

> 생성자 함수란 리턴 값으로 생성하는 함수를 객체 그 자체로서 반환하는 함수입니다. 같은 코드를 공유하는 여러가지 객체들을 갖고 싶다면, 생성자 함수를 삽입하는 것은 매우 좋은 선택입니다.

사실 생성자 함수는 다른 함수와 별반 다를 것이 없습니다. 그저 new라는 키워드가 붙은 이후에 생성자 함수로서 사용된다는 점과 객체 자체를 리턴한다는 점 밖에는요. 어떤 함수든 생성자 함수가 될 수 있습니다.

```js
const Foo = function () {};
const bar = new Foo();
bar; // {}
bar instanceof Foo; // true
bar instanceof Object; // true
```

생성자 함수는 object를 리턴하게 됩니다. object에 새로운 프로퍼티들을 할당하기 위해 this를 함수의 몸통 안에서 사용할 수 있습니다. 우리가 "baz"값으로 초기화된 bar라는 프로퍼티를 가진 object를 많이 만들고 싶다면 그 로직을 캡슐화하는 Foo라는 생성자 함수를 만들 수 있겠죠.

```js
const Foo = function () {
  this.bar = "baz";
};
const qux = new Foo();
qux; // { bar: "baz" }
qux instanceof Foo; // true
qux instanceof Object; // true
```

> 새로운 오브젝트를 만들기 위해서 생성자 함수를 사용할 수 있습니다.

new 키워드 없이 단순히 Foo()라는 함수를 실행한다면 Foo는 일반적인 함수처럼 동작할 것입니다. 그리고 위의 코드에서 함수 내부에 들어있는 this라는 키워드는 '실행 컨텍스트(Execution Context)'와 응답을 주고 받습니다. 그래서 만일 우리가 Foo()라는 함수를 전역 컨텍스트에서 실행시키게 되면, 전역 컨텍스트 시점의 this 인 window 객체에 bar라는 프로퍼티가 추가됩니다.

```js
Foo(); // undefined
window.bar; // "baz"
```

반대로 말하자면, 일반 함수를 생성자 함수로 실행한다면 함수의 역할을 한다기보다 그저 새로운 함수 오브젝트를 반환할 뿐이라는 거죠. 우리가 본 것처럼 말이죠.

```js
const pet = new String("dog");
// pet은 원시 타입의 "dog" 값을 갖는 것이 아니라 생성자 함수로 생성된 String 객체를 갖게 됩니다.
```

## 래퍼 오브젝트 (Wrapper Object, 포장 오브젝트)

> String, Number, Boolean, Function 와 같은 원시타입을 new 키워드로 생성하면 원시타입에 대한 래퍼 오브젝트(Wrapper Object)가 생성됩니다.

String은 문자열이 인자로 들어왔을 때, 원시 문자열(Primitive String)을 생성하는 전역 함수입니다. String 은 인자로 들어온 값을 문자열로 바꾸려합니다.

```js
String(1337); // "1337"
String(true); // "true"
String(null); // "null"
String(undefined); // "undefined"
String(); // ""
String("dog") === "dog"; // "true"
typeof String("dog"); // "string"
```

하지만 new 키워드를 붙인다면 String 은 여전히 생성자 함수로도 쓰일 수 있죠.

```js
const pet = new String("dog");
typeof pet; // "object"
pet === "dog"; // false
```

그리고 위의 생성자는 래퍼 오브젝트(Wrapper Object)라고 불리는 새로운 Object를 만들 것입니다.
위의 코드에서 새로운 Object는 "dog"이라는 문자열을 다음과 같은 프로퍼티로 나타냅니다.

```js
{
0: "d",
1: "o",
2: "g",
length: 3
}
```

래퍼 오브젝트(Wrapper Objects)는 오브젝트 래퍼(Object Wrappers)라는 이름으로도 자주 불립니다.

## 오토박싱(Auto-Boxing)

흥미로운 것은 원시 타입 문자열 생성자와 일반 오브젝트 생성자 둘 다 String 함수를 이용한다는 것입니다. 더욱 흥미로운 것은 원시 문자열 타입에서 .constructor 를 이용하여 생성자 프로퍼티를 확인할 수 있다는 것입니다. 우리는 원시 타입은 메소드를 가질 수 없다고 배웠었는데 어떻게 된 일일까요?

```js
const pet = new String("dog");
pet.constructor === String; // true
String("dog").constructor === String; // true
```

여기서 오토박싱이라 불리는 일이 벌어집니다. 우리가 특정한 원시타입에서 프로퍼티나 메소드를 호출하려 할 때, 자바스크립트는 처음으로 이것을 임시 래퍼 오브젝트로 바꾼 뒤에 프로퍼티나 메소드에 접근하려 합니다. 중요한 것은 이 과정에서 원본에는 아무런 영향을 미치지 않습니다.

```js
const foo = "bar";
foo.length; // 3
foo === "bar"; // true
```

위의 예에서, length 라는 프로퍼티에 접근하기 위해 자바스크립트는 foo를 오토박싱하고 이것을 래퍼 오브젝트에 넣습니다. 그리고 래퍼 오브젝트의 length 프로퍼티에 접근하고 값을 이용한 뒤에는 지워버립니다. 이 모든 과정은 foo라는 원시타입 변수에 전혀 영향을 미치지 않습니다. foo는 여전히 그저 원시 타입 문자열일 뿐입니다.

이러한 일련의 과정은 우리가 원시 타입에 프로퍼티를 할당하려고 할 때 자바스크립트가 왜 아무런 경고나 에러메시지를 출력하지 않는지를 알려줍니다. 원시 타입은 프로퍼티를 가질 수 없는데도 말이죠. 왜냐하면 프로퍼티를 할당할 때 잠시 원시 타입을 이용한 Wrapper Object(래퍼 오브젝트)를 만들고 거기에 할당하기 때문입니다.

```js
const foo = 42;
foo.bar = "baz"; // Assignment done on temporary wrapper object
foo.bar; // undefined
```

만일 undefined나 null과 같이 래퍼 오브젝트가 없는 원시 타입에 대해서 프로퍼티를 할당하려고 하면 자바스크립트는 에러메시지를 나타낼 것입니다.

```js
const foo = null;
foo.bar = "baz"; // Uncaught TypeError: Cannot set property 'bar' of null
```

## 요약

- 자바스크립트의 모든 것이 Object(객체)인 것은 아니다.
- 자바스크립트에는 6개의 원시 타입이 존재한다.
- 원시 타입이 아닌 것들은 모두 Object(객체)이다.
- 함수는 단순히 특별한 타입의 Object(객체)일 뿐이다.
- 함수는 새로운 Object(객체)를 만들기 위해 사용될 수 있다. (생성자 함수)
- Strings, Booleans, Numbers 는 원시 타입이면서 오브젝트이다. (래퍼 오브젝트를 갖는다.)
- 자바스크립트 내부에 존재하는 오토박싱(Autoboxing)이라는 기능 때문에 몇몇 원시 타입들 (Strings, Numbers, Booleans) 는 Object (객체)처럼 동작한다.
