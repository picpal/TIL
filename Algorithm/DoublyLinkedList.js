// Node
class Node {
  constructor(val) {
    this.val = val;
    this.next = null;
    this.prev = null;
  }
}

//DoublyLinkedList
class DoublyLinkedList {
  constructor() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }

  /**
   * Pushing pseudocode
   * 1. create a new node with the value passed to the function -> 값을 가지는 새로운 노드 생성
   * 2. If the head property is null set the head and tail to be the newly created node -> 헤드가 null인지, 아니면 길이가 0인지 확인 후 헤드와 테일에 새로운 노드를 생성한다.
   * 3. If not, set the next property on the tail to be that node -> 그렇지 않다면(무언가있다면) , 현재 테일을 찾아서 테일의 next 프로퍼티를 새로만든 노드로 설정
   * 4. Set the previous property on the newly created node to be the tail ->
   * 5. Set the tail to be the newly created node -> 테일 프로퍼티를 가장 끝에 있게 된 새로운 노드로 바꿔준다.
   * 6. Return Linked List
   */
  push(val) {
    var newNode = new Node(val);
    if (this.length === 0) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      this.tail.next = newNode;
      newNode.prev = this.tail;
      this.tail = newNode;
    }
    this.length++;
    return this;
  }

  /*
  * 1. If there is no head, return undefind
    2. store the current tail in a variable to return later 
    3. if the length is 1, set the head and tail to be null
    4. update the tail to be the previous Node
    5. Set the newTail's next to null
    6. Decrement the length 
    7. Return the value removed
  */
  pop() {
    if (!this.head) return undefined;
    var poppedNode = this.tail;

    if (this.length === 1) {
      this.head = null;
      this.tail = null;
    } else {
      this.tail = poppedNode.prev;

      this.tail.next = null;
    }
    this.length--;
    return poppedNode;
  }
}

let list = new DoublyLinkedList();
console.log(list.push(1));
console.log(list.push(2));
console.log(list.pop());
