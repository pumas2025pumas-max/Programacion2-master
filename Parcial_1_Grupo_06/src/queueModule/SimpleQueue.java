package queueModule;

public interface SimpleQueue<E> {
	public void enqueue(E element);
	public E dequeue();
	public E peek();
	public void clear();
	public int size();
	public boolean isEmpty();
}
