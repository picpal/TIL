
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
			throw n
		}
	}

}
```


2. 유동길이 Stack