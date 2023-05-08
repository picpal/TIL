# 기준점간 이동 배열 패턴

## 문제

- 주어진 숫자 만큼 연속된 숫자의 가장 큰 합계
- 예시)
  배열 : [ 1,2,5,2,8,1,5]
  숫자 : 2
  ⇒ 연속된 2개의 가장 큰 합계 : 2,8의 합 10이 출력
  배열 : [1,2,5,2,8,1,5]
  숫자 : 4
  ⇒ 연속된 4개의 가장 큰 합계 : 5,2,8,1의 합 17이 출력
- Worst Solution

  많은 양의 배열이 주어지게 되면 시간이 무한정 늘어나게 된다.

  ```jsx
  function(arr,num){
  	if(num > arr.length){
  		return null;
  	}

  	var max = -Infinity; /*음수의 합계도 담아야 하는 경우가 생겨서 0으로 처리 안함*/
  	let temp;
  	for(let i = 0; i< arr.length - num + 1; i++) {
  		temp = 0;
  		for(let j = 0; j < num; j++){
  			temp += arr[i + j];
  		}
  		if(temp > max){
  			max = temp;
  		}
  	}

  	return max;
  }

  // Time Complexity : O(N^2)
  ```

- Best Solution

  전체 배열을 한번만 탐색해서 값을 찾기 때문에 위의 방식보다 효율적이다.

  원리 : 포인터가 이동되면서 이전과 다음 값이 다르게 되는 숫자들을 더하고 빼는 과정으로 처리

  ```js
  [ 2,6,9,2,1,8,5,6,3]
    -     +
      -     +
        -     +
  ```

  ```jsx
  function maxSubarraySum(arr, num) {
    let maxSum = 0;
    let tempSum = 0;

    if (arr.length < num) return null;

    // 첫번째 합계를 구함
    for (let i = 0; i < num; i++) {
      maxSum += arr[i];
    }

    // 첫번째 합계를 임시로 저장
    tempSum = maxSum;

    // 첫번째 합계를 가지고 더하고 빼면서 값을 비교
    for (let i = num; i < arr.length; i++) {
      tempSum = tempSum - arr[i - num] + arr[i];

      // 두개의 수에서 최대값 저장
      maxSum = Math.max(maxSum, tempSum);
    }

    return maxSum;
  }
  // Time Complexity - O(N)
  ```

  <br />

  ## 되돌아 보기

  단순하게 생각해서 이중 반복문으로 처리하는 경우가 많은데 로직에서 규칙을 잘 찾아서 규칙에 대한 수식을 스스로 만들어 보는게 가장 중요한 것 같다. 문제에 대해서 조금만 다르게 생각하고 반복되는 규칙성이 없는지도 한번씩 살펴보자
