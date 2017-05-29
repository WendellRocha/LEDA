/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

class BubbleSort {
    public static void bubbleSort(int[] array, int leftIndex, int rightIndex) {
        for(int i = leftIndex; i < rightIndex; i++) {
            for(int j = leftIndex; j < rightIndex; j++) {
                if(array[j].compareTo(array[j+1]) > 0) {
                    swap(array, j, j+1);
                }
            }
        }
    }

    public static void bubbleSort(int [] array) {
        boolean trocou = true;
        while(trocou) {
            trocou = false;
            for(int i = 0; i < array.length - 1; i++) {
                if(array[i].compareTo(array[i+2]) > 0) {
                    swap(array, i, i+1);
                    trocou = true;
                }
            }
        }
    }

    public static void swap(int [] array, int i, int j) {
        if(array == null) 
            throw new IllegalArgumentException();
        
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}