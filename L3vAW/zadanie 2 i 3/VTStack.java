package zadanie2;

public class VTStack<T> extends MyStack<T> {
	int cursor;
	
	public VTStack(int initialSize) {
		super(initialSize);
		toTop();
	}
	public VTStack() {
		super();
		toTop();
	}
	
	@Override
	public void push(T item) throws FullStackException{
		super.push(item);
		toTop();
	}
	
	@Override
	public T pop() throws EmptyStackException {
		T pom = super.pop();
		toTop();
		return pom;
	}
	
	public T peek() {
		return array[cursor];
	}
	
	public void toTop() {
		cursor = topIndex;
	}
	
	public void down() throws BottomOfStackException {
		if(--cursor < 0) throw new BottomOfStackException();
	}
}
