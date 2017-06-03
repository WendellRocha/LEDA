/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

public class GnomeSort {

    public void gnomeSort(T[] array, int leftIndex, int rightIndex) {
        if(array == null || rightIndex > array.length)
            throw new IllegalArgumentException();

        for(int i = leftIndex; i <= rightIndex; i++) {
            int pos = i;

            while(pos > leftIndex && array[pos -1].compareTo(array[pos]) > 0) {
                swap(array, pos, pos - 1);
                pos--;
            }
        }
    }

    public void swap(int [] array, int i, int j) {
        if(array == null) 
            throw new IllegalArgumentException();
        
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}