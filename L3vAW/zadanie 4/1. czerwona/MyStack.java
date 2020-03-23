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
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public T pop() throws EmptyStackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void push(T elem) throws FullStackException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public T top() throws EmptyStackException {
		// TODO Auto-generated method stub
		return null;
	}
	
		
}
