# 선형검색(Linear Search)

## 개념

- 세트 간격으로 이동하면서 한번에 하나의 항목을 확인하는 식으로 모든 항목을 확인하는 방식

## javascript에서 선형 검색 함수

- indexOf
- includes
- find
- findIndex

## 연습문제

- 배열에서 제시한 값의 index를찾는 함수
    
    ```jsx
    function linearSearch(arr,findVal){
    	for(let i = 0; i<arr.length; i++){
    		if(arr[0] === findVal) return 1;
    	}
    	return -1;
    }
    ```
    

## Big O

- Best Case : O(1)
- Worst Case : O(n)
- Average Case : O(n)