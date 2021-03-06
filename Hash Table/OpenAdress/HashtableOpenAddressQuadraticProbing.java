/**
 * Data Structures and Algorithms Laboratory
 * Período 2017.1
 * @author Wendell Rocha
 */

public class HashtableOpenAddressQuadraticProbing<T extends Storable> extends AbstractHashtableOpenAddress<T> {

    public HashtableOpenAddressQuadraticProbing(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
        super(size);
        hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
        this.initiateInternalTable(size);
    }

    @Override
    public void insert(T element) {
        if (this.isFull())
            throw new HashtableOverflowException();

        if (element != null) {
            if (this.search(element) != null)
                return;

            int i = 0;
            int j = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, i);

            while (i < table.length) {
                if (table[j] == null || deletedElement.equals(table[j])) {
                    table[j] = element;
                    super.elements++;
                    return;
                } else {
                    i++;
                    j = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, i);
                    super.COLLISIONS++;
                }
            }
        }
    }

    @Override
    public void remove(T element) {
        if (this.isEmpty())
            throw new HashtableOverflowException();

        int j = indexOf(element);

        if (j >= 0) {
            table[j] = new DELETED();
            elements--;
        }
    }

    @Override
    public T search(T element) {
        if (element != null) {
            int i = 0;
            int j = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, i);

            while (i < table.length) {
                if (table[j] != null && table[j].equals(element)) {
                    return element;
                } else {
                    i++;
                    j = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, i);
                }
            }
        }
        return null;
    }

    @Override
    public int indexOf(T element) {
        if (this.search(element) != null) {
            int i = 0;
            int j = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, i);

            while (i < table.length) {
                if (table[j].equals(element)) {
                    return j;
                } else {
                    i++;
                    j = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, i);
                }
            }
        }
        return -1;
    }
}
