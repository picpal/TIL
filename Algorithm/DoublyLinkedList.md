# Doubly Linked List

> https://visualgo.net/en

## Objectives

- 이중 연결 리스트 구조
- 단일 연결 리스트와 이중 연결 리스트 기본 연산 비교

## 작동방식

- 인덱스 없음
- 헤드 테일이 존재
- 다음을 가르키는 포인터와 이전을 가르키는 포인터 2개가 존재함

기존 singly linked list는 한 방향으로만 움직이기 때문에 마지막 번째로 이동하기 위해서는 모든 노드를 거쳐야한다. 그리고 특정 노드의 이전 단계를 보기 위해서도 앞선 모든 노드를 탐색해서 찾아야한다.

하지만 doubly linked list는 이전 노드를 알 수 있기 때문에 마지막 번째나 특정 노드의 이전 노드를 찾아가기가 용이하다. 대신 다음 노드 이전 노드 모두 가지고 있기 떄문에 메모리를 더 많이 차지한다.

##

Node

- val
- next
- prev

class Node{
constructor(val){
this.val = val;
this.next = null;
this.prev = null;
}
}

DoublyLinkedList

- head
- tail
- length

class DoublyLinkedList {
constructor(){
this.head = head;
this.tail = tail;
this.length = length;
}
}
