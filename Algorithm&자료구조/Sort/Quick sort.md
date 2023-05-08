## 퀵 정렬 ( Quick Sort )

- 합병 정렬처럼 가정으로 시작
- 재귀를 통해 해결
- pivot의 위치를 선정할 때 될 수 있으면 중간 값을 선택하는게 최선이다. 하지만 데이터가 무었인지 알수 없는 상황이 많기 때문에 어렵다.
- 편의상 첫번째 요소를 pivot 위치로 잡고 실행

```jsx
function pivot(arr, start=0){
	function swap(array, i, j){
		var temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	// ES2015+
	// const swap = (arr,idx1,idx2) => {
	//    [arr[idx1] , arr[idx2]] = [arr[idx2] , arr[idx1]]
	// }

	var pivot = arr[start];
	var swapIdx = start;

	for(var i = start + 1; i<arr.length; i++){
		if(pivot > arr[i]){
			swapIdx++;
			swap(arr, swapIdx, i);
		}
	}
	swap(arr,start,swapIdx);
	return swapIdx;
}

function quickSort(arr, start=0, end=arr.length){
	if( start < end ){
		let pivotIndex = pivot(arr, start);

		// pivotIndex를기준으로 left 영역
		quickSort(arr,start,pivotIndex-1);
		// pivotIndex를기준으로 right 영역
		quickSort(arr, pivotIndex+1, end);
	}
	return arr;
}

const result = quickSort([4,6,9,1,2,5,3])
```

Big O

- Time Complex (Best) : O(n log n)
- Time Complex (worst) : O(n ) → 데이터가 이미 정렬된 경우에도 피벗을 사용하여 분할하는 행위를 그대로 반복하게된다. 적어도 정렬된 경우에서는 무작위로 피벗요소를 고르거나 중간에 있는 요소를 피벗 시작지점으로 선정하는게 낫다. 매번 첫번째나 마지막을 피벗으로 선정하면 최악의 시나리오가 발생할 수 있다.
- Time Complex (average) : O( )

# 정렬 알고리즘

> 데이터의 특별한 속성을 이용한 정렬
예를 들면 정수 정렬 알고리즘 이라는 그룹은 정수로만 수행
> 

## 기수 정렬 (Radix Sort)

- 직접 비교하지 않는다.
- 숫자로 작동
- 실제로 사용할 때는 이진수를 사용
- 문자열이나 이미지등도 이진형식으로 바꾸어서 사용
- 정렬시 실제 데이터는 십진법 숫자 이어야 한다.
- 숫자 크기에 대한 정보를 자릿수로 인코딩 한다.
- 자리수가 더 큰수 가 있다면 더 큰수가 된다. ( 4자리수 > 2자리수 )

작동원리

[ 1566, 4, 3556, 593, 408, 4386, 902, 7, 8157, 86, 9637, 29 ]

첫번째 그룹핑

10개의 버킷을 구성하여 마지막 자리수를 기준으로 나눠 담는다.

0 : 

1 :

2 : 902

3 : 593

4 : 4

5 : 

6 : 86, 4386, 3556, 1556

7 : 9637, 8157, 7

8 : 408

9 : 29

두번째 그룹핑

10개의 버킷을 구성하여 끝에서 두번째 자리수를 기준으로 나눠 담는다.

한자리 숫자는 2번째 수가 없기 때문에 0번쨰에 위치

0 : 408, 7 ,4, 902

1 :

2 : 29

3 : 9637

4 : 

5 : 8157 , 3556, 1556

6 : 

7 : 

8 : 86, 4386

9 : 593

이런방식으로 가장 긴 자리수의 숫자만큼 진행 

10진수 일떄라서 10개의 버킷

7진수는 7개의 버킷

2진수는 2개의 버킷  이런 방식으로 버킷을 구성

해당 번째 자리에서 숫자를 알 수 있어야 정렬이 가능하다. → 특정 조건에서만 정렬 가능

해당 번째의 수를 알기 위한 helper 함수가 필요

```jsx
function getDigit(num , i){
	return Math.floor(Math.abs(num) / Math.pow(10,i)) % 10;
}

getDigit([1,2,3,4,5] , 0); => 5
getDigit([1,2,3,4,5] , 1); => 4
```

버킷 정렬을 몇번이나 진행할 지 최대 길이의 수를 얻는 함수

```jsx
function digitCount(num){
	if(num === 0) return 1;
	return Math.floor(Math.log10(Math.abs(num))) + 1;

}

function mostDigits(){
	let maxDigits = 0;
	for(let i = 0; i<nums.length; i++){
		maxDigits = Math.max(maxDigits, digitCount(nums[i]));
	}
	return maxDigits;
}

mostDigits([1,2,3,4,123]);
```

두개의 helper함수를 활용한 기수 정렬 

- 의사코드
    - 숫자목록을 받는 함수 정의
    - 가장 큰수가 몇자리 인지 확인
    - 각 자리수 버킷을 만든다 ( 10진수는 10개의 버킷 )
    - 끝 자리수에 해당하는 숫자를 버킷에 해당하는곳에 할당
    - 기존 배열을 버킷의 값으로 변경 ( loop필요 없이 concat or spread 를 사용)
    - 그다음 끝에서 2번째 자리 숫자를 버킷에 해당하는곳에 할당
    - 기존 배열을 버킷의 값으로 변경
    - 가장 큰 수의 자리수 까지 반복

```jsx
function radixSort(nums){
	let maxDigitCount = mostDigits(nums);
	
	for(let k = 0; k < maxDigitCount; k++){
		let digitBuckets = Array.from({length:10},() => []);
		for(let i = 0; i < nums.length; i++){
			let digit = getDigit(nums[i],k);
			digitBuckets[digit].push(nums[i);
		}
		nums = [].concat(...digitBuckets);
	}

	return nums;
}
```

### Big O

- Time Complex
    
    
    | Complex | BIg O |
    | --- | --- |
    | Best | O(nk) |
    | Average | O(nk) |
    | Worst | O(nk) |
- Space Complex
    
    
    | Average | O(n + k) |
    | --- | --- |
- 일반적으로 인정되는 표기이다.
- 일반적으로 기수정렬이 비교정렬보다 빠르지만 메모리수에 저장하는 방법의 차이로 실제로는 다를 수 있다.
