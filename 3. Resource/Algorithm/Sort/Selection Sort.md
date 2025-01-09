# 선택정렬(Selection Sort)

선택 정렬은 일단 자리는 정해져있습니다. 첫번째 자리에 가장 작은 녀석을 집어넣으면 되죠. 그리고 난 후에 두번째 자리에 그 다음 가장 작은 녀석을 선택해 집어 넣습니다. 이 짓을 배열이 끝날때까지 합니다.

다음 배열 arr에는 9, 8, 1, 2, 3이 있습니다. 이것을 선택정렬로 정렬하도록 해봅시다.

[https://t1.daumcdn.net/cfile/tistory/99D2F5435BEFB65C1D](https://t1.daumcdn.net/cfile/tistory/99D2F5435BEFB65C1D)

우선 제일 첫번째 자리 , 즉 9가 있는 자리를 i라고 놓고 j는 i 다음부터 배열의 가장 작은 index를 뽑아 옵니다. 그것을 minIndex라고 하지요.

j는 8부터 배열을 검색하면서 가장 작은 index를 찾아보니 1이 있는 자리가 제일 작군요. 그러니 minIndex는 2(1이 있는 배열 원소의 위치!)가 됩니다. 그래서 가장 첫번째 자리는 1이 되겠습니다.

[https://t1.daumcdn.net/cfile/tistory/994F3E385BEFBAA520](https://t1.daumcdn.net/cfile/tistory/994F3E385BEFBAA520)

그 후 두번째 자리(8이 있는 자리)가 i가 되고, j는 그다음 가장 작은 원소의 index를 구하게 되니까 minIndex는 2가 있는 자리, 즉 4가 됩니다.

minIndex와 i를 바꿉니다.

[https://t1.daumcdn.net/cfile/tistory/998BFF3D5BEFBD4810](https://t1.daumcdn.net/cfile/tistory/998BFF3D5BEFBD4810)

다음은 9가 있는 자리입니다. i는 2가 되겠네요. 가장 작은 인덱스는 3이 있는 자리이므로 i와 바꾸어 줍니다.

[https://t1.daumcdn.net/cfile/tistory/99C12F3C5BEFC08837](https://t1.daumcdn.net/cfile/tistory/99C12F3C5BEFC08837)

다음 i가 하나 증가되어 9를 가리킵니다. 8이 더 작으니까 바꾸어 주어야겠죠??

[https://t1.daumcdn.net/cfile/tistory/99E10B335BEFC17924](https://t1.daumcdn.net/cfile/tistory/99E10B335BEFC17924)

이제 모든 비교와 교환은 끝났습니다. 오름차순으로 정렬이 된것을 볼 수 있습니다.

[https://t1.daumcdn.net/cfile/tistory/9992D03C5BEFC2682F](https://t1.daumcdn.net/cfile/tistory/9992D03C5BEFC2682F)

```java
void selectionSort(int arr[], int size) {
    int minIndex;
    int i, j;
    for (i = 0; i < size - 1; i++) {
        minIndex = i;
        for (j = i + 1; j < size; j++) 
            if (arr[j] < arr[minIndex])
                minIndex = j;
         
        swap(&arr[i], &arr[minIndex]);
    }
}
```
