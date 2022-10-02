# Divede and Conquer ( 분할 정복 패턴 )

배열이나 문자열 같은 큰 규모의 데이터셋을 처리
값을 찾기 위해 배열의 왼쪽에서 시작하여 오른쪽 끝까지 이동하는 것보다는
배열의 작은 조각으로 세분하여 각 조각들을 어디로 이동시킬지 결정하는 작업을 먼저 진행
즉 큰 데이터 덩어리를 작은 조각으로 나누는 것
탐색 알고리즘의 **이진 탐색** 혹은 일반 탐색이라는 알고리즘이 전형적인 예시

- A naive solution

  ```jsx
  function search(arr, value) {
    for (let i = 0; i < arr.length; i++) {
      if (arr[i] === value) {
        return i;
      }
    }

    return -1;
  }
  // Time Complexity : O(N)
  ```

- Refactor

  > 중간 값을 추출하여 찾을 값과 비교 후 찾을 값보다 큰 그룹에서
  > 다시 중간 값을 찾아서 찾을 값과 비교 하고 그룹화 하는 방식으로 진행하여 찾음.
  > 그렇기 때문에 배열은 정렬이 되어 있어야하며 비교 가능한 식이어야 함.

  ```jsx
  function search(arr, value) {
    let min = 0;
    let max = arrary.length - 1;

    while (min <= max) {
      let middle = Math.floor((min + max) / 2);
      let currentElement = array[middle];

      if (currentElement < val) {
        min = middle + 1;
      } else if (currentElement > val) {
        max = middle - 1;
      } else {
        return middle;
      }
    }

    return -1;
  }
  // Time Complexity : Log(N) - Binary Search
  ```

## 되돌아 보기

> 어떤 순서만 존재한다면 문자열도 정렬 후 이런 방식으로 탐색 할 수 있지 않을까? 아니면 문자를 ASCii 코드로 변환하여 찾는 방법은? 조금더 생각 해보자.
