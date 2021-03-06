/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

class QuickSort {

    public void quickSort(T [] array, int leftIndex, int rightIndex) {
        if(array == null || rightIndex > array.length)
            throw new IllegalArgumentException();

        if(leftIndex < rightIndex) {
            int pivot = partition(array, leftIndex, rightIndex);
            quickSort(array, leftIndex, pivot - 1);
            quickSort(array, pivot + 1, leftIndex);
        }
        
    }

    private int partition(int [] array, int begin, int end) {
        T pivot = array[begin];
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

    private void swap(T [] array, int i, int j) {
        if(array == null || i < 0 || j < 0)
            throw new IllegalArgumentException();   
        
        Object aux = array[i];
        array[i] = array[j];
        array[j] = aux;

    }
}