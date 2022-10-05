# 재귀(Recursion)

## 재귀란?

- 자기 자신을 호출하는 절차

<br />

## 재귀 활용예시

- JSON.parse , JSON.stringify
- document.getElementById , DOM traversal apgorithms
- 복잡한 구조를 가진 데이터를 다룰 때

<aside>
💡 때로는 재귀함수를 사용않는게 가독성이나 효율성이 더 좋다.
</aside>

<br />

## Javascript Call Stack

```jsx
function takeShower() {
  return "showering";
}

function eatBreakfast() {
  let meal = cookFood();
  return `Eating ${meal}`;
}

function cookFood() {
  let items = ["Oatmeal", "Eggs", "Protein"];
  return items[Math.floor(Math.random() * items.length)];
}

function wakeUp() {
  takeShower();
  eatBreakfast();
  console.log("go to work");
}

wakeUp();
```

<br />

## 재귀 수행 원리

### 2가지 기본 원칙

- 종료 지점이 존재해야함.
- 다른 입력값을 전달해야함.
- 예시1)

  ```jsx
  function countDown(num){
  	if(num < 0){
  		console.log("all done!!");
  		return;
  	}

  	console.log(num);
  	num--;
  	countDodwn(num);
  }

  countDown(5);`
  ```

- 예시2)

  ```jsx
  function sumRange(num) {
    if (num === 1) return 1; //종료지점
    return num + sumRange(num - 1); // 다른 입력값과 다른 반환값
  }

  sumRange(5);
  ```

- 예시3)

  ```jsx
  function factorial(num) {
    if (num === 1) return 1;
    return num * factorial(num - 1);
  }

  factorial(5);
  ```

  <br />

## 재귀의 잠재적 위험

- 종료조건을 잘못 설정
- 종료 조건으로 향하는 다른 값을 입력하지 않았을때
- 잘못된 값을 반환할 때

⇒ stack overflow 발생

<br />

## Helper method Recursion

- outer function
  외부 함수를 개발자 또는 사용자가 호출
- inside function
  내부에서 재귀 함수 선언 및 호출
- 함수를 호출한 다음에도 변수를 저장해서 전역변수 형태로 계속 사용하기 위해서 사용
  - 예시)
