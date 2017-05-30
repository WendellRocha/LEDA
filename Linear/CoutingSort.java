/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;
public class CoutingSort {

    public void sort(Integer [] array, int leftIndex, int rightIndex) {
        int max = array[0];
        for(int i = leftIndex; i <= rightIndex; i++) {
            if(max.compareTo(array[i]) > 0) {
                max = array[i];
            }
        }
    }

    public void coutingSort(Integer [] array, int max, int leftIndex, int rightIndex) {
        /**
         * cria o array auxiliar e preenche-o com zeros
         */
        int [] aux = new int[max];
        for(int i = leftIndex; i <= max; i++) {
            aux[i] = 0;
        }

        /**
         * realiza a contagem de ocorrencias no array desordenado
         */
        for(int i = 0; i < array.length; i++) {
            aux[array[i]]++;
        }

        /**
         * faz o complemento do número anterior
         */
        for(int i = leftIndex; i < rightIndex; i++) {
            aux[i] += aux[i-1];
        }

        /**
         * ordena o array da esquerda para a direita
         */
        int [] arrayOrdenado = new int [array.length];
        for(int j = rightIndex; j > leftIndex; j--) {
            arrayOrdenado[--ayx[array[i]]] = array[i];
        }

        /**
         * retorna os valores ordenados para o array de entrada
         */
        for(int i = leftIndex; i < rightIndex; i++) {
            array[i] = arrayOrdenado[i];
        }
    }
}