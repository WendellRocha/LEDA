import java.util.LinkedList;

class HashtableClosedAddress<T> {


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize);
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	int getPrimeAbove(int number) {
		int result = number + 1;
		while (!Util.isPrime(result)) {
			result++;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		int index = hash(element);
		if (element != null && !isFull()) {
			if (this.table[index] == null) {
				LinkedList<T> linkedList = new LinkedList<>();
				linkedList.add(element);
				this.table[index] = linkedList;
			} else {
				((LinkedList<T>) this.table[index]).add(element);
				this.COLLISIONS++;
			}
			this.elements++;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(T element) {
		int index = hash(element);
		if (element != null && this.search(element) != null) {
			if (this.table[index] != null)
				this.COLLISIONS--;

			if (((LinkedList<T>) this.table[index]).size() == 1)
				this.table[index] = null;
			else
				((LinkedList<T>) this.table[index]).remove(element);

			this.elements--;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		int index = hash(element);
		if (this.table[index] == null || !((LinkedList<T>) this.table[index]).contains(element))
			return null;
		else
			return element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(T element) {
		int index = hash(element);
		if (this.table[index] == null || !((LinkedList<T>) this.table[index]).contains(element))
			return -1;
		else
			return index;
	}

	private int hash(T element) {
		int e = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);
		return e;
	}
}