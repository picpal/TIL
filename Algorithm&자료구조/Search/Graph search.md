

# 그래프탐색 이란

- 하나의 정점으로부터 시작하여 차례대로 모든 정점들을 한 번씩 방문하는 것
- Ex) 특정 도시에서 다른 도시로 갈 수 있는지 없는지, 전자 회로에서 특정 단자와 단자가 서로 연결되어 있는지

# 그래프 탐색에서 자주 사용되는 예시

## 네트워크
- 우리가 사용하는 컴퓨터는 인터넷으로 연결된다. 각 컴퓨터나 네트워크 장비를 정점(vertex , node)로 연결을 간선(edge)로 본다면 그래프로 표현이 가능하다.

## 경로찾기
- 특정 위치 간 가장 짧은 경로 / 긴 경로를 그래프를 이용해 찾을 수 있다. 구글 맵 또한 이러한 응용을 한 것이며, 게임 내 NPC 등의 모델도 이를 통해 움직임이 구현된다.(이외 GPS, High Frequency Trading 등에도 활용)

## 순서확인
- 특정 정점을 할 일이라고 본다면 그에 대한 연결을 통해 순서를 지정할 수 있다.(위상 정렬 예시)

## 연결성확인
-  전자 회로 내 특정 회로가 상호 연결되어 있는지 확인하는 경우 등에 사용

# 너비 우선 탐색 (BFS , Breadth-First Search)

### 너비우선탐색이란

- 루트 노드(혹은 다른 임의의 노드)에서 시작해서 인접한 노드를 먼저 탐색하는 방법
- 시작 정점으로부터 가까운 정점을 먼저 방문하고 멀리 떨어져 있는 정점을 나중에 방문하는 순회 방법이다.
- 즉, 깊게(deep) 탐색하기 전에 넓게(wide) 탐색하는 것이다.
- 사용하는 경우: 두 노드 사이의 최단 경로 혹은 임의의 경로를 찾고 싶을 때 이 방법을 선택한다.
    - Ex) 지구상에 존재하는 모든 친구 관계를 그래프로 표현한 후 Ash와 Vanessa 사이에 존재하는 경로를 찾는 경우
    - 깊이 우선 탐색의 경우 - 모든 친구 관계를 다 살펴봐야 할지도 모른다.
    - 너비 우선 탐색의 경우 - Ash와 가까운 관계부터 탐색
    - 너비 우선 탐색(BFS)이 깊이 우선 탐색(DFS)보다 좀 더 복잡하다.
    

### 너비 우선 탐색(BFS)의 특징

- 직관적이지 않은 면이 있다.
    - BFS는 시작 노드에서 시작해서 거리에 따라 단계별로 탐색한다고 볼 수 있다.
- BFS는 재귀적으로 동작하지 않는다.
이 알고리즘을 구현할 때 가장 큰 차이점은, 그래프 탐색의 경우 어떤 노드를 방문했었는지 여부를 반드시 검사 해야 한다는 것이다.
- 이를 검사하지 않을 경우 무한루프에 빠질 위험이 있다.
- BFS는 방문한 노드들을 차례로 저장한 후 꺼낼 수 있는 자료 구조인 큐(Queue)를 사용한다.
    - 즉, 선입선출(FIFO) 원칙으로 탐색
    - 일반적으로 큐를 이용해서 반복적 형태로 구현하는 것이 가장 잘 동작한다.
- ‘Prim’, ‘Dijkstra’ 알고리즘과 유사하다.

### 너비 우선 탐색(BFS)의 과정

- 깊이가 1인 모든 노드를 방문하고 나서 그 다음에는 깊이가 2인 모든 노드를, 그 다음에는 깊이가 3인 모든 노드를 방문하는 식으로 계속 방문하다가 더 이상 방문할 곳이 없으면 탐색을 마친다.

![Untitled](%E1%84%8B%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%84%85%E1%85%B5%E1%84%8C%E1%85%B3%E1%86%B7,%E1%84%8C%E1%85%A1%E1%84%85%E1%85%AD%E1%84%80%E1%85%AE%E1%84%8C%E1%85%A9%2049af8291da234d6dbe835dcc2dda5518/Untitled.png)

