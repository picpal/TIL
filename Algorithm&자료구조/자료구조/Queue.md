> FIFO ( First In First Out )


```java
class Queus<T> {
	class Node<T> {
		private T data;
		private Node<T> next;

		public Node(T data){
			this.data = data;
		}
	}

	private Node<T> first;
	private Node<T> last;

	public void add (T item){
		Node<T> t = new Node<T>(item);

		if(last != null){
			last.next = t;
		}
		last = t;
		if(first == null){
			first = last;
		}
	}
	
	public void remove (T item){
		if(first == null){
			throw new NoSuchElementExcption();
		}

		T data = first.data;
		first = first.next;

		if(first == null){
			last = null;
		}
		return data;
	}

	public T peek() {
		if(first == null){
			throw new NoSuchElementExcption(); 
		}
		return first.data;
	}

	public boolean isEmpty(){
		return first == null;
	}

}

public class Test {
	public static void main (String[] args){
		Queue<Integer> q = new Queue<Integer>();
		q.add(1);
		q.add(2);
		q.remove();
		q.peek();
		q.isEmpty();
	}
}


```