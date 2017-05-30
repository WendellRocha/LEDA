import java.util.*;

class MergeSort {

    public void mergeSort(int [] array, int leftIndex, int rightIndex) {
        if(array == null || rightIndex > array.length)
			throw new IllegalArgumentException();
		
		if(leftIndex < rightIndex) {
			int mid = (leftIndex + rightIndex) / 2;
			mergeSort(array, leftIndex, mid);
			mergeSort(array, mid + 1, rightIndex);
			merge(array, leftIndex, mid, rightIndex);
		}
    }

    private void merge(int [] array, int leftIndex, int mid, int rightIndex) {
		@SuppressWarnings("unchecked")
		int[] aux = (int[]) new Comparable[rightIndex + 1];
		
		for(int a = leftIndex; a <= rightIndex; a++) {
			aux[a] = array[a];
		}
		
		int k = leftIndex;
		int i = leftIndex;
		int j = mid + 1;
		
		while(i <= mid && j <= rightIndex) {
			if(aux[i].compareTo(aux[j]) <= 0) {
				array[k] = aux[i];
				i++;
			} else {
				array[k] = aux[j];
				j++;
			}
			k++;
		}
		while(i <= mid) {
			array[k] = aux[i];
			i++;
			k++;
		}
		while(j <= rightIndex) {
			array[k] = aux[j];
			j++;
			k++;
		}
	}
}