1. a 노드(시작 노드)를 방문한다. (방문한 노드 체크)
    - 큐에 방문된 노드를 삽입(enqueue)한다.
    - 초기 상태의 큐에는 시작 노드만이 저장
        - 즉, a 노드의 이웃 노드를 모두 방문한 다음에 이웃의 이웃들을 방문한다.
2. 큐에서 꺼낸 노드과 인접한 노드들을 모두 차례로 방문한다.
    - 큐에서 꺼낸 노드를 방문한다.
    - 큐에서 커낸 노드과 인접한 노드들을 모두 방문한다.
        - 인접한 노드가 없다면 큐의 앞에서 노드를 꺼낸다(dequeue).
    - 큐에 방문된 노드를 삽입(enqueue)한다.
3. 큐가 소진될 때까지 계속한다.

### 너비 우선 탐색(BFS)의 구현

- 구현 방법
    - 자료 구조 큐(Queue)를 이용
- BFS 의사코드(pseudocode)

```java
void search(Node root) {
  Queue queue = new Queue();
  root.marked = true; // (방문한 노드 체크)
  queue.enqueue(root); // 1-1. 큐의 끝에 추가

  // 3. 큐가 소진될 때까지 계속한다.
  while (!queue.isEmpty()) {
    Node r = queue.dequeue(); // 큐의 앞에서 노드 추출
    visit(r); // 2-1. 큐에서 추출한 노드 방문
    // 2-2. 큐에서 꺼낸 노드와 인접한 노드들을 모두 차례로 방문한다.
    foreach (Node n in r.adjacent) {
      if (n.marked == false) {
        n.marked = true; // (방문한 노드 체크)
        queue.enqueue(n); // 2-3. 큐의 끝에 추가
      }
    }
  }
}
```

```java
import java.io.*;
import java.util.*;

/* 인접 리스트를 이용한 방향성 있는 그래프 클래스 */
class Graph {
  private int V; // 노드의 개수
  private LinkedList<Integer> adj[]; // 인접 리스트

  /** 생성자 */
  Graph(int v) {
    V = v;
    adj = new LinkedList[v];
    for (int i=0; i<v; ++i) // 인접 리스트 초기화
      adj[i] = new LinkedList();
  }

  /** 노드를 연결 v->w */
  void addEdge(int v, int w) { adj[v].add(w); }

  /** s를 시작 노드으로 한 BFS로 탐색하면서 탐색한 노드들을 출력 */
  void BFS(int s) {
    // 노드의 방문 여부 판단 (초깃값: false)
    boolean visited[] = new boolean[V];
    // BFS 구현을 위한 큐(Queue) 생성
    LinkedList<Integer> queue = new LinkedList<Integer>();

    // 현재 노드를 방문한 것으로 표시하고 큐에 삽입(enqueue)
    visited[s] = true;
    queue.add(s);

    // 큐(Queue)가 빌 때까지 반복
    while (queue.size() != 0) {
      // 방문한 노드를 큐에서 추출(dequeue)하고 값을 출력
      s = queue.poll();
      System.out.print(s + " ");

      // 방문한 노드와 인접한 모든 노드를 가져온다.
      Iterator<Integer> i = adj[s].listIterator();
      while (i.hasNext()) {
        int n = i.next();
        // 방문하지 않은 노드면 방문한 것으로 표시하고 큐에 삽입(enqueue)
        if (!visited[n]) {
          visited[n] = true;
          queue.add(n);
        }
      }
    }
  }
}
```

```java
/** 사용 방법 */
public static void main(String args[]) {
  Graph g = new Graph(4);

  g.addEdge(0, 1);
  g.addEdge(0, 2);
  g.addEdge(1, 2);
  g.addEdge(2, 0);
  g.addEdge(2, 3);
  g.addEdge(3, 3);

  g.BFS(2); /* 주어진 노드를 시작 노드로 BFS 탐색 */
}
```

