package queueModule;

public class SimpleArrayQueue<E> implements SimpleQueue<E> {

	private Object[] array;
	private int size;
	private static final int INITIAL_CAPACITY = 10;

	public SimpleArrayQueue() {
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
	public void enqueue(E element) {
		if (size == array.length) {
			resize();
		}
		array[size] = element;
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty()) return null;
		E element = (E) array[0];
		// Desplazar todos los elementos una posición a la izquierda
		for (int i = 0; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		array[size - 1] = null;
		size--;
		return element;
	}

	@Override
	public E peek() {
		if (isEmpty()) return null;
		return (E) array[0];
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
