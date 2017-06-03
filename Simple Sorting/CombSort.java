/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

public class CombSort {

    public combSort(T[] array, int leftIndex, int rightIndex) {
        if(array == null || rightIndex > array.length)
            throw new IllegalArgumentException();

        int gap = rightIndex - leftIndex;
        boolean trocou = true;
        int i = 0;

        if(array.length <= 1)
            trocou = false;

        while(gap > 1 || trocou) {
            if(gap > 1)
                gap = (int) (gap / 1.25);
        
            i = leftIndex;
            trocou = false;
            while(i + gap < rightIndex + 1) {
                if(array[i].compareTo(array[i + gap]) > 0) {
                    swap(array, i, i + gap);
                    trocou = true;
                }
                i++;
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