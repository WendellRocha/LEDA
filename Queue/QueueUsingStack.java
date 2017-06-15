/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws RuntimeException {
		if(stack1.isFull())
			throw new RuntimeException("Queue is full.");
		
		if(stack1.isEmpty()) {
			try {
				stack1.push(element);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		} else {
			while(!stack1.isEmpty()) {
				try {
					stack2.push(stack1.pop());
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
			
			try {
				stack1.push(element);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			
			while(!stack2.isEmpty()) {
				try {
					stack1.push(stack2.pop());
				} catch (RuntimeExceptionn e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public T dequeue() throws RuntimeException {
		if(isEmpty()) {
			throw new RuntimeException("Queue is empty.");
		} else {
			try {
				return stack1.pop();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public T head() {
		return stack1.top();
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}
}