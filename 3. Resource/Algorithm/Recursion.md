# 재귀(Recursion)

## 재귀란?

- 자기 자신을 호출하는 절차

## 재귀 활용예시

- JSON.parse , JSON.stringify
- document.getElementById , DOM traversal apgorithms
- 복잡한 구조를 가진 데이터를 다룰 때

<aside>
💡 때로는 재귀함수를 사용않는게 가독성이나 효율성이 더 좋다.

</aside>

## Javascript Call Stack

```jsx
function takeShower(){
	return "showering";
}

function eatBreakfast(){
	let meal = cookFood();
	return `Eating ${meal}`;
}

function cookFood(){
	let items = ['Oatmeal','Eggs','Protein'];
	return items[Math.floor(Math.random()*items.length)];
}

function wakeUp(){
	takeShower();
	eatBreakfast();
	console.log("go to work")
}

wakeUp();
```

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
    function sumRange(num){
    	if(num === 1) return 1; //종료지점
    	return num + sumRange( num - 1 );// 다른 입력값과 다른 반환값
    }
    
    sumRange(5);
    ```
    
- 예시3)
    
    ```jsx
    function factorial(num){
    	if(num === 1) return 1;
    	return num * factorial( num - 1 );
    }
    
    factorial(5);
    ```
    

## 재귀의 잠재적 위험

- 종료조건을 잘못 설정
- 종료 조건으로 향하는 다른 값을 입력하지 않았을때
- 잘못된 값을 반환할 때

⇒ stack overflow 발생

## Helper method Recursion

- outer function
    
    외부 함수를 개발자 또는 사용자가 호출
    
- inside function
    
    내부에서 재귀 함수 선언 및 호출
    
- 함수를 호출한 다음에도 변수를 저장해서 전역변수 형태로 계속 사용하기 위해서 사용
    - 예시)
    
    ```jsx
    function collectOddValues(arr){
    	let result = [];
    	
    	function helper(helperInput){
    		if(helperInput.length === 0){
    			return;
    		}
    		
    		if(helperInput[0] % 2 !== 0){
    			result.push(helperInput[0]);
    		}
    
    		helper(helperInput.slice(1));
    
    	}
    
    	helper(arr)
    
    	return result;
    }
    
    collectOddValues([1,2,3,4,5,6,7,8]);
    ```
    

## 순수 재귀 Pure Recursion

- 가독성은 helper recursion이 더 좋다

```jsx
function collectOddValues(arr){
	let newArr = [];

	if(arr.length === 0){
		return newArr;
	}

	if(arr[0] % 2 !== 0){
		newArr.push(arr[0]);
	}

	// concat function or spread 연산자 사용
	newArr = newArr.concat(collectOddValues(arr.slice(1)));

	return newArr;

}

