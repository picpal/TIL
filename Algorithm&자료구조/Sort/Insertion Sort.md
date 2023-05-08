## 삽입정렬 ( Insertion Sort )

- 한번에하나의 항목을 올바른 위치에 삽입해서 배열의 정렬된 부분을 점진적으로 구축
- 배열의 두번째 요소부터 시작
- 첫번째 요소를 정렬된 부분으로 간주
- 두번째 값을 앞의 값과 비교 후 자리 이동 결정
- 세번째 값을 첫번째 값, 두번째 값을 비교하여 위치 결정

구현

```jsx
function insertionSort(arr){
	for(var i = 1; i < arr.length; i++){
		var currentVal = arr[i];
		for(var j = i-1; j>=0 && arr[j] > currentVal; j--){
			arr[j+1] = arr[j]
		}
		arr[j+1] = currentVal;
	}

	return arr;
}
```

BigO

- Time Complex : O(n^2)
- 데이터가 거의 정렬된 경우 : O(n)
- best : O(1)

삽입정렬을 사용하는 유용한 경우

- 온라인 알고리즘이라는 데이터가 있는 경우
- 기존에 정렬된 데이터가 있고 신규 데이터가 들어와서 재 정렬되야 할 때 유용
- 전체배열을 다시 정렬할 이유가 없고 매번 새로운 데이터를 수신하는 경우

## 합병정렬 ( Merge Sort)

- 정렬된 두 배열 합병을 담당할 함수를 먼저 구현
- 정렬된 두 배열이 주어지면 정렬된 새 배열을 만들어야함
- 두 배열은 같은 방식의 정렬이 되어 있어야함. ( 오름차순, 내림차순등..)
- O(n+m) 의 복잡도를 가진다

원리

N배열 

[1,10,50]

M배열

[2,14,99,100]

1. 1과 2를 비교
2. 빈 배열에 더 작은 수 할당 [1]
3. N배열 위치를 shift하여 10으로 이동 
4. 10과 2를 비교 더 작은수 할당 [1,2]
5. M배열 위치를 shift하여 14로 이동
6. 10과 14를 비교 더 작은수 할당 [1,2,10]
7. N배열 위치를 shift하여 50로 이동
8. 50과 14를 비교 더 작은수 할당 [1,2,10,14]
9. M배열 위치를 shift하여99로 이동
10. 50과 99를 비교 더 작은수 할당 [1,2,10,14,50)
11. N배열의 위치가 더이상 shift 할 곳이 없음
12. M배열의 남은 항목 그대로 할당 [1,2,10,14,50,99,100] ( 이런 부분 때문에 비교되는 두배열이 같은 방식의 정렬을 필요로함)

구현

```jsx
function merge(arr1, arr2){
	let results = [];
	let i = 0;
	let j = 0;

	while(i < arr.length && j < arr2.length){
		if(arr2[j] > arr1[i]){
			results.push(arr1[i]);
			i++;
		}else{
			results.push(arr2[j]);
			j++;
		}
	}

	while(i < arr1.length){
		results.push(arr1[i]);
		i++;
	}

	while(j < arr2.length){
		results.push(arr2[j])
		j++;
	}

	return resuls;

}
```

Big O

- Time Complex : O(n log n)
- Space Complex : O(n)