> https://visualgo.net/en

# 단일 연결 리스트 (Singly Linked Lists)

- 단방향 연결 리스트란?
- 단일 연결 리스트 와 array list 구조 비교
- 클래스 정의 및 함수 생성

<br />

## 단방향 연결 리스트란?

- 자료구조
- 문자열, 숫자등 무었이던 원하는 데이터를 저장하는 자료구조
- 순서에 따라 다수의 데이터를 저장
- 어레이와 차이점
    - 다음 데이터 엘리먼트를 가리키는 익덱스 없이 그냥 다수의 데이터 엘리먼트들로 구성
    - 연결되어 있는 기차같은 모양
    - 첫번째부터 접근해야지만 특정 인덱스에 접근 가능
    - 엘리멘드틀을 노드라고 부른다
    - 노드들은 다음 노드를 가리키는 정보를 저장. 아무값도 없을시 null을저장
    - 헤드 : 연결리스트의 시작노드 가르킴
    - 테일 : 연결리스트의 마지막 노드를 가르킴
    - 길이 : 전체 노드의 개수
- 엘리베이터가 없는 건물로 생각. 5층을 가기 위해서는 1,2,3,4 층을 거쳐야함

```jsx
class Node{
	constructor(val){
		this.val = val;
		this.next = null;
	}

}

class SinglyLinkedList{
	constructor(){
		this.head = null;
		this.tail = null;
		this.length = 0;
	}

	push(val){
		var newNode = new Node(val);
		if(!this.head){
			this.head = newNode;
			this.tail = this.head;
		}else{
			this.tail.next = newNode;
			this.tail = newNode;
		}
		this.length++;
		return this;
	}

	pop(){
		if(!this.head) return undefined;
		var current = this.head;
		var newTail = currend;
		while(current.next){
			newTail = current;
			current = current.next;
		}
		console.log(current.val);
		console.log(newTail.val);

		this.tail = newTail;
		this.tail.next = null;
		this.length--;

		if(this.length === 0){
			this.head = null;
			this.tail = null;
		}
		return current;
	}

	shift(){
		if(!this.head) return undefined;

		var currentHead = this.head;
		this.head = currendHead.next;
		this.length--;
		
		if(this.length === 0){
			this.tail = null;
		}
		return currentHead;
	}

	unShift(val){
		var newNode = new Node(val);

		if(!this.head){
			this.head = new Node;
			this.tail = this.head;
		}else {
			newNode.next = this.head
			this.head = newNode;
		}

		this.length++;
		return this;
	}

	get(index){
		if(index < 0 || index >= this.length) return null;
		var counter = 0;
		var current = this.head;
		while(counter !== index){
			current = current.next;
			counter++;
		}
		return current;
	};

	set(index, val){
		var foundNode = this.get(index);
		if(foundNode){
			foundNode.val = val;
			return true;
		}

		return false;
	}

	insert(){
		if(index < 0 || index > this.length){
			return false;
		}

		if(index === this.length) return !!this.push(val);

		if(index === 0) !!this.unshift(val);

		var newNode = new Node(val);
		var prev = this.get(index-1);
		var temp = prev.next;
		prev.next = newNode;
		newNode.next = temp;
		this.length++;
		return true;
	}

	remove(index){
		if(index < 0 || index >= this.length) return undifined;
		if(index === 0) return this.shift();
		if(index === this.length -1) return this.pop();
		var prevNode = this.get(index -1);
		var removed = prevNode.next;
		prevNode.next = removed.next;
		this.length--;
		return removed;
	
	}

	print(){
		var arr = [];
		var current = this.head;
		while(current){
			arr.push(current.val);
			current = current.next;
		}
		console.log(arr);
	}

	reverse(){
		var node = this.head;
		this.head = this.tail;
		this.tail = node;

		var next;
		var prev = null;

		for(var i = 0; i < this.length; i++){
			next = node.next;
			node.next = prev;
			prev = node;
			node = next;
		}
	}

}
```

<br />

## Big O

- insertion : O(1)
- Removal : O(1) 가장처음값 or O(n) 가장마지막값
- Searching : O(n)
- Access : O(n)