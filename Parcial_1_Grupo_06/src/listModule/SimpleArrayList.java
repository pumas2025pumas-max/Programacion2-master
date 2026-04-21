package listModule;

public class SimpleArrayList<E> implements SimpleList<E> {
	
	private Object [] array;
	private int size;
	private static final int INITIAL_CAPACITY = 10;
	public SimpleArrayList() {
		this.array = new Object[INITIAL_CAPACITY];
		this.size = 0;
	}
	
	
	private void resize() {
		int dobleArray = array.length * 2;
		Object [] newArray = new Object[dobleArray];
		
		for (int i = 0; i < size; i ++ ) {
			newArray[i] = array[i];	
		}
		
		this.array = newArray;
			
		
	}

	@Override
	public boolean add(E element) {
		// TODO Auto-generated method stub
		if (size == array.length ) {
			resize();
		}
		array[size] = element;
		size ++;
		return true;
		
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		if (size == array.length) {
			resize();
		}
		for (int i = size; i > index; i --) {
			array[i] = array[i-1];
		}
		array[index] = element;
		size++;
		
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size ) throw new IndexOutOfBoundsException();
		E oldValue = (E) array[index];
		for (int i = index; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		array[size-1] = null;
		size--;
		return oldValue;
	}

	@Override
	public boolean remove(Object object) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(object)) {
				remove(i);
				return true;
				
			}
		}
		return false;
	}

	@Override
	public void clear() {
		this.array = new Object[INITIAL_CAPACITY];
		this.size = 0;
		
	}

	@Override
	public boolean contains(Object object) {
		for (int i = 0; i < size; i++) {
			if(array[i].equals(object))
			return true;
		}
		return false;
	}

	@Override
	public E get(int index) {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		return (E) array[index];
		
	}

	@Override
	public E set(int index, E element) {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		E oldValue = (E) array[index];
		array[index] = element; 
		return oldValue;
		
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
