/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

class QuickSortMedianOfThree {
    public void quickSortMedianOfThree(int [] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex && leftIndex >= 0) {
			int center = (leftIndex + rightIndex) / 2;
			medianaDeTres(array, leftIndex, rightIndex, center);
			Util.swap(array, center, rightIndex - 1);
			int posicaoPivot = partition(array, leftIndex, rightIndex - 1);
			quickSortMedianOfThree(array, leftIndex, posicaoPivot - 1);
			quickSortMedianOfThree(array, posicaoPivot + 1, rightIndex);
		}
    }
    private void medianaDeTres(int [] array, int leftIndex, int rightIndex, int center) {
		if(array[leftIndex].compareTo(array[rightIndex]) > 0)
			swap(array, leftIndex, rightIndex);
		if(array[center].compareTo(array[leftIndex]) < 0)
			swap(array, center, leftIndex);
		if(array[center].compareTo(array[rightIndex]) > 0)
			swap(array, center, rightIndex);
	}
	
    private int partition(int [] array, int begin, int end) {
        int pivot = array[begin];
        int i = begin;
        for(int j = i + 1; j <= end; j++) {
            if(array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, begin, i);
        return i;
    }

    private void swap(int [] array, int i, int j) {
        if(array == null || i < 0 || j < 0)
            throw new IllegalArgumentException();   
        
        int aux = array[i];
        array[i] = array[j];
        array[j] = aux;

    }
}