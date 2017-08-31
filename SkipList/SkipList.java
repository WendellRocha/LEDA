public class SkipList<T> implements SkipList<T> {

    protected boolean USE_MAX_HEIGHT_AS_HEIGHT = false;
    protected SkipListNode<T> root;
    protected SkipListNode<T> NIL;

    protected int maxHeight;

    protected double PROBABILITY = 0.5;
    protected int height;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public SkipList(int maxHeight) {

        if (USE_MAX_HEIGHT_AS_HEIGHT) {
            this.height = maxHeight;
        } else {
            this.height = 1;
        }

        this.maxHeight = maxHeight;
        root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
        NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
        connectRootToNil();
    }

    /**
     * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
     * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
     * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
     * metodo deve conectar apenas o forward[0].
     */
    private void connectRootToNil() {
        for (int i = 0; i < maxHeight; i++) {
            root.forward[i] = NIL;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void insert(int key, T newValue, int height) {
        if (USE_MAX_HEIGHT_AS_HEIGHT)
            height = this.maxHeight;

        if (this.height < height) {
            int current = this.height;

            while (current < height) {
                this.root.forward[current] = this.NIL;
                current++;
            }
            this.height = height;
        }

        SkipListNode<T> node = this.root;
        SkipListNode<T>[] update = new SkipListNode[this.height];

        for (int i = this.height - 1; i >= 0; i--) {
            while (node.forward[i] != null && node.forward[i].key < key) {
                node = node.forward[i];
            }
            update[i] = node;
        }
        node = node.forward[0];

        if (key != node.key) {
            node = new SkipListNode<T>(key, height, newValue);
            int index = 0;
            while (index < height) {
                node.forward[index] = update[index].forward[index];
                update[index].forward[index] = node;
                index++;
            }
        } else {
            node.value = newValue;
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void remove(int key) {
        SkipListNode[] array = new SkipListNode[this.maxHeight];

        SkipListNode<T> node = this.root;

        for (int i = maxHeight - 1; i >= 0; i--) {
            if (node.forward[i] != this.NIL) {
                while (node.forward[i].value != null && node.forward[i].key < key)
                    node = node.forward[i];
            }
            array[i] = node;
        }
        node = node.getForward()[0];

        if (node.key == key) {

            for (int i = 0; i < maxHeight; i++) {
                if (array[i].getForward()[i] != node) {
                    break;
                }
                array[i].getForward()[i] = node.getForward()[i];
            }
        }
    }

    @Override
    public int height() {
        SkipListNode<T>[] forward = root.getForward();
        int h = forward.length - 1;
        if (USE_MAX_HEIGHT_AS_HEIGHT) {
            h = maxHeight;
        } else {
            for (; h >= 0 && isRootOrNil(forward[h]); h--)
                ;
            h += 1;
        }
        return h;
    }

    private boolean isRootOrNil(SkipListNode<T> aux) {
        return aux.getKey() == Integer.MAX_VALUE || aux.getKey() == Integer.MAX_VALUE;
    }

    @Override
    public SkipListNode<T> search(int key) {
        SkipListNode<T> node = this.root;

        for (int i = maxHeight - 1; i >= 0; i--) {
            while (node.getForward(i) != null && node.getForward(i).getKey() < key) {
                node = node.getForward(i);
            }
        }

        SkipListNode<T> aux = node.getForward(0);

        if (aux.getKey() != key) {
            aux = null;
        }

        return aux;
    }

    @Override
    public int size() {
        int size = 0;
        SkipListNode<T> node = this.root.getForward(0);
        while (node != NIL) {
            size++;
            node = node.getForward(0);
        }
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SkipListNode<T>[] toArray() {
        SkipListNode<T>[] array = new SkipListNode[size() + 2];
        SkipListNode<T> node = this.root;

        int index = 0;
        while (node != null) {
            array[index++] = node;
            node = node.getForward(0);
        }
        return array;
    }

}