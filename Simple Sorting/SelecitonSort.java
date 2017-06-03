/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

class SelectionSort {

    public void selectionSort(T [] array, int leftIndex, int rightIndex) {
        T menor;
        int indice;
        for(int i = leftIndex; i < rightIndex; i++) {
            menor = array[i];
            indice = i;
            for(int j = i + 1; j <= rightIndex; j++) {
                if(array[j].compareTo(menor) < 0) {
                    menor = array[j];
                    indice = j;
                }
            }
            array[indice] = array[i];
            array[i] = menor;
        }
    }

    public void selectionSort(T [] array) {
        T menor;
        int indice;
        for(int i = 0; i < array.length; i++) {
            menor = array[i];
            indice = i;
            for(int j = i + 1; j <= array.length; j++) {
                if(array[j].compareTo(menor) < 0) {
                    menor = array[j];
                    indice = j;
                }
            }
            array[indice] = array[i];
            array[i] = menor;
        }
    }
}