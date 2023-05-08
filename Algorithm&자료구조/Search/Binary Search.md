#search #algorithm

# 이진검색(binary Search)

- 검색 할 때 마다 반 만큼의 자료를 없앨 수 있다.
- 주의할 점은 분류된 배열을 대상으로만 작동한다.
- 배열의 중간 지점 값을 주어진 값과 비교 후 2개로 나눠진 배열에서 다시 주어진 값에 해당하는 배열에서 중간 지점 값과 비교

## 연습문제

my solution

```jsx
function binarySearch(arr,findVal){
	let idx = Math.ceil(arr.length/2)
	for(let i = 0; i<arr.length; i++){
		if(arr[idx] === findVal){
			return idx;
		}
		if(arr[idx] > findVal){
			idx = idx/2;
		}
		if(arr[idx] < findVal){
			idx = Math.ceil((idx + arr.length) / 2;)
		}
	}
}
```

lecture

```jsx
function binarySearch(arr,findVal){
	let start = 0;
	let end  = arr.length - 1;
	let middle  = Math.ceil((start + end) / 2);
	
	while(arr[middle] !== findVal && start <= end){
		if(findVal < arr[middle]){
			end = middle - 1;
		}else{
			start = middle + 1;
		}
		middle = Math.ceil((start + end) / 2);
	}

	return arr[middle] === findVal ? middle : -1;
}
```

## Big O

- Best Case : O(1)
    - 중간지점의 값을 한번에 찾은 경우
- Worst Case : O(log n)
    - 모든 지점을 체크하는 경우
    - 찾는 값이 없을때 모든 지점을 체크하게됨
    - 하지만 다른 검색과 비교했을때 매우 빠른속도를 가지고 있음