/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

import java.util.*;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class Heap<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		T[] resp = makeArrayOfComparable(index + 1);
		for (int i = 0; i <= index; i++) {
			resp[i] = this.heap[i];
		}
		return resp;
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		if(position >= this.index || position < 0)
			return;
		
		int l = this.left(position);
		int r = this.right(position);
		int index = position;
		
		if(left(position) <= this.index && this.comparator.compare(this.heap[index],this.heap[l]) < 0)
			index = l;
		
		if(right(position) <= this.index && this.comparator.compare(this.heap[index], this.heap[r]) < 0)
			index = r;
		
		if(index != position) {
			swap(this.heap, index, position);
			this.heapify(index);
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		if(element == null)
			return;
		
		this.heap[++index] = element;
		int i = this.index;
		
		while(i != 0 && this.comparator.compare(this.heap[parent(i)], this.heap[i]) < 0) {
			swap(this.heap, i, this.parent(i));
			i = this.parent(i);
		}
	}

	@Override
	public void buildHeap(T[] array) {
		if(array == null)
			return;
		
		this.heap = array;
		this.index = array.length - 1;
		
		for(int i = this.index; i >= 0; i--) {
			this.heapify(i);
		}
	}

	@Override
	public T extractRootElement() {
		if(this.isEmpty())
			return null;
		
		T result = this.rootElement();
		
		if(this.index >= 0) {
			swap(this.heap, 0, this.index--);
			this.heapify(0);
		}
		
		return result;
	}

	@Override
	public T rootElement() {
		if(this.isEmpty())
			return null;
		
		return this.heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		Comparator<T> comparator = this.getComparator();
		this.index = -1;
		this.comparator = (a, b) -> b.compareTo(a);
		this.buildHeap(array);

		T[] aux = makeArrayOfComparable(this.size());
		
		for(int i = 0; i < aux.length; i++) {
			aux[i] = this.extractRootElement();
		}
		
		this.heap = makeArrayOfComparable(INITIAL_SIZE);
		this.comparator = comparator;
		
		return aux;
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}
	
	private int bigger(T[] array, int i, int j) {
		if(this.comparator.compare(array[i], array[j]) > 0)
			return i;
		else
			return j;
    }
    
    private static void swap(Object[] array, int i, int j) {
        if (array == null)
           throw new IllegalArgumentException();
  
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
     }

    private static <T> T[] makeArrayOfComparable(int size) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Comparable[size];
        return array;
     }

}