let result = collectOddValues([1,2,3,4,5,6]);
```

## 재귀함수 연습문제

- 연습1)
    
    ```jsx
    // Math.pow() function 같은 기능을 재귀로 구현
    
    function power(baseNum , expandNum){
    	if(expandNUm === 0) return 1;
    	return num * power(baseNUm,expandNum -1);
    }
    ```
    

- 연습2)
    
    ```jsx
    // 모든 배열의 값을 곱한 값 구하기
    
    function productOfArray(arr){
    	if(arr.length <= 1){
    		return arr [0];
    	}
    
    	return arr[0] * productOfArray(arr.slice(1));
    }
    ```
    

- 연습3)
    
    My Solution
    
    ```jsx
    // 피보나치 수열의 n번째 수 구하기
    
    function fib(num){
    	let prevNum = 0;
    	let curNum = 1;
    
    	function helper(count = num){
    		if(num <= 2){
    			return prevNum + curNum;
    		}
    
    		const nextNum = prevNum + curNum;
    		prevNum = curNum;
    		curNum = nextNum;
    
    		return helper();
    	}
    }
    ```
    
    Lecture  Solution
    
    ```jsx
    function fib(n){
    	if(n <=2 ) return 1;
    	return fib(n-1) + fib(n-2);
    }
    ```
    
    해설
    
    ```jsx
    			      								fib(5)
          	 fib(4)                +              fib(3)
        fib(3) + fib(2)                      fib(2) + fib(1)
    fib(2) + fib(1)
    
    fib(2) + fib(1) = 2
    fib(3) + fib(2) = 3
    fib(4) + fib(3) = 5
    ```
    
- 연습4)
    
    앞 뒤 문자열이 동일한 지 비교
    
    예시)
    
    ‘amanaplanacanalpanama’ // true
    
    ‘tacocat’ // true
    
    ‘awesome’ // false
    
    ```jsx
    function isPalindrome(text){
    	if(text.length < 2) return true;
    	if(text.slice(0,1) !== text.slice(-1)) return false;
    
    	return isPalindrome(text.slice(1,-1));
    }
    ```
    

 

- 연습5)
    
    주어진함수를 통해서 전달된 배열이 참인지 확인
    
    [1,2,3,4,5] , val ⇒ val % 2 !== 0 // true
    
    [4,6,8] , val % 2 !== 0 // false
    
    [4,6,8], val > 10 // false
    
    ```jsx
    function someRecursive(arr, callback){
    	if(arr.length === 0) return false;
      return callback(arr[0]) || someRecursive(arr.slice(1),callback);
    }
    ```
    

- 연습6)
    
    다중 배열 1차원 배열로 변환
    
    [1,2,3,[4,5]] ⇒ [1,2,3,4,5]
    
    [1,2,3,[ [ [],[4,5] ] ] ] ⇒ [1,2,3,4,5]
    
    ```jsx
    const resultArr = [];
    
    function flatten(arr){
    	if(arr.length === 0) return;
    	
    	const item = arr.shift();
    	if(Array.isArray(item)){
    		flatten(item);
    	}else{
    		resultArr.push(item);
    	}
    
    	flatten(arr);
    }
    
    const result = flatten([1,2,3,4,[5,6]]);
    ```
    

- 연습6)
    
    다차원배열을 1차원 배열로 변환
    
    ```jsx
    const result = [];
    
    function flatten(arr){
    	if(arr.length === 0) return;
    
    	const item = arr.shift();
    
    	if(Array.isArray(item)){
    		flatten(item);
    	}else{
    		resultArr.push(item);
    	}
    
    	flatten(arr);
    }
    
    flatten([1,2,3,[4,5,[6,7,8]]]);
    ```
    

- 연습7)
    
    object 의 value 중 number 만 문자열로 변경
    
    ```jsx
    function stringifyNumbers(obj){
    	for(key in obj){
    		if(typeof obj[key] === 'number'){
    			obj[key] = obj[key].toString();
    		}
    		if(typeof obj[key] === 'object' && !Array.isArray(obj[key)){
    			stringifyNumbers(obj[key]);
    		}
    
    	}
    	
    	return obj;
    }
    
    const obj = {
    	num : 1,
    	test :  [],
    	data : {
    		val : 4,
    		info : {
    			isRight : true,
    			random : 66
    		}
    	}
    }
    
    const result = stringifyNumbers(obj);
    ```
    

<aside>
💡 재귀에 대한 원리를 단순히 재호출의 개념으로만 파악하다보니 응용되는 문제들이 다른길로 돌아가는것 같다.  스택이라는 구조와 같이 파악해서 다시 호출 됨과 동시에 이전의 값을 스택으로 저장하고 있다는 것을 잊지 말자. 재귀 문제는 규칙성과 이전값을 얼마나 잘 다루느냐에 따라 다르게 풀리는 것 같다.
재귀를 사용할 때 주의 할 것은 아무리 좋은 재귀함수라도 대량의 데이터는 stack이 과도하게 쌓여 overflow가 발생 할 수 있다.
종료조건!!! , 다른 값의 전달!!!! 두가지만 기억해도 stackoverflow는 나지 않는다.

</aside>



# 재귀함수 **Recursion Function**

### **재귀 (Recursion)**

프로그래밍에서 재귀(Recursion)란 자신을 정의할 때 자기 자신을 재참조하는 것을 말한다. 따라서 재귀 함수란 함수가 호출되어 실행할 때, 함수 내부에서 자기 자신을 다시 호출하는 **재귀 호출(Recursive call)**의 형태를 말한다.

### **Recursive vs Iterative**

보통 Recursive와 Iterative가 많이 비교되곤 한다. Iterative는 '반복적인'이란 뜻을 가지고 있다. 즉, 우리가 흔히 사용하는 for문이나 forEach문과 같은 반복 연산을 가리킨다. 항상 그런 것은 아니지만 많은 경우에 Recursion으로 처리할 수 있는 문제는 Iterator로도 처리할 수 있고, 반대로 Iterator로 처리할 수 있는 것은 Recursion으로 처리할 수 있다. 어떤 방법으로 문제를 해결하느냐는 프로그래머의 마음이지만 때로는 Iterative 코드보다 Recursive 코드를 사용했을 때 더 이해하기 쉬운 코드가 될 때가 있다. 그러므로 우리는 두 가지 방법을 모두 명확하게 알고 있어야 한다.

재귀 호출을 이해하기 위해서는 **스택(Stack)**이라는 자료 구조를 먼저 살펴보는 것이 좋다. 왜냐면 우리의 컴퓨터는 호출 스택이라고 불리는 스택을 사용하여 함수를 실행하기 때문이다. 호출 스택은 일반적인 프로그래밍에서도 중요하지만 재귀를 사용할 때 더욱 중요하다.

### 재귀의 3가지 중요한 특성

모든 재귀 함수는 3가지의 중요한 특성을 갖습니다.

### 종료 조건

간단하게, `if(나쁜 값이 들어왔다면) { 정지! };`과 같이 이해하시면 됩니다. 종료 조건은 재귀의 안전장치입니다. 종료 조건을 여러분들의 긴급 브레이크처럼 생각하세요. 좋지 않은 입력 값이 들어왔을 때, 재귀가 계속하여 동작하는 것을 방지해줍니다. 위의 팩토리얼 예제에서, `if (x < 0) return;`은 우리가 설정한 종료 조건입니다. 음수의 팩토리얼을 구하는 것은 불가능합니다. 그래서 우리는 음수 입력 값이 들어왔을 때, 팩토리얼 함수가 작동하지 않길 원합니다.

### 기반 조건(Base case, 기저 상태)

간단하게, `if(이런 일이 일어난다면) { 성공! }`과 같이 이해하시면 됩니다. 이 조건 역시 재귀 함수를 멈춘다는 점을 감안하면, 기반 조건은 어쩌면 재귀의 종료조건과 비슷합니다. 하지만 종료 조건은 모든 나쁜 데이터들을 잡아낸 다는 것을 기억하세요. 반면에 기반 조건은 재귀 함수의 *목적* 입니다. 기반 조건은 주로 `if` 문 내부에 있습니다. 팩토리얼 예제에서는, `if (x === 0) return 1;`이 기반 조건이었습니다. x가 0까지 내려갔을 때, 우리는 팩토리얼을 구하는데 성공한 것입니다!

### 재귀

간단하게, 함수가 자기 자신을 호출하는 것입니다. 팩토리얼 예제에서, `return x * factorial(x -1);`부분이 실제로 재귀가 일어나는 곳입니다. 우리는 숫자 `x`가 `factorial(x-1)`함수의 결과 값으로 곱해진 어떤 값을 반환합니다.

### **팩토리얼 (Factorial) 구하기**

재귀 함수를 설명할 때 가장 많이 등장하는 예제 코드가 바로 팩토리얼 구하기이다. 팩토리얼이란 자기 자신부터 시작해서 1 감소한 숫자들을 곱한 값이다. 예를 들어서 **5!**은 **5 * 4 * 3 * 2 * 1** = 120이다.

팩토리얼을 먼저 Iterative 코드로 작성해보면 다음과 같다.

```jsx
function factorial (n) {
	var result = 1;
	for (var i = n; i >= 1; i--) {
		result *= i;
	}
	return result;
}

```

n부터 1까지의 수를 반복하여 result 변수에 곱한다. 곱셈을 해야 하므로 result 변수의 초기값은 당연히 1이어야 한다.

재귀 함수로 만들어보자. 만약에 factorial(5)부터 factorial(1)을 쭉 써보면 다음과 같을 것이다.

**factorial(5) = 5 * 4 * 3 * 2 * 1 = 5 * factorial(4);**

**factorial(4) = 4 * 3 * 2 * 1 = 4 * factorial(3);**

**factorial(3) = 3 * 2 * 1 = 3 * factorial(2);**

**factorial(2) = 2 * 1 = 2 * factorial(1);**

**factorial(1) = 1;**

재귀 함수를 작성하여 호출하면 함수는 자기 자신을 계속해서 호출하여 실행한다. 이 때 특정 조건이 되었을 때, 재귀 호출을 종료하는 문장이 반드시 하나 이상 존재해야 하는데, 이렇게 재귀 호출을 중단시키는 조건 문장을 **Base case** 또는 **Termination case** 라고 한다.

```jsx
function factorial (n) {
	if (n === 1) { // Base case, Termination case
		return 1;
	}
	return n * factorial(n - 1);
}
factorial(3); // 6

```

1. 먼저 파라미터 n의 값으로 **3**이 전달된다.
2. stack에 3을 저장하고 **factorial(3 - 1) = factorial(2)**을 실행한다.
3. n의 값으로 **2**가 전달된다. stack에 2를 저장하고 **factorial(2 - 1) = factorial(1)**을 실행한다.
4. n의 값으로 **1**이 전달된다. n이 1이면 1을 리턴하고 함수를 종료한다.
5. factorial(1)이 1을 return하고 종료하였으므로 2 * 1을 연산하고 그 값인 **2**를 return한다.
6. 리턴된 2와 3을 곱한 후 그 값인 **6**을 리턴하고 모든 함수가 종료된다.

![https://blog.kakaocdn.net/dn/dgAk2U/btqvUQ7tEFp/cc57tKac2iSZKaScLeRTck/img.png](https://blog.kakaocdn.net/dn/dgAk2U/btqvUQ7tEFp/cc57tKac2iSZKaScLeRTck/img.png)

factorial 함수에 대한 각각의 호출이 자신만의 n값의 사본을 가지고 있다는 사실을 꼭 기억해야 한다. 서로 다른 함수 호출에 대한 n값에는 접근할 수 없다.

### **재귀 함수를 사용했을 때의 단점**

위에서 살펴봤듯이 재귀 함수를 사용하면 함수의 호출이 스택에 차곡 차곡 쌓이게 되고, 위에서부터 차례대로 값을 반환하기 전에는 계속 메모리 공간을 차지하고 있다. 그렇기 때문에 호출 스택이 너무 커져서 메모리를 엄청나게 소비할 수도 있다. 이러한 이유 때문에 재귀를 사용하는 것보다 반복문을 사용했을 때 더 성능이 좋은 경우가 많다. 그러므로 상황에 따라 적절한 방법을 골라서 사용할 수 있어야 한다.