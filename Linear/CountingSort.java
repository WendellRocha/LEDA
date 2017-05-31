/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;
public class CountingSort {

    public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(array == null || rightIndex > array.length)
			throw new IllegalArgumentException();
		
		int max = 0;
        for(int i = leftIndex; i <= rightIndex; i++) {
            if(array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        countingSort(array, max, leftIndex, rightIndex);
	}

    public void countingSort(Integer [] array, int max, int leftIndex, int rightIndex) {
       /**
         * cria o array auxiliar e preenche-o com zeros
         */
        int [] aux = new int[max + 1];
        for(int i = leftIndex; i <= max; i++) {
            aux[i] = 0;
        }

        /**
         * realiza a contagem de ocorrencias no array desordenado
         */
        for(int i = leftIndex; i < rightIndex + 1; i++) {
            aux[array[i]]++;
        }

        /**
         * faz o complemento do número anterior
         */
        for(int i = 1; i <= max; i++) {
            aux[i] += aux[i-1];
        }

        /**
         * ordena o array da esquerda para a direita
         */
        int [] arrayOrdenado = new int [array.length];
        for(int i = rightIndex; i >= leftIndex; i--) {
        	arrayOrdenado[--aux[array[i]]] = array[i];
        }

        /**
         * retorna os valores ordenados para o array de entrada
         */
        for(int i = leftIndex; i < rightIndex + 1; i++) {
            array[i] = arrayOrdenado[i];
        }
    }
}