> LIFO ( Last In First Out )


```java

class Stack<T> {
	class Node<T> {
		private T data;
		private Node<T> next;
		
		public Node(T data) {
			this.data = data;
		}
	}

	private Node<T> top;

	public T pop() {
		if(top == null) {
			throw nex ExptyStackException();
		}

		T item = top.data;
		top = top.next;
		return item;
	}

	public void push(T item){
		Node<T> t = new Node<T>(item);
		t.next = top;
		top = t;
	}

	public T peek() {
		if(top == null){
			throw new ExptyStackException();
		}	
		return top.data;
	}

	public boolean isEmpty(){
		return top == null;
	}
}


public class Test {
	public static void main (String[] args){
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.pop();
		s.isEmpty();
	}
}

```