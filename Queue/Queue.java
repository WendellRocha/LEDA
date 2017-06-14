/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

class Queue<T> {

	private T[] array;
	private int tail;

	public Queue(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	public T head() {
		if(isEmpty())
			return null;
		return array[0];
	}

	public boolean isEmpty() {
		return tail == -1;
	}

	public boolean isFull() {
		return tail == array.length - 1;
	}

	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i + 1];
		}
		tail--;
	}

	public void enqueue(T element) throws RuntimeException {
		if(isFull())
			throw new RuntimeException("Queue is full.");
		array[++tail] = element;
	}

	public T dequeue() throws RuntimeException {
		if(isEmpty())
			throw new RuntimeException("Queue is empty.");
		T aux = array[0];
		shiftLeft();
		return aux;
	}
}