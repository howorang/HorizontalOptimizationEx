package dmcs.excercise.leaks;

import java.util.Arrays;

public class LeakyStack<T> {

    private int size;
    private int stackPointer = -1;
    private Object[] array;

    public LeakyStack() {
        this(10);
    }

    public LeakyStack(int size) {
        this.size = size;
        this.array = new Object[size];
    }

    @SuppressWarnings("unchecked")
    public T get() {
        return (T) array[stackPointer--];
    }

    public void put(T object) {
        if ((stackPointer + 1) >= size) {
            resize();
        }
        array[++stackPointer] = object;
    }

    private void resize() {
        this.size = 2 * this.size;
        array = Arrays.copyOf(array, size);
    }
}
