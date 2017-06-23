class RecursiveSingleLinkedList<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedList<T> next;

	public RecursiveSingleLinkedList() {

	}

	public RecursiveSingleLinkedList(T data,
			RecursiveSingleLinkedList<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.getData() == null;
	}

	@Override
	public int size() {
		if(this.isEmpty())
			return 0;
		else
			return 1 + this.getNext().size();
	}

	@Override
	public T search(T element) {
		if(element == null || this.isEmpty())
			return null;
		else
			if(this.getData().equals(element))
				return data;
			else
				return this.getNext().search(element);
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			if(this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedList<T>());
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && !this.isEmpty()) {
			if(this.getData().equals(element)) {
				this.data = this.getNext().getData();
				this.next = this.getNext().getNext();
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Comparable[this.size()];
		
		if(this.isEmpty())
			return array;
		
		toArray(array, 0, this);
		
		return array;
	}
	
	private void toArray(T[] array, int i, RecursiveSingleLinkedList<T> node) {
		if(!node.isEmpty()) {
			array[i++] = node.getData();
			toArray(array, i, node.next);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedList<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedList<T> next) {
		this.next = next;
	}

}
