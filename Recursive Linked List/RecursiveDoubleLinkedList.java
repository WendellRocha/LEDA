/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */


class RecursiveDoubleLinkedList<T> extends RecursiveSingleLinkedList<T> {

	protected RecursiveDoubleLinkedList<T> previous;

	public RecursiveDoubleLinkedList() {

	}

	public RecursiveDoubleLinkedList(T data, RecursiveSingleLinkedList<T> next, RecursiveDoubleLinkedList<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			if(!this.isEmpty()) {
				RecursiveDoubleLinkedList<T> node = new RecursiveDoubleLinkedList<T>(this.data, this.next, this);
				((RecursiveDoubleLinkedList<T>)node.next).previous = node;
				this.setData(element);
				this.setNext(node);
			} else {
				this.insert(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if(!this.isEmpty()) {
				this.data = this.getNext().getData();
				this.next = this.getNext().getNext();
				
				if(this.getNext() != null) 
					this.previous = this;
		}
	}

	@Override
	public void removeLast() {
		if(!this.isEmpty()) {
			if(this.previous.isEmpty() && this.next.isEmpty()) {
				this.removeFirst();
			}
			
			if(this.getNext().isEmpty()) {
				this.previous.setNext(new RecursiveDoubleLinkedList<T>());
			} else {
				((RecursiveDoubleLinkedList<T>) this.next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedList<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedList<T> previous) {
		this.previous = previous;
	}

}
