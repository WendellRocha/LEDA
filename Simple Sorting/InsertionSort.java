/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

class InsertionSort {
    public static void insertionSort(int [] array, int leftIndex, int rightIndex) {
        for(int i = leftIndex; i <= rightIndex; i++){
            int key = array[i];
            int j = i - 1;
            while(j >= leftIndex && array[j].compareTo(key) > 0) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
    }

    public static void insertionSort(int [] array) {
        for(int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while((j > -1) && array[j].compareTo(key) > 0) {
                array[j+1] = array [j];
                j--;
            }
            array[j+1] = key;
        }
    }
}