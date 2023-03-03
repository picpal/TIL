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
      poppedNode.prev = null;
    }
    this.length--;
    return poppedNode;
  }

  /**
   *
   * 1. If length is 0, return undifined
   * 2. Store the current head property in a variable ( we'll call it old had)
   * 3. If the length is one
   *  - set the head to be null
   *  - set the tail to be null
   * 4. Update the head to be the next of the old head
   * 5. Set the head's prev property to null
   * 6. Set the old head's next to null
   * 7. Decrement the length
   * 8. Return old head
   */
  shift() {
    if (this.length === 0) return undefined;
    let oldHead = this.head;
    if (this.length === 1) {
      this.head = null;
      this.tail = null;
    } else {
      this.head = oldHead.next;
      this.head.prev = null;
      oldHead.next = null;
    }

    this.length--;
    return oldHead;
  }

  /*
    1. create a new node with the vlaue passed to the function
    2. If the length is 0 
      - Set the head to be the new node
      - Set the tail to be the new node
    3. Otherwise
      - Set the prev property on the head of the list to be the new node
      - Set the next property on the new node to be the head property
      - Update the head to be the new node
    4. Increment the length 
    5. return the list 
  */
  unshift(val) {
    let newNode = new Node(val);
    if (this.length === 0) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      this.head.prev = newNode;
      newNode.next = this.head;
      this.head = newNode;
    }
    this.length++;
    return this;
  }

  /**
   * 1. If the index is less than 0 or greater or equal to the length, return null
   * 2. If the index is less than or equal to half the length of the list
   *  - Loop through the list starting from the head and loop
   *  - Return the node once it is found
   * 3. If the index is greater than half the length of the list
   *  - Loop through the list starting from the tail and loop towards the middle
   *  - Return the node once it is found
   */
  get(index) {
    if (index < 0 || index >= this.length) {
      return null;
    }

    var count, current;
    if (index <= this.length / 2) {
      count = 0;
      current = this.head;
      while (count != index) {
        current = current.next;
        count++;
      }
    } else {
      count = this.lenght - 1;
      current = this.tail;
      while (count != index) {
        current = current.prev;
        count--;
      }
    }
    return current;
  }

  /**
   * 1. create a variable which is the result of the get method
   *    at the index passed to the function
   *  - if the get method returns a valid node, set the value of that
   *    node to be the value passed to the function
   *  - Return true
   * 2. otherwise return false
   */
  set(index, val) {
    var foundNode = this.get(index);
    if (foundNode != null) {
      foundNode.val = val;
      return true;
    }

    return false;
  }

  /**
   *
   */
  insert() {}
}

let list = new DoublyLinkedList();
list.push(1);
list.push(2);
list.push(50);
list.push(100);

const oldTail = list.pop();
console.log(oldTail);

list = new DoublyLinkedList();
list.push("Harry");
list.push("Ron");
list.push("Hermione");

const shift = list.shift();
console.log(shift);
console.log(list);

const unshift = list.unshift("kim");
console.log(unshift);
console.log(list);
