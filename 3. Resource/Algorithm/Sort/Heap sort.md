# 힙정렬 (Heap sort)

**힙 정렬(Heap Sort)**

힙에 대해서 모르는 분들은 아래 url를 참고하시기 바랄게요.

[https://reakwon.tistory.com/42](https://reakwon.tistory.com/42)

힙에 대해서 아셨다면 이제 어떻게 정렬을 하는지 알아보도록 하지요.

**정렬**

위의 url의 코드를 통해서 힙정렬을 한다고 하면 의외로 쉽습니다. 메인 함수를 이렇게 바꾸어서 실행해보세요. 정렬이 아주아주 잘됩니다.

```java
int main() {
    heap hp;
    initHeap(&hp);
    int i;
    int arr[] = { 5,3,4,1,6,10 };
    int size = sizeof(arr) / sizeof(int);
    for (i = 0; i < size; i++)
        insert(&hp, arr[i]);
 
    for (i = 0; i < size; i++)
        arr[i] = deleteData(&hp);
 
    for (i = 0; i < size; i++)
        printf("%d ", arr[i]);
    printf("\n");
}
```

근데 문제점이 있죠. arr이라는 배열 공간과 heap이라는 배열 공간 두개를 쓰니까 메모리의 낭비가 있습니다.

그리고 insert함수를 부르는 시간 nlog(n), deleteData함수를 부르는 시간 nlog(n)입니다.

**우리는 더 잘할 수 있다**

이것도 훌륭하지만 우리는 더 나아갈 수 있습니다. 더 잘할 수 있습니다.

아래의 그림은 방금 위의 배열을 단지 트리 형태로 표현한 것입니다.

[https://t1.daumcdn.net/cfile/tistory/995C8A495C0294E417](https://t1.daumcdn.net/cfile/tistory/995C8A495C0294E417)

최대힙을 구현할 것인데, 힙의 조건을 만족하지 않지요. 왜냐면 5가 가장 큰 원소가 아니거든요.

아차, heap을 구현할때는 1부터 인덱스가 시작되는데 힙정렬할땐 0부터 시작한답니다. 왜냐면 공간을 아끼기 위해 그 배열 자체를 정렬할 것이기 때문이에요. 배열의 인덱스는 0부터 시작하는 것이니까요.

**heapify**

우리는 이것을 힙의 모양으로 고치는 것, 그 함수를 heapify라고 정의해보겠습니다.

**heapify(int arr[], int here, int size)** = 크기가 size인 배열 arr을 here부터 힙의 모양대로 만드는 함수

코드와 함께 heapify가 어떻게 동작하는 지 보도록 합시다.

```java
void heapify(int arr[], int here, int size) {
    int left = here * 2 + 1;
    int right = here * 2 + 2;
    int max=here;
    if (left<size&&arr[left]>arr[max])
        max = left;
    if (right < size&&arr[right]>arr[max])
        max = right;
 
    if (max != here) {
        swap(&arr[here], &arr[max]);
        heapify(arr, max, size);
    }
}
```

이 함수 하나로 힙의 모양을 만들수는 없습니다.

아래 왼쪽의 트리에서 우리가 heapify(arr, 0, size)를 호출한다고 치면 오른쪽의 트리로 만들어지게 됩니다.

[https://t1.daumcdn.net/cfile/tistory/993B48505C029A4D1F](https://t1.daumcdn.net/cfile/tistory/993B48505C029A4D1F)

가장 작은 1이 맨 끝에 이동함을 알 수 있죠. 하지만 최대힙은 아닙니다. 4가 가장 큰 값이 아니잖아요.

우리는 이렇게 생각해볼 수 있습니다. 만약 자식을 갖고 있는 노드가 있다면 그 위치에서부터 heapify를  역순으로 호출하는 방식이죠. 그러니까 자식노드를 갖고 있는 노드는 0, 1, 2의 인덱스인데, heapify(arr, 2, size), heapify(arr, 1, size), heapify(arr, 0, size) 이렇게 호출하면 되지 않을까요?

그런 함수가 buildHeap이라는 함수입니다.

**buildHeap**

우선 우리는 자식을 갖고 있는 노드가 어떤 인덱스를 갖고 있어야하는지 알아야합니다. 어떻게 알 수 있을까요??

가장 마지막 노드는 size-1의 노드인것을 알 수 있습니다. 하지만 우리는 그 부모님 성함을 알 수 있죠. 바로 size/2 -1이 그 부모노드의 인덱스라는 것을 알 수 있습니다.

그러니까 맨 마지막의 인덱스는 5, 그 부모노드는 5/2 -1로 2가 됩니다.

이제부터 우리는

{ 5,3,4,1,6,10 }

이 배열을 heap으로 만드는 과정을 살펴봅니다.

초기는 아래와 같죠.

[https://t1.daumcdn.net/cfile/tistory/9997AE395C029DC226](https://t1.daumcdn.net/cfile/tistory/9997AE395C029DC226)

자식을 갖고 있는 노드를 알았으니 heapify(arr, 2, size)를 호출해봅시다. 그러면 위의 트리는 이렇게 바뀝니다.

[https://t1.daumcdn.net/cfile/tistory/995E9E3E5C029DFA3C](https://t1.daumcdn.net/cfile/tistory/995E9E3E5C029DFA3C)

10이 더 크니까 4와 자리를 바꾸죠.

이제 heapify(arr, 1, size)를 호출하면 다음과 같이 바뀝니다.

[https://t1.daumcdn.net/cfile/tistory/99D6FD3F5C029E3222](https://t1.daumcdn.net/cfile/tistory/99D6FD3F5C029E3222)

6이 더 크니까 3과 자리를 바꿉니다.

이제 heapify(arr,  0, size)를 호출합시다.

[https://t1.daumcdn.net/cfile/tistory/9997C43B5C029E5835](https://t1.daumcdn.net/cfile/tistory/9997C43B5C029E5835)

5의 자식 중 10이 5보다 크네요. 자리 바꿔줍니다.

루트까지 호출이 되었으니까 heapify는 종료됩니다.

어때요? 최대힙 모양이 완성되었죠?

이것을 for루프로 돌리면 아래와 같은 코드가 됩니다.

```java
void buildHeap(int arr[], int size) {
    int i,j;
    for (i = size / 2 - 1; i >= 0; i--) {
        heapify(arr, i, size);
        for (j = 0; j < size; j++)
            printf("%d ", arr[j]);
        printf("\n\n");
    }
}
```

**이제 정렬 좀 하자..**

여기까지 됐으면 거의 끝났습니다. 루트원소를 맨끝에 옮기고 다시 heapify를 호출하면 됩니다. 단, 트리의 사이즈는 하나씩 줄어듭니다.

이제 정렬과정만 보이면 되겠군요.

```java
void heapSort(int arr[],int size) {
    int treeSize;
    buildHeap(arr, size);
    for (treeSize = size - 1; treeSize >= 0; treeSize--) {
        swap(&arr[0], &arr[treeSize]);
        heapify(arr, 0,treeSize);
    }
}
```

트리의 크기가 처음에는 6입니다. 크기를 점점 줄여나가는 것을 보세요.

아래의 그림들이 위의 코드가 어떻게 동작하는지 보여줍니다.

[https://t1.daumcdn.net/cfile/tistory/99BF8E4B5C02A25F2E](https://t1.daumcdn.net/cfile/tistory/99BF8E4B5C02A25F2E)

우선 0번 노드와 5번 노드의 값을 바꾸어줍니다. 똥색 표시는 이미 정렬된 부분을 의

미합니다. 그 후 heapify를 호출하면 다시 힙의 모양을 유지하게 됩니다.

[https://t1.daumcdn.net/cfile/tistory/9935D94B5C02A26008](https://t1.daumcdn.net/cfile/tistory/9935D94B5C02A26008)

이제는 0번 노드와 4번 노드를 교환합니다. 그리고 똥색으로 표시해주고, 이제는 트리 사이즈가 4가 됩니다. 그 후 heapify를 호출해서 사이즈 4까지 힙의 모양으로 만들어 줍니다. 5가 제일 큰 노드가 되겠네요.

[https://t1.daumcdn.net/cfile/tistory/9933404B5C02A26001](https://t1.daumcdn.net/cfile/tistory/9933404B5C02A26001)

이제 0번 노드와 3번 노드를 바꿉니다. 5, 6, 10까지 정렬된 것이 보이죠?? 그 후 heapify를 호출해서 최대힙의 모양으로 유지합니다.

[https://t1.daumcdn.net/cfile/tistory/99E0E74B5C02A2601B](https://t1.daumcdn.net/cfile/tistory/99E0E74B5C02A2601B)

이제 거의 다 왔습니다. 0번 노드가 가장 크므로 마지막 노드와 교체합니다. 4까지 정렬된 것을 확인할 수 있습니다. 그 후 heapify 호출해서 heap으로 유지합니다.

[https://t1.daumcdn.net/cfile/tistory/9944C74B5C02A26132](https://t1.daumcdn.net/cfile/tistory/9944C74B5C02A26132)

이제 가장 큰 노드 0번과 1번을 바꿉니다. 3까지 정렬됐습니다. 그 후 heapify호출하여 heap의 모양으로 만들어 줍니다.

[https://t1.daumcdn.net/cfile/tistory/9933DD4B5C02A26133](https://t1.daumcdn.net/cfile/tistory/9933DD4B5C02A26133)

이제 하나남았네요. 가장 큰 노드가 자기 자신이니까 바꾸나 마나고 heapify를 호출해도 그 모양 그대로 유지합니다.

트리를 보세요! 정렬이 된것을 확인할 수 있지요??

```java
#include <stdio.h>
 
void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}
void heapify(int arr[], int here, int size) {
    int left = here * 2 + 1;
    int right = here * 2 + 2;
    int max=here;
    if (left < size&&arr[left]>arr[max])
        max = left;
    if (right < size&&arr[right]>arr[max])
        max = right;
 
    if (max != here) {
        swap(&arr[here], &arr[max]);
        heapify(arr, max, size);
    }
}
 
void buildHeap(int arr[], int size) {
    int i,j;
    for (i = size / 2 - 1; i >= 0; i--) {
        heapify(arr, i, size);
        for (j = 0; j < size; j++)
            printf("%d ", arr[j]);
        printf("\n\n");
    }
}
 
void heapSort(int arr[],int size) {
    int treeSize;
    buildHeap(arr, size);
    for (treeSize = size - 1; treeSize >= 0; treeSize--) {
        swap(&arr[0], &arr[treeSize]);
        heapify(arr, 0,treeSize);
    }
}
void printArray(int arr[], int size) {
    int i;
    for (i = 0; i < size; i++)
        printf("%d ", arr[i]);
    printf("\n");
}
int main() {
    int arr[] = { 5,3,4,1,6,10 };
    int size = sizeof(arr) / sizeof(int);
     
    heapSort(arr, size);
    printArray(arr, size);
}
```

**시간복잡도**

힙 정렬에서 시간 복잡도는 O(nlogn)입니다. 완전 이진 트리이기때문에 높이가 균등합니다. 그래서 heapify는 logn으로 보장할 수 있습니다. 그리고 heapify를 n번 호출하기 때문에 nlogn이라는 것을 알 수 있지요.
