import java.util.Arrays;

public class DynamicArray<T> {

    private int size;
    private T[] array;

    public DynamicArray(int initialCapacity) {

        if (initialCapacity < 0) {

            throw new IllegalArgumentException("Invalid");
        }

        this.size = 0;
        this.array = (T[]) new Object[initialCapacity];
    }

    private int getSize(){

        return this.size;
    }

    public boolean isEmpty(){

        return this.size == 0;
    }

    public T get(int index){

        // Checks if index is valid
        if (index < 0 || this.size <= index){

            throw new IndexOutOfBoundsException();
        }

        return this.array[index];
    }

    public void set(int index, T element){

        // Checks if index is valid
        if (index < 0 || this.size <= index){

            throw new IndexOutOfBoundsException();
        }

        array[index] = element;
    }

    public void add(T element){

        ensureCapacity(this.size+1);
        this.array[size] = element;
        size++;
    }

    public void insert(int index, T element){

        // Checks if index is valid
        if (index < 0 || this.size <= index){

            throw new IndexOutOfBoundsException();
        }

        ensureCapacity(size + 1);

        // Shift elements to the right to make space for the new element
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = element;
        size++;

    }

    public void remove(int index)
    private void ensureCapacity(int minCapacity){
        if(minCapacity > this.array.length){
            int newCapacity = Math.max(array.length * 2, minCapacity);
            this.array = Arrays.copyOf(array, newCapacity);
        }
    }

}