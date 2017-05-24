import java.util.*;

class BubbleSort {

    public bubbleSort(int[] vetor, int leftIndex, int rightIndex) {
        int aux;
        for(int i = leftIndex; i < rightIndex; i++) {
            for(int j = leftIndex; j < rightIndex; j++) {
                if(array[j].CompareTo(array[j+1]) > 0) {
                    aux = array[j];
                    array[j] = array[j+1];
                    array[j+1] = aux;
                }
            }
        }
    }
}