import javax.management.RuntimeErrorException;

/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

class Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public Stack(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	public T top() {
		if(this.isEmpty())
			return null;
		return array[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == array.length - 1;
	}

	public void push(T element) throws RuntimeException {
		if(this.isFull())
			throw new RuntimeException("Stack is full.");
		array[++top] = element;
	}

	public T pop() throws RuntimeErrorException {
		if(this.isEmpty())
			throw new RuntimeException("Stack is empty.");
		T aux = array[top];
		top--;
		return aux;
	}
}