
# reduce 문법
> reduceRight 는 오른쪽에서 왼쪽으로 순회
```js
배열.reduce((누적값, 현잿값, 인덱스, 요소) => { return 결과 }, 초깃값);
배열.reduceRight((누적값, 현잿값, 인덱스, 요소) => { return 결과 }, 초깃값);
```

# reduce 덧셈 예시
```js
result = oneTwoThree.reduce((acc, cur, i) => { 
	console.log(acc, cur, i); 
	return acc + cur; 
}, 0); 
// 0 1 0 // 1 2 1 // 3 3 2 
result; // 6
```


# 비동기 프로그래밍에서의 사용
```js
const promiseFactory = (time) => { 
	return new Promise((resolve, reject) => { 
		console.log(time); setTimeout(resolve, time); 
	}); 
}; 

[1000, 2000, 3000, 4000].reduce((acc, cur) => { 
	return acc.then(() => promiseFactory(cur)); 
}, Promise.resolve()); 

// 바로 1000 
// 1초 후 2000 
// 2초 후 3000 
// 3초 후 4000
```

초깃값을 Promise.resolve()로 한 후에, return된 프로미스에 then을 붙여 다음 누적값으로 넘기면 됩니다. 프로미스가 순차적으로 실행됨을 보장할 수 있습니다.

반복되는 모든 것에는 reduce를 쓸 수 있다!!