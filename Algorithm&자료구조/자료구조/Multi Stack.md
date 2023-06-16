
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
```java
import java.util.EmptyStackException;  
  
class FullStackException extends Exception{
	public FullStackException(){
		super();
	}

	public FullStackException(String msg){
		super(msg);
	}
}
  
class MultiStacks {  
    private class StackInfo{  
        public int start, dataSize, stackSize;  
        public StackInfo(int start, int stackSize){  
            this.start = start;  
            this.stackSize = stackSize;  
            this.dataSize = 0;  
        }  
  
        public boolean isWithinStack(int index){  
            if(index < 0 || index >= values.length){  
                return false;  
            }  
  
            int virualIndex = index < start ? index + values.length : index;  
            int end = start + stackSize;  
            return start <= virualIndex && virualIndex < end;  
        }  
  
        public int getLastStackIndex(){  
            return adjustIndex(start + stackSize -1);  
        }  
        public int getLastDataIndex(){  
            return adjustIndex(start + dataSize -1);  
        }  
        public int getNewDataIndex(){  
            return adjustIndex(getLastDataIndex() +1);  
        }  
        public boolean isFull(){  
            return dataSize == stackSize;  
        }  
        public boolean isEmpty(){  
            return dataSize == 0;  
        }  
    }  
  
    private StackInfo[] info;  
    private int[] values;  
  
    public MultiStacks(int numOfStacks, int defaultSize){  
        info  = new StackInfo[numOfStacks];  
        for(int i = 0; i<numOfStacks; i++){  
            info[i] = new StackInfo(defaultSize * i , defaultSize);  
        }  
        values = new int[numOfStacks * defaultSize];  
    }  
  
    private void expand(int stackNum){  
        int nextStack = (stackNum +1) % info.length;  
        shift(nextStack);  
        info[stackNum].stackSize++;  
    }  
  
    private void shift(int stackNum){  
        StackInfo stack = info[stackNum];  
        if(stack.dataSize >= stack.stackSize){  
            int nextStack = (stackNum +1) % info.length;  
            shift(nextStack);  
            stack.stackSize++;  
        }  
        int index  = stack.getLastStackIndex();  
        while(stack.isWithinStack(index)){  
            values[index] = values[previousIndex(index)];  
            index = previousIndex(index);  
        }  
        values[stack.start] = 0;  
        stack.start = nextIndex(stack.start);  
        stack.stackSize--;  
    }  
  
    public int numberOfElements(){  
        int totalDataSize = 0;  
        for(StackInfo sd : info){  
            totalDataSize += sd.dataSize;  
        }  
        return totalDataSize;  
    }  
  
    public boolean allStacksAreFull(){  
        return numberOfElements() == values.length;  
    }  
  
    private int adjustIndex(int index){  
        int max = values.length;  
        return ((index % max) + max) % max;  
    }  
  
    private int nextIndex(int index){  
        return adjustIndex(index +1);  
    }  
  
    private int previousIndex(int index){  
        return adjustIndex(index-1);  
    }  
    public void push(int stackNum, int value) throws FullStackException{  
        if(allStacksAreFull()){  
            throw  new FullStackException();  
        }  
  
        StackInfo stack = info[stackNum];  
        if(stack.isFull()){  
            expand(stackNum);  
        }  
        values[stack.getNewDataIndex()] = value;  
        stack.dataSize++;  
    }  
  
    public int pop (int stackNum) throws  Exception {  
        StackInfo stack = info[stackNum];  
        if(stack.isEmpty()){  
            throw new EmptyStackException();  
        }  
        int last = stack.getLastStackIndex();  
        int value = values[last];  
        values[last] = 0;  
        stack.dataSize--;  
        return value;  
    }  
  
    public int peek (int stackNum){  
        StackInfo stack = info[stackNum];  
        if(stack.isEmpty()){  
            throw new EmptyStackException();  
        }  
  
        return values[stack.getLastDataIndex()];  
    }  
}  


```