### 너비 우선 탐색(BFS)의 시간 복잡도

- 인접 리스트로 표현된 그래프: O(N+E)
- 인접 행렬로 표현된 그래프: O(N^2)
- 깊이 우선 탐색(DFS)과 마찬가지로 그래프 내에 적은 숫자의 간선만을 가지는 희소 그래프(Sparse Graph) 의 경우 인접 행렬보다 인접 리스트를 사용하는 것이 유리하다.

관련된 Post
트리 : [https://gmlwjd9405.github.io/2018/08/12/data-structure-tree.html](https://gmlwjd9405.github.io/2018/08/12/data-structure-tree.html)
그래프 : [https://gmlwjd9405.github.io/2018/08/13/data-structure-graph.html](https://gmlwjd9405.github.io/2018/08/13/data-structure-graph.html)

**BFS/DFS 예제 ( Javascript )**

![Untitled](%E1%84%8B%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%84%85%E1%85%B5%E1%84%8C%E1%85%B3%E1%86%B7,%E1%84%8C%E1%85%A1%E1%84%85%E1%85%AD%E1%84%80%E1%85%AE%E1%84%8C%E1%85%A9%2049af8291da234d6dbe835dcc2dda5518/Untitled%201.png)

```jsx
//BFS 방식: A - B - C - D - G - H - I - E - F - J

const graph = {
    A: ['B', 'C'],
    B: ['A', 'D'],
    C: ['A', 'G', 'H', 'I'],
    D: ['B', 'E', 'F'],
    E: ['D'],
    F: ['D'],
    G: ['C'],
    H: ['C'],
    I: ['C', 'J'],
    J: ['I']
  };

const BFS = (graph , startNode) => {
    const visited = []; // 탐색을 마친 노드들
    let needVisit = []; // 탐색해야할 노드들

    needVisit.push(startNode); // 노드 탐색 시작

    while(needVisit.length !== 0){
        const node = needVisit.shift(); // queue이기 때문에 선입선출 , shift()를 사용
        if(!visited.includes(node)){ // 해당 노드가 탐색된 적 없다면
            visited.push(node);
            needVisit = [...needVisit , ...graph[node]];
        }
    }
    return visited;
}

const result = BFS(graph,"A");

console.log(result);
```

```jsx
class Node{
    constructor(data){
        this.data = data; // 다른 노드와 차별점을 두는 데이터
        this.children = []; // 자식들과의 정보(주소)를 담을 배열
    }

    add(data){
        this.children.push(new Node(data)); // 자식노드를 생성하고 바로 배열에 저장한다. (주소를 저장하는 행위)
    }

    remove(data){
        this.children = this.children.filter(child => child.data === data ? false : true); // 해당하는 자식의 정보를 배열에서 빼주기 위한 filter
    }
}

class Tree{
    constructor(){
        this.root = null;
    }

    BFS(fn){
        if(this.root === null) return;

        const unvisitedQueue = [this.root];
        while(unvisitedQueue.length !== 0){
            const current = unvisitedQueue.shift(); 
            unvisitedQueue.push(...current.children); // 현재 부모 노드의 자식들을 모두 큐에 담는다
            fn(current); //현재 노드를 가지고 callback 함수 실행
        }

    }

    DFS(fn) {
        if(this.root === null) return;

        const unvisitedList = [this.root];
        while(unvisitedList.length !== 0) {
            const current = unvisitedList.shift();
            unvisitedList.unshift(...current.children); // list 앞에다 넣어준다. (우선순위: 내 자식들이 먼저야! )
            fn(current);
        }
    }

}

const treeObj = new Tree(); // 빈트리 생성
treeObj.root = new Node('a'); // 루트가 node 'a'의 주소를 가리키면 'a'의 자식들까지 접근가능하다. 
treeObj.root.add('b'); // a의 자식 'b','c'
treeObj.root.add('c');
treeObj.root.children[0].add('d'); // 'b'의 자식으로 'd'가 추가된다.

const lettersBFS = [];
const lettersDFS = [];
treeObj.BFS(node => lettersBFS.push(node.data));
treeObj.DFS(node => lettersDFS.push(node.data));

console.log(lettersBFS);
console.log(lettersDFS);
```

# 깊이 우선 탐색 (DFS: Depth-First Search)

### 깊이 우선 탐색이란

- 루트 노드(혹은 다른 임의의 노드)에서 시작해서 다음 분기(branch)로 넘어가기 전에 해당 분기를 완벽하게 탐색하는 방법
- 미로를 탐색할 때 한 방향으로 갈 수 있을 때까지 계속 가다가 더 이상 갈 수 없게 되면 다시 가장 가까운 갈림길로 돌아와서 이곳으로부터 다른 방향으로 다시 탐색을 진행하는 방법과 유사하다.
- 즉, 넓게(wide) 탐색하기 전에 깊게(deep) 탐색하는 것이다.
- 사용하는 경우: 모든 노드를 방문 하고자 하는 경우에 이 방법을 선택한다.
- 깊이 우선 탐색(DFS)이 너비 우선 탐색(BFS)보다 좀 더 간단하다.
- 단순 검색 속도 자체는 너비 우선 탐색(BFS)에 비해서 느리다.

### 깊이 우선 탐색(DFS)의 특징

- 자기 자신을 호출하는 순환 알고리즘의 형태 를 가지고 있다.
- 전위 순회(Pre-Order Traversals)를 포함한 다른 형태의 트리 순회는 모두 DFS의 한 종류이다.
- 이 알고리즘을 구현할 때 가장 큰 차이점은, 그래프 탐색의 경우 어떤 노드를 방문했었는지 여부를 **반드시 검사** 해야 한다는 것이다. 이를 검사하지 않을 경우 무한루프에 빠질 위험이 있다.

### 깊이 우선 탐색(DFS)의 과정

![Untitled](%E1%84%8B%E1%85%A1%E1%86%AF%E1%84%80%E1%85%A9%E1%84%85%E1%85%B5%E1%84%8C%E1%85%B3%E1%86%B7,%E1%84%8C%E1%85%A1%E1%84%85%E1%85%AD%E1%84%80%E1%85%AE%E1%84%8C%E1%85%A9%2049af8291da234d6dbe835dcc2dda5518/Untitled%202.png)

1. a 노드(시작 노드)를 방문한다.
    - 방문한 노드는 방문했다고 표시한다.
2. a와 인접한 노드들을 차례로 순회한다.
    - a와 인접한 노드가 없다면 종료한다.
3. a와 이웃한 노드 b를 방문했다면, a와 인접한 또 다른 노드를 방문하기 전에 b의 이웃 노드들을 전부 방문해야 한다.
    - b를 시작 정점으로 DFS를 다시 시작하여 b의 이웃 노드들을 방문한다.
4. b의 분기를 전부 완벽하게 탐색했다면 다시 a에 인접한 정점들 중에서 아직 방문이 안 된 정점을 찾는다.
    - 즉, b의 분기를 전부 완벽하게 탐색한 뒤에야 a의 다른 이웃 노드를 방문할 수 있다는 뜻이다.
    - 아직 방문이 안 된 정점이 없으면 종료한다.
    - 있으면 다시 그 정점을 시작 정점으로 DFS를 시작한다
    

### 깊이 우선 탐색(DFS)의 구현 방법 2가지

1. 순환 호출 이용
2. 명시적인 스택 사용
    - 명시적인 스택을 사용하여 방문한 정점들을 스택에 저장하였다가 다시 꺼내어 작업한다.
    순환 호출을 이용한 DFS 의사코드(pseudocode)
    1. 순환 호출을 이용한 DFS 의사코드(pseudocode)
    
    ```java
    void search(Node root) {
      if (root == null) return;
      // 1. root 노드 방문
      visit(root);
      root.visited = true; // 1-1. 방문한 노드를 표시
      // 2. root 노드와 인접한 정점을 모두 방문
      for each (Node n in root.adjacent) {
        if (n.visited == false) { // 4. 방문하지 않은 정점을 찾는다.
          search(n); // 3. root 노드와 인접한 정점 정점을 시작 정점으로 DFS를 시작
        }
      }
    }
    ```
    
    ```java
    import java.io.*;
    import java.util.*;
    
    /* 인접 리스트를 이용한 방향성 있는 그래프 클래스 */
    class Graph {
      private int V;   // 노드의 개수
      private LinkedList<Integer> adj[]; // 인접 리스트
    
      /** 생성자 */
      Graph(int v) {
          V = v;
          adj = new LinkedList[v];
          for (int i=0; i<v; ++i) // 인접 리스트 초기화
              adj[i] = new LinkedList();
      }
    
      /** 노드를 연결 v->w */
      void addEdge(int v, int w) { adj[v].add(w); }
    
      /** DFS에 의해 사용되는 함수 */
      void DFSUtil(int v, boolean visited[]) {
          // 현재 노드를 방문한 것으로 표시하고 값을 출력
          visited[v] = true;
          System.out.print(v + " ");
    
          // 방문한 노드와 인접한 모든 노드를 가져온다.
          Iterator<Integer> i = adj[v].listIterator();
          while (i.hasNext()) {
              int n = i.next();
              // 방문하지 않은 노드면 해당 노드를 시작 노드로 다시 DFSUtil 호출
              if (!visited[n])
                  DFSUtil(n, visited); // 순환 호출
          }
      }
    
      /** 주어진 노드를 시작 노드로 DFS 탐색 */
      void DFS(int v) {
          // 노드의 방문 여부 판단 (초깃값: false)
          boolean visited[] = new boolean[V];
    
          // v를 시작 노드로 DFSUtil 순환 호출
          DFSUtil(v, visited);
      }
    
      /** DFS 탐색 */
      void DFS() {
          // 노드의 방문 여부 판단 (초깃값: false)
          boolean visited[] = new boolean[V];
    
          // 비연결형 그래프의 경우, 모든 정점을 하나씩 방문
          for (int i=0; i<V; ++i) {
              if (visited[i] == false)
                  DFSUtil(i, visited);
          }
      }
    }
    ```
    
    ```java
    /** 사용 방법 */
    public static void main(String args[]) {
        Graph g = new Graph(4);
    
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
    
        g.DFS(2); /* 주어진 노드를 시작 노드로 DFS 탐색 */
        g.DFS(); /* 비연결형 그래프의 경우 */
    }
    ```
    
    ### 깊이 우선 탐색(DFS)의 시간 복잡도
    
    - DFS는 그래프(정점의 수: N, 간선의 수: E)의 모든 간선을 조회한다.
        - 인접 리스트로 표현된 그래프: O(N+E)
        - 인접 행렬로 표현된 그래프: O(N^2)
    - 즉, 그래프 내에 적은 숫자의 간선만을 가지는 희소 그래프(Sparse Graph) 의 경우 인접 행렬보다 인접 리스트를 사용하는 것이 유리하다.
    
    ```jsx
    //DFS 방식: A - B - D - E - F - C - G - H - I - J
    
    const graph = {
        A: ["B", "C"],
        B: ["A", "D"],
        C: ["A", "G", "H", "I"],
        D: ["B", "E", "F"],
        E: ["D"],
        F: ["D"],
        G: ["C"],
        H: ["C"],
        I: ["C", "J"],
        J: ["I"]
    };
    
    const DFS = (graph , startNode) => {
        const visited = []; // 탐색을 마친 노드들
        let needVisit = []; // 탐색해야할 노드들
    
        needVisit.push(startNode); // 노드 탐색 시작
    
        while(needVisit.length !== 0){ //탐색할 노드가 남아있을때 까지
            const node = needVisit.shift(); // queue 이기 때문에 선입선출 , shift()를 사용
            if (!visited.includes(node)) { // 해당 노드가 탐색된 적 없다면
                visited.push(node); 
                needVisit = [...graph[node], ...needVisit];
            }
        }
    
        return visited;
    }
    
    console.log(DFS(graph, "A"));
    ```
    




