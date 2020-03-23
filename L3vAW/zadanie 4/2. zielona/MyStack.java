package zadanie2;

public class MyStack<T> implements IStack<T> {

	private static final int DEFAULT_CAPACITY = 16;
	T array[];
	int topIndex;
	int size;
	
	@SuppressWarnings("unchecked")
	public MyStack (int initialSize){
		array = (T[])(new Object[initialSize]);
		topIndex = array.length - 1;
		size = 0;
	}	
	public MyStack (){
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == array.length;
	}

	@Override
	public T pop() throws EmptyStackException {
		if(isEmpty()) throw new EmptyStackException();
		size--;
		T t = array[topIndex];
		if(--topIndex < 0) topIndex = array.length-1;
		return t;
	}

	@Override
	public void push(T elem) throws FullStackException {
		if(size < array.length) size++;
		if(++topIndex >= array.length) topIndex = 0; 
		array[topIndex] = elem;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T top() throws EmptyStackException {
		if(size == 0) throw new EmptyStackException();
		return array[topIndex];
	}
	
}
