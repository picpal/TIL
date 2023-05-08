#search #algorithm

# DFS ( Depth-First Search )

## 설명

- DFS(깊이 우선 검색) 알고리즘은 깊이 우선 순서로 그래프의 모든 정점을 방문하는 데 사용되는 순회 알고리즘
- 소스 정점에서 시작하여 역추적하기 전에 각 분기를 따라 가능한 한 멀리 탐색

## 구현코드

```js
const graph = {
  A: ["B", "C"],
  B: ["A", "D", "E"],
  C: ["A", "F"],
  D: ["B"],
  E: ["B", "F"],
  F: ["C", "E"],
};

const start = "A";

function dfs(graph, start) {
  let visited = new Set();
  let stack = [start];

  while (stack.length > 0) {
    let vertex = stack.pop();

    if (!visited.has(vertex)) {
      visited.add(vertex);
      stack.push(...graph[vertex].filter((v) => !visited.has(v)));
    }
  }

  return visited;
}

console.log(dfs(graph, start));
```

## 코드 설명

- dfs 함수는 그래프 객체와 시작 정점을 매개변수로 받습니다.

  - 'graph' 객체 : 각 키가 정점이고 해당 값이 인접한 정점의 배열인 객체로 표현
  - 'start' 매개변수 : 검색을 위한 시작 정점

- 두 개의 데이터 구조를 초기화

  - 방문한 정점을 추적하기 위한 'visited'라는 집합
  - 방문해야 하는 정점을 추적하기 위한 'stack'이라는 배열

- 'stack'에 정점이 있는 한 계속되는 루프
- 루프의 각 반복에서 pop() 메서드를 사용하여 stack의 맨 위에서 정점을 pop
- 팝업된 정점을 방문하지 않은 경우 알고리즘은 add() 메서드를 사용하여 visited 집합에 정점을 추가
- filter() 메서드를 사용하여 방문하지 않은 이웃 정점의 배열을 가져오고 push() 메서드와 스프레드 연산자(...)를 사용하여 stack에 푸시합니다.
- 알고리즘은 모든 정점을 방문할 때까지 깊이 우선 순서로 그래프를 계속 탐색
- 'stack'이 비면 검색을 통해 start 정점에서 도달할 수 있었던 모든 정점을 담은 visited을 반환
- 이 구현의 한 가지 장점은 재귀적 접근 방식 대신 반복적 접근 방식을 사용하여 매우 큰 그래프에서 호출 스택이 너무 커지는 문제를 방지
- 이 구현에서는 그래프가 연결되어 있다고 가정
- 그래프가 연결되지 않은 경우 그래프의 모든 구성 요소를 방문하도록 알고리즘을 수정




