# 빈도수 체크

- 두개의 개별 반복문이 두개의 중첩 반복문 보다 효율이 훨씬 좋다
- 개별 반복문을 통해서 분리한 두개의 객체에 정보를 담아서 비교를 하는 방식으로 사용하면
- 기존의 중첩 반복문을 사용하지 않고 비교하는데 더 용이하다
- 구현 코드

  ```js
  function same(arr1, arr2){
    if(arr1.length !== arr2.length){
      return false;
    }

    let frequencyCounter1 = {};
    let frequencyCounter2 = {};

    for(let val of arr1){
      frequencyCounter1[val] = (frequencyCounter1[val] || 0 ) +1;
    }

    for(let val of arr2){
      frequencyCounter2[val] = (frequencyCounter2[val] || 0 ) +1;
    }

    for(let key in frequencyCounter1){
      if(!(key ** 2 in frequencyCounter2){
        return false;
      }
      if(frequencyCounter2[key **2] !== frequencyCounter1[key]){
        return false;
      }

    }

    return true;

  }

  const result = same([1,2,3],[9,1,4]);

  console.log(result);

  // Time Complexity - O(n)
  // Space Complexity - O(n)
  ```

<br />
> 객체에서 key값을 가지고 in으로 탐색하는게 빠른가 아니면 배열에서 indexOf로 순번을 찾는게 빠를까?
> ⇒ 테스트 후 결과를 기입해 놓자!!!!!
<br /><br />

# Anagrams 예제

- 두개의 문자열이 일치하는지 비교
- validAnagram(’abc’,’ccc’); // false
- validAnagram(’abc’,’cba’); // true
- 소문자로만 생각하면 된다.
- 문제에 대한 의문이 생기면 출제자에게 좀 더 구체적인 제한 사항을 물어봐야한다.

## My solution

```js
const validAnagram = function(w1,w2){
	w1 = w1.split('').sort();
	w2 = w2.split('').sort();

	if(w1 === w2) return true;

	const wordObj1 = {};
	const wordObj2 = {};

	for(word of w1){
		wordObj1[word] = (wordObj1[word] || 0) + 1;
	}

	for(word of w2){
		wordObj2[word] = (wordObj2[word] || 0) + 1;
	}

	for(key in wordObj1){
		if(wordObj1[key] !== wordObj2[key]{
			return false;
		}
	}

	return true;
}
```

<br />

## lecture solution

```jsx
const validAnagram = function (w1, w2) {
  if (w1.length !== w2.length) {
    return false;
  }

  const lookup = {};

  for (let i = 0; i < w1.length; i++) {
    let letter = w1[i];
    lookup[letter] ? (lookup[letter] += 1) : (lookup[letter] = 1);
  }

  for (let i = 0; i < w2.length; i++) {
    let letter = w2[i];

    // 0이 false값을 반환한다는 개념을 알았지만 실제 사용은 안해봤는데
    // 이런식으로도 검증 로직에서 사용하면 더 간단하게 표현도 가능하다는것을 다시 생각해보게 되었다.
    // 하지만 다른 언어 기준에서 볼 때도 이 문장이 이해가 되는 부분이 있을까??
    if (!lookup[letter]) {
      return false;
    } else {
      lookup[letter] -= 1;
    }
  }

  return true;
};
```

## 되돌아 보기

> 단어이면서 소문자 등의 제약사항 때문에 문자들을 정렬한 다음 비교해서 반환해도 결과는 같을것 같다.
> 빈도수 체크에 대한 선행 학습한 내용을 너무 그대로 사용해서 해결하려 했다. 필요없는 로직이나 다른 문법등을 응용해서 적용해봤어도 될 것 같은데 아직은 사고력이 좀 부족한 것 같은 느낌이다.
