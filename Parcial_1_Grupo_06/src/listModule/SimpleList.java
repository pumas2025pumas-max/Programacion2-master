package listModule;

public interface SimpleList <E> {
	public boolean add(E Element);
	public void add (int index, E element);
	public E remove(int index);
	public boolean remove (Object object);
	public void clear();
	public boolean contains(Object object);
	public E get(int index);
	public E set(int index, E element);
	public int size();
	public boolean isEmpty();
	
	
	
	
	
	

}
