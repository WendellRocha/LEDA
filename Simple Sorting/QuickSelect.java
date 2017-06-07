/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

class QuickSelect<T extends Comparable<T>> {

	public T quickSelect(T[] array, int k) {
		if (array == null || array.length == 0 || k > array.length || k < 1)
			return null;
		return quickSelect(array, 0, array.length - 1, k);
	}

	private T quickSelect(T[] v, int ini, int fim, int k) {
		int i = ini;
		T pivot = v[ini];
		for (int j = ini + 1; j <= fim; j++) {
			if (v[j].compareTo(pivot) < 0) {
				i++;
				swap(v, i, j);
			}
		}

		swap(v, i, ini);

		if (k == i + 1) {
			return pivot;
		} else if (k < i + 1) {
			return quickSelect(v, ini, i - 1, k);
		} else {
			return quickSelect(v, i + 1, fim, k);
		}

	}

	private void swap(Object [] array, int i, int j) {
		if(array == null)
			throw new IllegalArgumentException();
		
		Object aux = array[i];
		array[i] = array[j];
		array[j] = aux;
		
	}
}
