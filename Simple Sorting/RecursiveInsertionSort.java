import java.util.*;

class RecursiveInsertionSort {
    public static void recursiveInsertionSort(int [] array, int leftIndex, int rightIndex) {
        if(leftIndex < rightIndex) {
            for(int j = leftIndex; j < rightIndex; j++) {
                if(array[j+1].compareTo(array[j]) < 0) {
                    swap(array, j, j+1);
                }
            }
            for(int i = rightIndex - 1; i > leftIndex; i--) {
                if(array[i-1].compareTo(array[i]) > 0) {
                    swap(array, i, i-1);
                }
            }

            recursiveInsertionSort(array, leftIndex + 1, rightIndex - 1);
        }
    }

    public static void sort(int [] array, int i, int j) {
        if (array == null)
        throw new IllegalArgumentException();
        
        Object aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }
}