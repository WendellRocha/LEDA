/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

class StackRecursiveDoubleLinkedList<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	public void push(T element) throws StackOverflowException {
		if(element != null) {
			if(isFull())
				throw new StackOverflowException();

			top.insertFirst(element);
		}
	}

	public T pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		
		T aux = this.top();
		top.removeFirst();
		return aux;
	}

	public T top() {
		if (!top.isEmpty())
			return top.toArray()[0];
		else 
			return null;
	}

	public boolean isEmpty() {
		return top.isEmpty();
	}

	public boolean isFull() {
		return top.size() == this.size;
	}
}
