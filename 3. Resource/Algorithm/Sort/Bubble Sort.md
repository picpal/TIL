#sort

## 버블정렬(bubbleSort)

- 효율적인 정렬은 아니다.
- 다른 정렬 알고리즘과 비교 및 분석하는데 도움이 된다.

no optimize 

```jsx
function bubbleSort(arr){
	for(let i = arr.length; i > 0; i--){
		for(let j = 0; j < i - 1; j++){
			if(arr[j] > arr[j+1]){
				let temp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = temp;
			}
		}
	}

	return arr;
}
```

optimize

- 이미정렬이 다 됬음에도 계속해서 남은 항목을 정렬하기 위해 진행됨
- 정렬이 다 되었다고 판단되는 경우 loop를 멈출 수 있도록 로직 추가

```jsx
function bubbleSort(arr){
	var noSwaps;
	for(let i = arr.length; i > 0; i--){
		noSwaps = true;
		for(let j = 0; j< i - 1; j++){
			if(arr[j] > arr[j+1]){
				let temp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = temp;
				noSwaps = false;
			}
		}
		if(noSwaps) break;
	}
	return arr;
}
```

## Big O

- Time Complex : O(n^2)
- optimze time Complex : O(n)

## 선택정렬(selectionSort)

- 버블 정렬과 다르게 추출한 값을 끝에 위치시키는것이 아니라 한번에 하나씩 위치에 배열
- 정렬데이터는 처음부터 누적이 됨
- 최소값을 찾아 맨 앞에 위치 시킴

### 구현

```jsx
function selectionSort(arr){
	for(var i = 0; i<arr.length; i++){
		var lowest = i;
		for(var j = i +1; j<arr.length; j++){
			if(arr[j] < arr[lowest]){
				lowest = j;
			}
		}
				
		if( i !== lowest){ //optimize
			var temp = arr[i]
			arr[i] = arr[lowest];
			arr[lowest] = temp;
		}
	}
	return arr;
}
```

### Big O

- Time Complex : O(n^2)
- 선택정렬이 버블보다 나은 경우는 스왑수를 최소화 했을 때 뿐이다. (흔한 경우는 아님)