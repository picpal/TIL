# 다중 포인터

> 인덱스나 위치에 해당하는 포인터나 값을 만든 다음 특정 조건에 따라 중간 지점에서부터
> 시작 지점이나 끝 지점이나 양쪽 지점을 향해 이동시키는 것
> 다중 포인터 패턴은 리팩토링의 이점을 가지게 된다.

<br />

## 포인터를 사용하지 않은 반복문

```js
function sumZero(arr) {
  for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < arr.length; j++) {
      if (arr[i] + arr[j] === 0) {
        return [arr[i], arr[j]];
      }
    }
  }
}

// Time Complexity : O (N^2)
// Space Complexity : O(1)
```

<br />

## 포인터를 사용한 리팩토링 코드

```jsx
function sumZero(arr){
	let left = 0;
	let right = arr.length - 1;

	while(left < right){
		let sum = arr[left] + arr[right];
		if(sum === 0){
			return [[arr[left] , arr[right]];
		}else if(){
			right--;
		}else{
			left++;
		}
	}
}

// Time complexity : O(n)
// Space Complexity : O(1)

// test Arr
[-3,-2,-1,0,1,2,3] // true
[-2,0,1,3] // false
[1,2,3] // false
```

<br />

## 두 개의 포인터를 사용한 값 비교

```
  i
  [1,1,1,1,1,2,3,4,5,6]
  j

      i
  [1,1,1,1,1,2,3,4,5,6]
  j

  이런 형태로 j 포인터를 한칸씩 이동 시키면서 i와 비교 후 다르면 i를 다음 칸으로 이동 시키면서
  i값을 j값으로 변경
```

```jsx
function counterUniqueValues(arr) {
  if (arr.length === 0) return 0;

  let p1 = 0;
  let p2 = 1;

  for (let i = 1; i < arr.length; i++) {
    if (arr[p1] !== arr[p2]) {
      p1++;
      arr[p1] = arr[p2];
    }
    p2++;
  }

  return p1 + 1;
}

// Time Complexity : O(n)
// Space Complexity : O(n)
```

<br />

## Set 사용

: 중복 제거가되어 고유 항목만 남게되기 때문에 Time Complexity 가 O(1)을 가지게 된다.

```jsx
const result = new Set([1, 1, 1, 1, 1, 2, 3, 4]); // 1,2,3,4
const length = result.size;
console.log(length);

// Time Complexity 가 O(1)
```
