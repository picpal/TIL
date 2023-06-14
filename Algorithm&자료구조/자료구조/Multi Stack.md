
배열로 Stack 만들기

1. 고정길이 Stack
```java
class FixedMultiStacks {
	private int numOfStacks = 3;
	private int stackSize;
	private int[] values;
	private int[] sizes;

	class FullStackException extends Exception{
		public FullStackException(){
			super();
		}

		public FullStackException(String msg){
			super(msg);
		}
	
	}

	public FixedMultiStacks(int stackSize){
		this.stackSize = stackSize;
		this.sizes = new int[numOfStacks];
		this.values = new int[numOfStacks * stackSize];
	}

	public boolean isEmpty(int stackNum){
		return sizes[stackNum] == 0;
	}

	public boolean isFull(int stackNum){
		return sizes[stackNum] == stackSize;
	}

	public int getTopIndex(int stackNum){
		int offset = stackSize * stackNum;
		int size = sizes[stackNum];
		return offset + size - 1;
	}

	public void push(int stackNum, int data){
		if(isFull(stackNum)){
			throw new FullStackException();
		}
		values[getTopIndex(stackNum) + 1] = data;
		sizes[stackNum]++;
	}

	public int pop(int stackNum){
		if(isEmpty(stackNum)){
			throw new EmptyStackException();
		}
		int top = getTopIndex(stackNum);
		int data = values[top];
		values[top] = 0;
		sizes[stackNum]--;
		return data;
	}

	public int peek(int stackNum){
		if(isEmpty(stackNum)){
			throw new EmptyStackException();
		}
		return values[getTopIndex(stackNum)];
	}
}

public class Test{
	public static void main(String[] args){
		FixedMultiStaks ms = new FixedMultiStacks(5);
		try{
			ms.push(0,1);
		}catch(FullStackException e){
			System.out.println("It's Full");
		}
	}

}

```


2. 유동길이 Stack