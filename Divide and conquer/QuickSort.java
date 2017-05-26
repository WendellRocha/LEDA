import java.util.*;

class QuickSort {

    public static void quickSort(int [] array, int leftIndex, int rightIndex) {
        if(array == null || rightIndex > array.length)
            throw new IllegalArgumentException();

        if(leftIndex < rightIndex) {
            int pivot = partition(array, leftIndex, rightIndex);
            quickSort(array, leftIndex, pivot - 1);
            quickSort(array, pivot + 1, leftIndex);
        }
        
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