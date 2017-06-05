/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */
class ExtendedCoutingSort {

    public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(array == null || rightIndex > array.length)
			throw new IllegalArgumentException();
		
		int max = 0;
		int min = 0;
		for(int i = leftIndex; i <= rightIndex; i++) {
			if(array[i].compareTo(max) > 0) {
				max = array[i];
			}
			
			if(array[i].compareTo(min) > 0) {
				min = array[i];
			}
		}
		max = Math.abs(max);
		min = Math.abs(min);
		countingSort(array, min, max, leftIndex, rightIndex);
	}

	private void countingSort(Integer[] array, int min, int max, int leftIndex, int rightIndex) {
		int [] aux = new int[min + max + 1];
		      

        /**
         * realiza a contagem de ocorrencias no array desordenado
         */
        for(int i = leftIndex; i < rightIndex + 1; i++) {
            aux[array[i] + min]++;
        }

        /**
         * faz o complemento do número anterior
         */
        for(int i = 1; i <= min + max; i++) {
            aux[i] += aux[i-1];
        }

        /**
         * ordena o array da esquerda para a direita
         */
        int [] arrayOrdenado = new int [array.length];
        for(int i = rightIndex; i >= leftIndex; i--) {
        	arrayOrdenado[--aux[array[i] + min]] = array[i];
        }

        /**
         * retorna os valores ordenados para o array de entrada
         */
         int count = 0;
        for(int i = leftIndex; i < rightIndex + 1; i++) {
            array[i] = arrayOrdenado[cont++];
        }
    }
}