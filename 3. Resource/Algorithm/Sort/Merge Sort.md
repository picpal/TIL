# 병합정렬

**기본 개념**

병합 정렬을 알기 전에 우선 Devide and Conquer에 관한 개념을 알고 있어야 합니다. 아니, 몰라도 됩니다. 이제부터 배울꺼거든요. 간단히 말해 **어떤 문제를 우선 작은 문제로 쪼개고 난 후 다시 조합하여 원래의 문제를 푼다**는 것인데요.

병합정렬을 통해서 어떤 개념인지 알아보도록 하겠습니다.

우선 배열이 있습니다. 정렬이 되지 않은 정수형 배열이지요.

일단 묻지도 따지지도 않고 쪼갭니다. 아주 산산조각을 냅니다.

[https://t1.daumcdn.net/cfile/tistory/9915E3445BEFF11326](https://t1.daumcdn.net/cfile/tistory/9915E3445BEFF11326)

이 후에 쪼갠 역순으로 정렬을 하는 것입니다. 아래의 그림처럼요. 쪼갠것을 다시 차근차근 조립하면서 정렬하는 것을 볼 수 있지요.

[https://t1.daumcdn.net/cfile/tistory/99F3C9485BEFF20703](https://t1.daumcdn.net/cfile/tistory/99F3C9485BEFF20703)

그래서 우리는 이 그림을 이해하는 것이 목적입니다.

**구현**

구현은 2가지의 큰 틀로 구성이 됩니다.

**1. 분할**

가장 먼저 해야될 작업입니다. 이 작업은 쉽습니다. 배열을 그냥 2로 계속 나누다보면 언젠가는 하나의 원소를 갖게되니까 그때 멈추면 됩니다.

mergeSort라는 함수를  보세요. 정렬할 배열과 왼쪽끝을 나타내는 left, 오른쪽 끝을 나타내는 right를 인자로 받고 있습니다.

```java
void mergeSort(int arr[], int left, int right) {
 
    if(left==right) return;  
    int mid;
     
    mid = (left + right) / 2;
    mergeSort(arr, left, mid); 
    mergeSort(arr, mid + 1, right); 
     
}
```

아래의 그림처럼 left와 right를 이용해서 mid를 기준으로 쪼개는 겁니다. mid는 left와 right의 중간 부분을 가리키고 있죠.

[https://t1.daumcdn.net/cfile/tistory/99894D4F5BEFFB7817](https://t1.daumcdn.net/cfile/tistory/99894D4F5BEFFB7817)

그러다가 하나의 원소만 남게되면 바로 return이 됩니다. 어떻게 알 수 있을까요? 하나의 원소만 남았다는 것은 left와 right가 서로 같은 값을 가졌다는 것을 의미하니까 left==right라면 바로 return 하면 됩니다.

[https://t1.daumcdn.net/cfile/tistory/99BB004F5BEFFB780E](https://t1.daumcdn.net/cfile/tistory/99BB004F5BEFFB780E)

**2. 병합**

병합은 조금 어려울 수도 있지만 이해하면 별거 없습니다.

우선 코드부터 보면서 이해하자구요.

```java

void merge(int arr[], int left, int right) {
     
    int L, R, k, a;
    int mid = ( left + right ) / 2;
    L = left;
    R = mid + 1;
    k = left;
 
    while (L <= mid && R <= right)
        tmp[k++] = arr[L] <= arr[R] ? arr[L++] : arr[R++];
 
    if (L>mid) 
        for (a = R; a <= right; a++)
            tmp[k++] = arr[a];
    else
        for (a = L; a <= mid; a++)
            tmp[k++] = arr[a];
     
    for (a = left; a <= right; a++) 
        arr[a] = tmp[a];
     
}

```

뭐지 이 변태같은 코드는? 이러지 마시구요. 하나하나 보도록 합시다. 차근차근히

인자로 배열 arr과 left, right를 받고 있습니다. 나누어진 배열의 한쪽은 L, 그리고 다른 한쪽은 R을 인덱스로 하기로 합시다. 쪼개진 배열 하나를 왼쪽에 있다 생각하고 L, 다른 하나는 오른쪽에 있다고 생각하고 R로 한거에요. R은 mid+1부터 시작해야겠죠?

tmp라는 임시배열은 k를 인덱스로 합니다.

그리고 나누어 쪼개져서 정렬된 일부분의 배열을 부분 배열이라고 하겠습니다.

자, 됐으면 이제 while문을 돕니다. while루프는 왼쪽 부분 배열을 전부 다 돌았거나 오른쪽 부분 배열을 전부다 돌때까지 반복합니다. 그러니 L은 mid 이하까지 R은 right 이하까지 반복하는데 왼쪽 배열, 오른쪽 배열 하나라도 전부 반복이 되었다면 탈출합니다.

여기서부터는 그림이 좀 필요하겠네요.

[https://t1.daumcdn.net/cfile/tistory/99C369365BF0008716](https://t1.daumcdn.net/cfile/tistory/99C369365BF0008716)

왼쪽 부분 배열과 오른쪽 부분 배열의 원소를 비교해서 더 작은 값이 tmp로 값이 복사됩니다. 같을 때는 아무거나 넣어도 상관없겠죠. 그러니 현재 1과 2 중 1이 더 작으므로 tmp에 1이 저장됩니다. 그리고 L과 k를 하나씩 증가시키죠. 사실 k는 계속 증가하는 상태입니다. 항상 값이 들어가니까요.

[https://t1.daumcdn.net/cfile/tistory/99E04E365BF000881C](https://t1.daumcdn.net/cfile/tistory/99E04E365BF000881C)

그 후 arr[L]과 arr[R] 중 값이 작은 것을 또 선택합니다. 3과 2 중 2가 더 작으니 2를 tmp에 넣습니다. 이후 R과 k는 증가합니다.

[https://t1.daumcdn.net/cfile/tistory/99552B365BF0008835](https://t1.daumcdn.net/cfile/tistory/99552B365BF0008835)

마찬가지입니다. arr[L]과 arr[R] 중 가장 작은 값은 3이므로 3을 넣습니다. 그 후 k와 L의 값이 하나 증가합니다.

[https://t1.daumcdn.net/cfile/tistory/99D387365BF000880F](https://t1.daumcdn.net/cfile/tistory/99D387365BF000880F)

4까지 넣고 나면 한쪽 부분 배열(왼쪽)이 전부 tmp에 저장됐으므로 이제 while루프는 종료됩니다.

이제 남은 것을 옮겨야하는데요. 5가 남았죠?

만약 L이 mid보다 크다면 왼쪽 부분 배열이 tmp에 전부 복사된 것이므로 오른쪽에 있는 부분 배열의 값을 모조리 tmp에 집어넣습니다.

반대도 역시 똑같습니다. 오른쪽 부분 배열이 전부 tmp에 복사되었다면 R은 right보다 크게 될테니까 왼쪽 부분 배열의 남은 값을 전부 tmp에 집어넣으면 되는 것이죠.

이제 원본 배열에 값을 다시 돌려주어야합니다. tmp는 잠시 저장하는 용도로 쓰기 때문이죠.

[https://t1.daumcdn.net/cfile/tistory/99BC0E395BF003BF1C](https://t1.daumcdn.net/cfile/tistory/99BC0E395BF003BF1C)

어때요. 아름답게 정렬이 된것을 확인할 수 있죠??

merge라는 함수를 구현했으니, 이제 mergeSort에서도 이 함수를 이용해서 병합! 하면 됩니다. 아래처럼 merge 함수를 추가하세요.

```java

void mergeSort(int arr[], int left, int right) {
     
    if (left == right) return;
    int mid;
    mid = (left + right) / 2;
    mergeSort(arr, left, mid); 
    mergeSort(arr, mid + 1, right); 
    merge(arr, left, right); 
     
}

```

보다 이해가 잘 되기 위해 전체코드를 보세요.

```java
#include <stdio.h>
#define SIZE 5
int tmp[SIZE];
                     
void merge(int arr[], int left, int right) {
     
    int L, R, k, a;
    int mid = (left + right) / 2;
    L = left;
    R = mid + 1;
    k = left;
 
    while (L <= mid && R <= right)
        tmp[k++] = arr[L] <= arr[R] ? arr[L++] : arr[R++];
 
    if (L>mid) 
        for (a = R; a <= right; a++)
            tmp[k++] = arr[a];
    else
        for (a = L; a <= mid; a++)
            tmp[k++] = arr[a];
     
    for (a = left; a <= right; a++) 
        arr[a] = tmp[a];
     
}
 
 
void mergeSort(int arr[], int left, int right) {
     
    if (left == right) return;
    int mid;
    mid = (left + right) / 2;
    mergeSort(arr, left, mid); 
    mergeSort(arr, mid + 1, right); 
    merge(arr, left, right); 
     
}
void printArr(int arr[], int size) {
    int i;
    for (i = 0; i<size; i++) 
        printf("%d ", arr[i]);
     
    printf("\n");
}
void main() {
    int i;
    int arr[SIZE] = { 3,4,1,5,2 };
 
    mergeSort(arr, 0, SIZE - 1);
    printArr(arr, SIZE);
}

```






```java
public class Test {
	private static void moergeSort(int[] arr){
		int[] tmp = new int[arr.length];
		mergeSort(arr, tmp, 0, arr.length-1);
	}

	private static void mergeSort(int[] arr, int[] tmp, int start, int end){
		if(start < end){
			int mid = (start + end) / 2;
			mergeSort(arr, tmp, start, mid);
			mergeSort(arr, tmp, mid +1, end);
			merge(arr, tmp, start, mid, end);
		}
	}

	public static void main(String[] args){
		for(int i = start; i < end; i++){
			tmp[i] = arr[i];
		}
		
		int part1 = start;
		int prat2 = mid + 1;
		int index = start;
		while(part1 <= mid && part2 <= end){
			if(tmp[part1] <= tmp[part2]){
				arr[index] = tmp[part1];
				part1++;
			}else{
				arr[index] = tmp[part2];
				part2++;
			}
			index++;
		}

		// 앞쪽 정렬에 대해서만 체크해서 다시 넣어줌
		for(int i = 0; i<= mid -part1; i++){
			arr[index + i] = tmp[part1 + i];
		}
	}

	public static void main(String[] args){
		
	
	}
}
```