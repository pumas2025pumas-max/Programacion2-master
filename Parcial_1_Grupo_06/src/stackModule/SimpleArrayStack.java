package stackModule;

public class SimpleArrayStack<E> implements SimpleStack<E> {

	private Object[] array;
	private int size;
	private static final int INITIAL_CAPACITY = 10;

	public SimpleArrayStack() {
		this.array = new Object[INITIAL_CAPACITY];
		this.size = 0;
	}

	// Duplica la capacidad del array cuando se llena
	private void resize() {
		int nuevaCapacidad = array.length * 2;
		Object[] nuevoArray = new Object[nuevaCapacidad];
		for (int i = 0; i < size; i++) {
			nuevoArray[i] = array[i];
		}
		this.array = nuevoArray;
	}

	@Override
	public void push(E element) {
		if (size == array.length) {
			resize();
		}
		array[size] = element;
		size++;
	}

	@Override
	public E pop() {
		if (isEmpty())
			return null;
		E element = (E) array[size - 1];
		array[size - 1] = null;
		size--;
		return element;
	}

	@Override
	public E peek() {
		if (isEmpty())
			return null;
		return (E) array[size - 1];
	}

	@Override
	public void clear() {
		this.array = new Object[INITIAL_CAPACITY];
		this.size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
}
